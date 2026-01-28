package com.mycompany.clinica.domain.repo;

import com.mycompany.clinica.domain.entity.Consultation;
import java.util.List;

public interface ConsultationRepo {
    int insertConsultation(Consultation consultation, int patientId);
    List<Consultation> getConsultationsByPatient(int patientId);
}
