package com.appointment_app.repository;

import com.appointment_app.entity.Appointment;
import com.appointment_app.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends PagingAndSortingRepository<Appointment,Long>, CrudRepository<Appointment,Long> {

    @Query(value = "SELECT a FROM Appointment a WHERE a.appointmentDate >= :today")
    List<Appointment> findAppointmentsForTodayAndFuture(@Param("today") Long today, Pageable pageable);

}
