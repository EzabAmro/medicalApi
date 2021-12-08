package com.nezab.MedicalApp.dtos;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private List<MedicalAppointmentDTO> appointments;
}
