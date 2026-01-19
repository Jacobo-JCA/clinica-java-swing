package com.mycompany.clinica.aplication.usecase;

import com.mycompany.clinica.infrastructure.execption.ValidationException;
import com.mycompany.clinica.domain.entity.Patient;
import com.mycompany.clinica.domain.repo.PatientRepo;
import java.util.List;
import java.time.LocalDate;
import com.mycompany.clinica.domain.service.PatientService;

public class PatientServiceImpl implements PatientService {
    private final PatientRepo patientRepo;
    
    public PatientServiceImpl(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }
    
    @Override
    public String validateFields(Patient patient) {
        StringBuilder mensajeError = new StringBuilder();
        if (!patient.getDni().matches("[0-9]{10}")) {
            mensajeError.append("La cédula debe ser un número de 10 dígitos.\n");
        }
        if (!patient.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            mensajeError.append("El email no es válido.\n");
        }
        if (patient.getDateOfBirth() != null && patient.getDateOfBirth().isAfter(LocalDate.now())) {
            mensajeError.append("La fecha de nacimiento no puede ser futura.\n");
        }
        if (patient.getMedicalRecordNumber() <= 0) {
            mensajeError.append("El número de expediente debe ser mayor a cero.\n");
        }
        if (!patient.getPhoneNumber().matches("[0-9]+") || patient.getPhoneNumber().length() < 10) {
            mensajeError.append("El teléfono debe contener solo números y tener al menos 10 dígitos.\n");
        }
        return mensajeError.length() > 0 ? mensajeError.toString() : null;
    }
    
    @Override
    public int savePatient(Patient patient) {
        String message = validateFields(patient);
        if(message != null) {
            throw new ValidationException(message);
        }
        return patientRepo.insertPatient(patient);
    }

    @Override
    public List<Patient> getAllPatients() { 
        return patientRepo.getAllPatients();
    }
    
    @Override
    public Patient getPatientById(int patientId) {
        return patientRepo.getPatientById(patientId);
    }

    @Override
    public List<Patient> getPatientByField(String field) {
        return patientRepo.getAllPatientByField(field);
    }

    @Override
    public void updatePatient(Patient patient) {
        patientRepo.updatePatient(patient);
    }

    @Override
    public void deletePatient(int patientId) {
        patientRepo.deletePatient(patientId);
    }
}
