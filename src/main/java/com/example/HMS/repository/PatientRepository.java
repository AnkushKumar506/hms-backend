package com.example.HMS.repository;

import com.example.HMS.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    
} 
    

