package com.mycompany.clinica.domain.repo;

import com.mycompany.clinica.domain.entity.HealthStatus;
import java.util.List;

public interface HealthStatusRepo {
    int insertHealthStatus(HealthStatus healthStatus, int patientId);
    List<HealthStatus> getAllHealthStatus(int patientId);
}
