package com.nezab.MedicalApp.controllers;

import com.nezab.MedicalApp.converters.PatientConverter;
import com.nezab.MedicalApp.dtos.PatientDTO;
import com.nezab.MedicalApp.models.Patient;
import com.nezab.MedicalApp.repositories.PatientRepository;
import com.nezab.MedicalApp.services.PatientService;
import com.nezab.MedicalApp.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Pageable;


@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    private final PatientConverter patientConverter = new PatientConverter();

    @GetMapping(value = "/patients")
    public ResponseEntity<WrapperResponse<List<PatientDTO>>> findAllPatients(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "1") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Patient> patients = patientService.findAll(page);
        return new WrapperResponse<>(
                "Success",
                patientConverter.fromEntity(patients),
                true
        ).createResponse();
    }

    @PostMapping(value = "/patients")
    public ResponseEntity<WrapperResponse<PatientDTO>> create(
            @RequestBody Patient patient
    ) {
        Patient newPatient = patientService.save(patient);
        return new WrapperResponse<>(
                "Success",
                patientConverter.fromEntity(newPatient),
                true
        ).createResponse();
    }

}
