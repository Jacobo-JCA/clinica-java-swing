
package com.mycompany.clinica.domain.service;

import com.mycompany.clinica.domain.entity.Consultation;
import java.util.List;

public interface ConsultationService {
    List<Consultation> getConsultationByPatient(int patientId);
    int save(Consultation consultation, int patientId);
    String validateFields(Consultation consultation);
}
