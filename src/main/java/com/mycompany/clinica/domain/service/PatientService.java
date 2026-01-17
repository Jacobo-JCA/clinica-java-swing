package com.mycompany.clinica.domain.service;

import com.mycompany.clinica.domain.entity.Patient;
import java.util.List;

public interface PatientService {
    int savePatient(Patient patient);
    List<Patient> getPatientByField(String field);
    Patient getPatientById(int patientId);
    List<Patient> getAllPatients();
    void updatePatient(Patient patient);
    void deletePatient(int patientId);
    String validateFields(Patient patient);
}
