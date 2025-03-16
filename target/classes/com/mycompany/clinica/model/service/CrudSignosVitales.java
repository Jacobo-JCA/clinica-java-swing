
package com.mycompany.clinica.model.service;

import com.mycompany.clinica.model.entity.SignosVitales;

public interface CrudSignosVitales {
    void guardar(SignosVitales signosVitales, int idConsulta);
    SignosVitales obtenerSignoVitalePorId(int idConsulta);
    String validarCampos(SignosVitales signosVitales);
}
