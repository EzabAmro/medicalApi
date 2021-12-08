package com.nezab.MedicalApp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicalAppointments")
public class MedicalAppointment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reg_date", nullable = false, updatable = false)
    private LocalDateTime regDate;

    @ManyToOne
    @JoinColumn(name = "fk_patient", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "fk_doctor", nullable = false)
    private Doctor doctor;

    @Override
    public String toString() {
        return "MedicalAppointment{" +
                "id=" + id +
                ", regDate=" + regDate +
                ", patient=" + patient +
                ", doctor=" + doctor +
                '}';
    }
}
