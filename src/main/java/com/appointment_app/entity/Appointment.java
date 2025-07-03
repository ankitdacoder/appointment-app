package com.appointment_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_id_generator")
    @SequenceGenerator(name = "appointment_id_generator", sequenceName = "appointment_id_sequence", initialValue =1000, allocationSize = 1)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;



    @Column(nullable = false, updatable = false)
    private Long appointmentDate;


    private String reason;

    @Column(nullable = false, updatable = false)
    private Long createdAt;



    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+05:30"));

        // set before insert
    }


}
