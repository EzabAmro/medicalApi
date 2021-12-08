package com.nezab.MedicalApp.converters;

import com.nezab.MedicalApp.dtos.DoctorDTO;
import com.nezab.MedicalApp.models.Doctor;

public class DoctorConverter extends AbstractConverter<Doctor, DoctorDTO>{
    @Override
    public DoctorDTO fromEntity(Doctor entity) {
        if (entity == null) return null;
        return DoctorDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .specialty(entity.getSpecialty())
                .build();
    }

    @Override
    public Doctor fromDTO(DoctorDTO dto) {
        if (dto == null) return null;
        return Doctor.builder()
                .id(dto.getId())
                .name(dto.getName())
                .specialty(dto.getSpecialty())
                .build();
    }
}
