package com.mycompany.clinica.domain.repo;

import com.mycompany.clinica.domain.entity.VitalSigns;
import java.util.List;

public interface VitalSignsRepo {
    int insertVitalSigns(VitalSigns vitalSigns, int consultationId);
    List<VitalSigns> getAllVitalSigns(int consultationId);
}
