
package com.mycompany.clinica.domain.service;

import com.mycompany.clinica.domain.entity.VitalSigns;
import java.util.List;

public interface VitalSignsService {
    int guardar(VitalSigns signosVitales, int idConsulta);
    List<VitalSigns> obtenerSignosVitales(int idConsulta);
    String validarCampos(VitalSigns signosVitales);
}
