package com.example.HMS.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password; // will store the HASHED password, never plain text

    private String role; // e.g. "ADMIN", "DOCTOR", "RECEPTIONIST"
}