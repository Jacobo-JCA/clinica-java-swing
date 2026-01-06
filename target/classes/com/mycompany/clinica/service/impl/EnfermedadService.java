package com.mycompany.clinica.service.impl;

import com.mycompany.clinica.execption.NegocioException;
import com.mycompany.clinica.repository.BaseDatos;
import com.mycompany.clinica.entity.Enfermedades;
import com.mycompany.clinica.service.CrudEnfermedad;
import java.util.List;

public class EnfermedadService implements CrudEnfermedad {
    private final BaseDatos db = BaseDatos.getInstanceDB();
    
    @Override
    public String validarCampos(Enfermedades enfermedades) {
        StringBuilder mensajeError = new StringBuilder();
        if (enfermedades.getPatologico() == null || enfermedades.getPatologico().trim().isEmpty()) {
            mensajeError.append("El antecedente patológico no puede estar vacío.");
        }

        if (enfermedades.getNoPatologico() == null || enfermedades.getNoPatologico().trim().isEmpty()) {
            mensajeError.append("El antecedente no patológico no puede estar vacío.");
        }

        if (enfermedades.getClinico() == null || enfermedades.getClinico().trim().isEmpty()) {
            mensajeError.append("El antecedente clínico no puede estar vacío.");
        }

        if (enfermedades.getQuirurjico() == null || enfermedades.getQuirurjico().trim().isEmpty()) {
            mensajeError.append("El antecedente quirúrgico no puede estar vacío.");
        }

        if (enfermedades.getHereditario() == null || enfermedades.getHereditario().trim().isEmpty()) {
            mensajeError.append("El antecedente hereditario no puede estar vacío.");
        }
        return mensajeError.length() > 0 ? mensajeError.toString() : null;
    }
    
    @Override
    public int guardar(Enfermedades enfermedad, int idPaciente) {
        String mensaje = validarCampos(enfermedad);
        if (mensaje != null) {
            throw new NegocioException(mensaje);
        }
        return db.insertEnfermedades(enfermedad, idPaciente);
    }

    @Override
    public List<Enfermedades> obtenerEnfermedades(int idPaciente) {
        return db.obtenerEnfermedades(idPaciente);
    }
}
