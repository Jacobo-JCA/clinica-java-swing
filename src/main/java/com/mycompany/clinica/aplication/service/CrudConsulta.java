
package com.mycompany.clinica.aplication.service;

import com.mycompany.clinica.domain.entity.Consultation;
import java.util.List;

public interface CrudConsulta {
    List<Consultation> obtenerConsultas(int idPaciente);
    int guardar(Consultation consulta, int idPaciente);
    String validarCampos(Consultation consulta);
}
