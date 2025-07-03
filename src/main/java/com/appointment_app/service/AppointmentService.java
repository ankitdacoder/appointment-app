package com.appointment_app.service;

import com.appointment_app.entity.Appointment;
import com.appointment_app.entity.User;
import com.appointment_app.repository.AppointmentRepository;
import com.appointment_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.*;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final UserRepository patientRepo;
    private final AppointmentRepository appointmentRepo;

    public User registerPatient(User patient) {
        return patientRepo.save(patient);
    }

    public Appointment bookAppointment(Long patientId, Long localDateTime, String reason) {

        LocalDateTime localDateTimeNow =    LocalDateTime.ofInstant(
                Instant.ofEpochSecond(localDateTime),
                ZoneId.systemDefault()
        );


        if (localDateTimeNow.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment must be today or in the future.");
        }

        User patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        long unixTimestamp = localDateTimeNow.toEpochSecond(ZoneOffset.of("+05:30")); // or use ZoneOffset.of("+05:30") for IST
        System.out.println("Unix Timestamp: " + unixTimestamp);


        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setReason(reason);
        appointment.setAppointmentDate(unixTimestamp);


        return appointmentRepo.save(appointment);
    }

    public List<Appointment> getAppointmentsByDate(Long date) {
        return appointmentRepo.findAppointmentsForTodayAndFuture(date);
    }


}
