package com.example.HMS.service;

import com.example.HMS.entity.Doctor;
import com.example.HMS.repository.AppointmentRepository;
import com.example.HMS.repository.DoctorRepository;
import com.example.HMS.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    public DoctorService(DoctorRepository doctorRepository, UserRepository userRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        return doctorRepository.findById(id).map(doctor -> {
            doctor.setName(updatedDoctor.getName());
            doctor.setSpecialization(updatedDoctor.getSpecialization());
            doctor.setContact(updatedDoctor.getContact());
            doctor.setAvailability(updatedDoctor.getAvailability());
            return doctorRepository.save(doctor);
        }).orElseThrow(() -> new RuntimeException("Doctor not found with id " + id));
    }

    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id " + id));

        if (appointmentRepository.existsByDoctorId(id)) {
            throw new RuntimeException("Cannot delete doctor with existing appointments. Reassign or remove them first.");
        }

        // Clean up the linked login account too, if one exists
        if (doctor.getUsername() != null) {
            userRepository.findByUsername(doctor.getUsername())
                    .ifPresent(userRepository::delete);
        }

        doctorRepository.deleteById(id);
    }
}