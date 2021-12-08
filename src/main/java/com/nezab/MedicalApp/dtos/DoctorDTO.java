package com.nezab.MedicalApp.dtos;


import com.nezab.MedicalApp.models.MedicalAppointment;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {

    private Long id;
    private String name;
    private String specialty;
    private List<MedicalAppointment> appointments;
}
