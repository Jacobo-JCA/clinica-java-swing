
package com.mycompany.clinica.service;

import com.mycompany.clinica.entity.Enfermedades;
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
