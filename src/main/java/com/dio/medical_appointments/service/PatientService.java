package com.dio.medical_appointments.service;

import com.dio.medical_appointments.domain.model.Patient;
import com.dio.medical_appointments.domain.repository.PatientRepository;
import com.dio.medical_appointments.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository repository;

    public List<Patient> getAll() {
        return repository.findAll();
    }

    public Patient findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + id));
    }

    public Patient save(Patient patient) {
        if (repository.existsByCpf(patient.getCpf()))
            throw new BusinessException("CPF already registered.");
        if (repository.existsByEmail(patient.getEmail()))
            throw new BusinessException("Email already registered.");
        return repository.save(patient);
    }

    public Patient update(Long id, Patient patientUpdated) {
        return repository.findById(id)
                .map(patient -> {
                    patient.setName(patientUpdated.getName());
                    patient.setCpf(patientUpdated.getCpf());
                    patient.setCellphone(patientUpdated.getCellphone());
                    patient.setEmail(patientUpdated.getEmail());
                    patient.setDateOfBirth(patientUpdated.getDateOfBirth());

                    return repository.save(patient);
                }).orElseThrow(() -> new EntityNotFoundException("Consultation not found with ID: "+ id));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Patient not found with ID: " + id);
        }
        repository.deleteById(id);
    }
}
