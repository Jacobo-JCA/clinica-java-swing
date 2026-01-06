
package com.mycompany.clinica.aplication.service;

import com.mycompany.clinica.domain.entity.Enfermedades;
import java.util.List;

/**
 *
 * @author jacob
 */
public interface CrudEnfermedad {
    int guardar(Enfermedades enfermedad, int idPaciente);
    List<Enfermedades> obtenerEnfermedades(int idPaciente);
    String validarCampos(Enfermedades enfermedades);
}
