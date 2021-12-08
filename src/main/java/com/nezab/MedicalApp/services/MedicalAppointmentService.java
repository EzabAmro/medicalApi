package com.nezab.MedicalApp.services;

import com.nezab.MedicalApp.models.MedicalAppointment;
import com.nezab.MedicalApp.models.Patient;
import com.nezab.MedicalApp.repositories.MedicalAppointmentRepository;
import com.nezab.MedicalApp.repositories.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class MedicalAppointmentService {

    @Autowired
    private MedicalAppointmentRepository medicalAppointmentRepository;

    public List<MedicalAppointment> findAll(Pageable page) {
        try {
            return medicalAppointmentRepository.findAll(page).toList();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage(), e);
        }
    }

    public MedicalAppointment findById(Long id) {
        try {
            return medicalAppointmentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Appointment not found"));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage(), e);
        }
    }

    @Transactional
    public MedicalAppointment save(MedicalAppointment medicalAppointment) {
        try {
            return medicalAppointmentRepository.save(medicalAppointment);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Error(e.getMessage(), e);
        }
    }
}
