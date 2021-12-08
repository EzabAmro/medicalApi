package com.nezab.MedicalApp.services;

import com.nezab.MedicalApp.models.Doctor;
import com.nezab.MedicalApp.models.Patient;
import com.nezab.MedicalApp.repositories.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> findAll(Pageable page) {
        try {
            return patientRepository.findAll(page).toList();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage(), e);
        }
    }

    @Transactional
    public Patient save(Patient patient) {
        try {

            return patientRepository.save(patient);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage(), e);
        }
    }

    public Patient findPatientById(Long id) {
        try {
            return patientRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Patient not found"));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage(), e);
        }
    }


}
