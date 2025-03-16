
package com.mycompany.clinica.model.service;

import com.mycompany.clinica.model.entity.Enfermedades;
import java.util.List;

/**
 *
 * @author jacob
 */
public interface CrudEnfermedad {
    void guardar(Enfermedades enfermedad, int idPaciente);
    List<Enfermedades> obtenerEnfermedades(int idPaciente);
    String validarCampos(Enfermedades enfermedades);
}
