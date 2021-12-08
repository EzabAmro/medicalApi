package com.nezab.MedicalApp.converters;

import com.nezab.MedicalApp.dtos.MedicalAppointmentDTO;
import com.nezab.MedicalApp.models.MedicalAppointment;

public class MedicalAppointmentConverter extends AbstractConverter<MedicalAppointment, MedicalAppointmentDTO> {
    @Override
    public MedicalAppointmentDTO fromEntity(MedicalAppointment entity) {
        if (entity == null) return null;
        return MedicalAppointmentDTO.builder()
                .id(entity.getId())
                .regDate(entity.getRegDate())
                .doctor(entity.getDoctor())
                .patient(entity.getPatient())
                .build();
    }

    @Override
    public MedicalAppointment fromDTO(MedicalAppointmentDTO dto) {
        if (dto == null) return null;
        return MedicalAppointment.builder()
                .id(dto.getId())
                .regDate(dto.getRegDate())
                .doctor(dto.getDoctor())
                .patient(dto.getPatient())
                .build();
    }
}
