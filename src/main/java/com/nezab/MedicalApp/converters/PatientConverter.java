package com.nezab.MedicalApp.converters;

import com.nezab.MedicalApp.dtos.PatientDTO;
import com.nezab.MedicalApp.models.Patient;

public class PatientConverter extends AbstractConverter<Patient, PatientDTO> {
    @Override
    public PatientDTO fromEntity(Patient entity) {
        if (entity == null) return null;
        return PatientDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .build();
    }

    @Override
    public Patient fromDTO(PatientDTO dto) {
        if (dto == null) return null;
        return Patient.builder()
                .id(dto.getId())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
    }
}
