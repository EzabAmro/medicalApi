package com.nezab.MedicalApp.dtos;


import com.nezab.MedicalApp.models.Doctor;
import com.nezab.MedicalApp.models.Patient;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalAppointmentDTO {
    private Long id;
    private LocalDateTime regDate;
    private Doctor doctor;
    private Patient patient;
}
