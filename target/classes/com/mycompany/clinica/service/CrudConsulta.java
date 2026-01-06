
package com.mycompany.clinica.service;

import com.mycompany.clinica.entity.Consulta;
import java.util.List;

public interface CrudConsulta {
    List<Consulta> obtenerConsultas(int idPaciente);
    int guardar(Consulta consulta, int idPaciente);
    String validarCampos(Consulta consulta);
}
