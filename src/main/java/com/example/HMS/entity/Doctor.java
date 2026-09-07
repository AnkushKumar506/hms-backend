package com.example.HMS.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "doctors")
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String specialization;

    private String contact;

    private String availability; // e.g. "Mon-Fri, 9AM-5PM"
}