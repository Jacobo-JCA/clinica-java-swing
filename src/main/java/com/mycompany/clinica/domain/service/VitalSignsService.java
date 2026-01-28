
package com.mycompany.clinica.domain.service;

import com.mycompany.clinica.domain.entity.VitalSigns;
import java.util.List;

public interface VitalSignsService {
    int guardar(VitalSigns signosVitales, int medicalAppointmentId);
    List<VitalSigns> obtenerSignosVitales(int medicalAppointmentId);
    String validarCampos(VitalSigns signosVitales);
}
