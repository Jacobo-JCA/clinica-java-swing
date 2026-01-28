package com.mycompany.clinica.domain.service;

import com.mycompany.clinica.domain.entity.Consultation;
import java.util.List;

public interface ConsultationService {
    List<Consultation> getConsultationByPatient(int medicalAppointmentId);
    int save(Consultation consultation, int medicalAppointmentId);
    String validateFields(Consultation consultation);
}
