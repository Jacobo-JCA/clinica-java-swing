package com.mycompany.clinica.domain.service;

import com.mycompany.clinica.domain.entity.MedicalAppointment;
import java.util.List;

public interface MedicalAppointmentService {
    int saveAppointment(MedicalAppointment medicalAppointment, int patientId); 
    MedicalAppointment getAppointment(int patientId);
    List<MedicalAppointment> getAppointmentsByPatient(int patientId); 
    void updateAppointment(MedicalAppointment appointment); 
    void deleteAppointment(int appointmentId);
}
