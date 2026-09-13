package com.example.HMS.repository;

import com.example.HMS.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorId(Long doctorId);
}