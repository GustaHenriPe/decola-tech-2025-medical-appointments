package com.dio.medical_appointments.service;

import com.dio.medical_appointments.domain.model.Consultation;
import com.dio.medical_appointments.domain.model.Doctor;
import com.dio.medical_appointments.domain.model.Patient;
import com.dio.medical_appointments.domain.repository.ConsultationRepository;
import com.dio.medical_appointments.domain.repository.DoctorRepository;
import com.dio.medical_appointments.domain.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public List<Consultation> getAll() {
        return consultationRepository.findAll();
    }

    public Consultation findById(Long id) {
        return consultationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consultation not found with ID: " + id));
    }

    @Transactional
    public Consultation save(Consultation consultation) {
        Long doctorId = consultation.getDoctor().getId();
        Long patientId = consultation.getPatient().getId();

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID: " + doctorId));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + patientId));

        consultation.setDoctor(doctor);
        consultation.setPatient(patient);

        return consultationRepository.save(consultation);
    }

    public Consultation update(Long id, Consultation consultationUpdated) {
        return consultationRepository.findById(id)
                .map(existingConsultation -> {
                    existingConsultation.setScheduledDateTime(consultationUpdated.getScheduledDateTime());
                    existingConsultation.setReason(consultationUpdated.getReason());
                    existingConsultation.setStatus(consultationUpdated.getStatus());
                    return consultationRepository.save(existingConsultation);
                })
                .orElseThrow(() -> new EntityNotFoundException("Consultation not found with ID: "+ id));
    }

    public void delete(Long id) {
        if (!consultationRepository.existsById(id)) {
            throw new EntityNotFoundException("Consultation not found with ID: " + id);
        }
        consultationRepository.deleteById(id);
    }

}
