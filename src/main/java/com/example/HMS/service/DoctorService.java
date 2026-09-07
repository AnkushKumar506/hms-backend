package com.example.HMS.service;

import com.example.HMS.entity.Doctor;
import com.example.HMS.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
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
        doctorRepository.deleteById(id);
    }
}