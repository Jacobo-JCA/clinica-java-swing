
package com.mycompany.clinica.domain.service;

import com.mycompany.clinica.domain.entity.HealthStatus;
import java.util.List;

/**
 *
 * @author jacob
 */
public interface HealthStatusService {
    int guardar(HealthStatus enfermedad, int patientId);
    List<HealthStatus> getAllHealthStatus(int idPaciente);
    String validarCampos(HealthStatus healthStatus);
}
