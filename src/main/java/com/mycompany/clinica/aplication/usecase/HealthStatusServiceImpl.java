package com.mycompany.clinica.aplication.usecase;

import com.mycompany.clinica.aplication.util.ValidationUtil;
import com.mycompany.clinica.infrastructure.execption.NegocioException;
import com.mycompany.clinica.domain.entity.HealthStatus;
import com.mycompany.clinica.domain.repo.HealthStatusRepo;
import java.util.List;
import com.mycompany.clinica.domain.service.HealthStatusService;

public class HealthStatusServiceImpl implements HealthStatusService {
    private final HealthStatusRepo healthStatusRepo;
    
    public HealthStatusServiceImpl(HealthStatusRepo healthStatusRepo) {
        this.healthStatusRepo = healthStatusRepo;
    }
    
    @Override
    public String validarCampos(HealthStatus healthStatus) {
        StringBuilder mensajeError = new StringBuilder();
        if (ValidationUtil.isNullOrBlank(healthStatus.getPathological())) {
            mensajeError.append("El antecedente patológico no puede estar vacío.");
        }

        if (ValidationUtil.isNullOrBlank(healthStatus.getNoPathological())) {
            mensajeError.append("El antecedente no patológico no puede estar vacío.");
        }

        if (ValidationUtil.isNullOrBlank(healthStatus.getClinical())) {
            mensajeError.append("El antecedente clínico no puede estar vacío.");
        }

        if (ValidationUtil.isNullOrBlank(healthStatus.getSurgical())) {
            mensajeError.append("El antecedente quirúrgico no puede estar vacío.");
        }

        if (ValidationUtil.isNullOrBlank(healthStatus.getHereditary())) {
            mensajeError.append("El antecedente hereditario no puede estar vacío.");
        }
        return mensajeError.length() > 0 ? mensajeError.toString() : null;
    }
    
    @Override
    public int guardar(HealthStatus healthStatus, int patientId) {
        String mensaje = validarCampos(healthStatus);
        if (mensaje != null) {
            throw new NegocioException(mensaje);
        }
        return healthStatusRepo.insertHealthStatus(healthStatus, patientId);
    }

    @Override
    public List<HealthStatus> getAllHealthStatus(int patientId) {
        return healthStatusRepo.getAllHealthStatus(patientId);
    }
}
