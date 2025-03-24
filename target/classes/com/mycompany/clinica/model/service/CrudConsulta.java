
package com.mycompany.clinica.model.service;

import com.mycompany.clinica.model.entity.Consulta;
import java.util.List;

public interface CrudConsulta {
    List<Consulta> obtenerConsultas(int idPaciente);
    int guardar(Consulta consulta, int idPaciente);
    String validarCampos(Consulta consulta);
}
