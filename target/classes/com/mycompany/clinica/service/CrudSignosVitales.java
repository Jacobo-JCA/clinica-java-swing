
package com.mycompany.clinica.service;

import com.mycompany.clinica.entity.SignosVitales;
import java.util.List;

public interface CrudSignosVitales {
    int guardar(SignosVitales signosVitales, int idConsulta);
    List<SignosVitales> obtenerSignosVitales(int idConsulta);
    String validarCampos(SignosVitales signosVitales);
}
