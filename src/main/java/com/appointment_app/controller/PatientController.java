package com.appointment_app.controller;

import com.appointment_app.entity.Appointment;
import com.appointment_app.entity.User;
import com.appointment_app.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final AppointmentService appointmentService;
    private record Patient(Integer patientId,String patientName,String phoneNumber){}

     List<Patient> patients=new ArrayList<>(List.of(
             new Patient(1001,"Ankit","8120836123"),
             new Patient(1001,"Shivani","939551704"),
             new Patient(1001,"Archana","8120836123"),
             new Patient(1001,"Vivek","8120836123"),
             new Patient(1001,"Mira","8120836123")
     ));


    @GetMapping("/list")
    public List<Patient> getPatients(){
        return patients;
    }

    @PostMapping("/patients")
    public ResponseEntity<User> registerPatient(@RequestBody User patient) {
        return ResponseEntity.ok(appointmentService.registerPatient(patient));
    }


    @PostMapping("/book")
    public ResponseEntity<?> bookAppointment(@RequestBody Appointment appointment) {
        try {

            return ResponseEntity.ok(appointmentService.bookAppointment(appointment.getId(), appointment.getAppointmentDate(), appointment.getReason()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/appointments")
    public List<Appointment> getAppointmentsByDate(@RequestParam("date") Long localDateTime) {
        LocalDateTime localDateTimeNow =    LocalDateTime.ofInstant(
                Instant.ofEpochSecond(localDateTime),
                ZoneId.systemDefault()
        );
        Long unixTimestamp = localDateTimeNow.toEpochSecond(ZoneOffset.of("+05:30")); // or use ZoneOffset.of("+05:30") for IST
        System.out.println("Unix Timestamp: " + unixTimestamp);
        return appointmentService.getAppointmentsByDate(unixTimestamp);
    }



}
