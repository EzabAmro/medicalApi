package com.nezab.MedicalApp.services;


import com.nezab.MedicalApp.models.Doctor;
import com.nezab.MedicalApp.repositories.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> findAll(Pageable page) {
        try {
            return doctorRepository.findAll(page).toList();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage(), e);
        }
    }
    public Doctor findDoctorById(Long id) {
        try {
            return doctorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage(), e);
        }
    }


    public Doctor save(Doctor doctor) {
        try {
            return doctorRepository.save(doctor);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage(), e);
        }
    }

}
