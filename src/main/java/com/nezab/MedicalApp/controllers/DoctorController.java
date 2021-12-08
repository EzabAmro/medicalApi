package com.nezab.MedicalApp.controllers;

import com.nezab.MedicalApp.converters.DoctorConverter;
import com.nezab.MedicalApp.dtos.DoctorDTO;
import com.nezab.MedicalApp.dtos.PatientDTO;
import com.nezab.MedicalApp.models.Doctor;
import com.nezab.MedicalApp.models.Patient;
import com.nezab.MedicalApp.services.DoctorService;
import com.nezab.MedicalApp.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    private final DoctorConverter doctorConverter = new DoctorConverter();


    @GetMapping(value = "/doctors")
    public ResponseEntity<WrapperResponse<List<DoctorDTO>>> findAllDoctor(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "1") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Doctor> doctors = doctorService.findAll(page);
        return new WrapperResponse<>(
                "Success",
                doctorConverter.fromEntity(doctors),
                true
        ).createResponse();
    }

    @PostMapping(value = "/doctors")
    public ResponseEntity<WrapperResponse<DoctorDTO>> create(
            @RequestBody Doctor doctor
    ) {
        Doctor newDoctor = doctorService.save(doctor);
        return new WrapperResponse<>(
                "Success",
                doctorConverter.fromEntity(newDoctor),
                true
        ).createResponse();
    }

}
