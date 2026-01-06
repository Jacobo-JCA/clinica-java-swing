package com.mycompany.clinica.aplication.service.impl;
import com.mycompany.clinica.infrastructure.execption.NegocioException;
import com.mycompany.clinica.infrastructure.repository.BaseDatos;
import com.mycompany.clinica.domain.entity.Consultation;
import com.mycompany.clinica.aplication.service.CrudConsulta;
import java.time.LocalDate;
import java.util.List;

public class ConsultaService implements CrudConsulta {
    private final BaseDatos db = BaseDatos.getInstanceDB();
    
    @Override
    public String validarCampos(Consultation consulta) {
        StringBuilder mensajeError = new StringBuilder();
        if (consulta.getMotivoConsulta() == null || consulta.getMotivoConsulta().trim().isEmpty()) {
            mensajeError.append("El motivo de consulta no puede estar vacío.");
        }       
        if (consulta.getFechaConsulta().isAfter(LocalDate.now())) {
            mensajeError.append("La fecha de consulta no puede ser en el futuro.");
        }        
        if (consulta.getDiagnostico() == null || consulta.getDiagnostico().trim().isEmpty()) {
            mensajeError.append("El diagnóstico no puede estar vacío.");
        }
        if (consulta.getReceta() == null || consulta.getReceta().trim().isEmpty()) {
            mensajeError.append("La receta no puede estar vacio.");
        }       
        return mensajeError.length() > 0 ? mensajeError.toString() : null;
    }
    
    @Override
    public int guardar(Consultation consulta, int idPaciente) {
        String mensaje = validarCampos(consulta);
        if (mensaje != null) {
            throw new NegocioException(mensaje);
        }
        return db.insertConsulta(consulta, idPaciente);
    }
    
    @Override
    public List<Consultation> obtenerConsultas(int idPaciente) {
        return db.obtenerConsultas(idPaciente);
    }
}
