package com.example.HMS.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "patients")
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    private String gender;

    private String contact;

    private String medicalHistory;

    private String admissionStatus; // e.g. "Admitted", "Discharged", "OPD"
}