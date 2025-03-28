package com.dio.medical_appointments.service;

import com.dio.medical_appointments.domain.model.Doctor;
import com.dio.medical_appointments.domain.repository.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository repository;

    public List<Doctor> getAll() {
        return repository.findAll();
    }

    public Doctor findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID: " + id));
    }

    public Doctor save(Doctor doctor) {
        return repository.save(doctor);
    }

    public Doctor update(Long id, Doctor doctorUpdated) {
        return repository.findById(id)
                .map(doctor -> {
                    doctor.setName(doctorUpdated.getName());
                    doctor.setSpecialty(doctorUpdated.getSpecialty());
                    return repository.save(doctor);
                })
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID: "+ id));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Doctor not found with ID: " + id);
        }
        repository.deleteById(id);
    }

}
