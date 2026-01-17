package com.mycompany.clinica.domain.repo;

import com.mycompany.clinica.domain.entity.Patient;
import java.util.List;

public interface PatientRepo {
    int insertPatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientByDni(String dni);
    Patient getPatientById(int patientId);
    List<Patient> getAllPatientByField(String field);
    void updatePatient(Patient patient);
    void deletePatient(int patientId);
}
