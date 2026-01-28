
package com.mycompany.clinica.domain.service;

import com.mycompany.clinica.domain.entity.HealthStatus;
import java.util.List;


public interface HealthStatusService {
    int guardar(HealthStatus enfermedad, int medicalAppointmentId);
    List<HealthStatus> getAllHealthStatus(int medicalAppointmentId);
    String validarCampos(HealthStatus healthStatus);
}
