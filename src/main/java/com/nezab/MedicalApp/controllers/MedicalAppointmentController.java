package com.nezab.MedicalApp.controllers;

import com.nezab.MedicalApp.converters.MedicalAppointmentConverter;
import com.nezab.MedicalApp.dtos.MedicalAppointmentDTO;
import com.nezab.MedicalApp.models.Doctor;
import com.nezab.MedicalApp.models.MedicalAppointment;
import com.nezab.MedicalApp.models.Patient;
import com.nezab.MedicalApp.services.*;
import com.nezab.MedicalApp.utils.SmsRequest;
import com.nezab.MedicalApp.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalAppointmentController {

    private final Service service;

    @Autowired
    private MedicalAppointmentService medicalAppointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private SendEmail sendEmail;

    private final MedicalAppointmentConverter medicalAppointmentConverter = new MedicalAppointmentConverter();

    @Autowired
    public MedicalAppointmentController(Service service) {
        this.service = service;
    }

    @GetMapping(value = "/appointments")
    public ResponseEntity<WrapperResponse<List<MedicalAppointmentDTO>>> findAllMedicalAppointment(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "1") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<MedicalAppointment> appointments = medicalAppointmentService.findAll(page);
        return new WrapperResponse<>(
                "Success",
                medicalAppointmentConverter.fromEntity(appointments),
                true
        ).createResponse();
    }

    @GetMapping(value = "/appointments/{id}")
    public ResponseEntity<WrapperResponse<MedicalAppointmentDTO>> findMedicalAppointmentById(
            @PathVariable Long id
    ) {
        MedicalAppointment appointment = medicalAppointmentService.findById(id);
        return new WrapperResponse<>(
                "Success",
                medicalAppointmentConverter.fromEntity(appointment),
                true
        ).createResponse();
    }

    @PostMapping(value = "/appointments")
    public ResponseEntity<WrapperResponse<MedicalAppointmentDTO>> create(
            @RequestBody MedicalAppointment appointment
            ) {
        MedicalAppointment newMedicalAppointment = medicalAppointmentService.save(appointment);

        Doctor doctor = doctorService.findDoctorById(appointment.getDoctor().getId());
        Patient patient = patientService.findPatientById(appointment.getPatient().getId());

        sendEmail.sendEmail(
                "baezmoranicolas@gmail.com",
                patient.getEmail(),
                "Citas nueva EPS - confirmación de cita",
                (
                        "El presente correo es para informar al paciente " +
                        patient.getName() +
                        " que su cita fue confirmada para el dia " +
                        newMedicalAppointment.getRegDate().toString().split("T")[0] +
                        " a la hora " +
                        newMedicalAppointment.getRegDate().toString().split("T")[1] +
                        " con el doctor " +
                        doctor.getName() +
                        ",con especialidad en " +
                        doctor.getSpecialty()
                )
        );

        SmsRequest smsRequest = new SmsRequest(patient.getPhone(), patient.getName() +
                " su cita fue confirmada para el dia " +
                newMedicalAppointment.getRegDate().toString().split("T")[0] +
                " a la hora " +
                newMedicalAppointment.getRegDate().toString().split("T")[1] +
                " con el doctor " +
                doctor.getName() +
                ",con especialidad en " +
                doctor.getSpecialty());
        service.sendSms(smsRequest);

        return new WrapperResponse<>(
                "Success",
                medicalAppointmentConverter.fromEntity(newMedicalAppointment),
                true
        ).createResponse();
    }

}
