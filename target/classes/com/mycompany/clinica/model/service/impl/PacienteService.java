package com.mycompany.clinica.model.service.impl;

import com.mycompany.clinica.execption.ValidacionException;
import com.mycompany.clinica.model.data.BaseDatos;
import com.mycompany.clinica.model.entity.Paciente;
import java.util.List;
import com.mycompany.clinica.model.service.CrudPaciente;
import java.time.LocalDate;

public class PacienteService implements CrudPaciente {
    private final BaseDatos db = BaseDatos.getInstanceDB();

    public PacienteService() {
    }
    
    @Override
    public String validarCampos(Paciente paciente) {
        StringBuilder mensajeError = new StringBuilder();
        if (!paciente.getCedula().matches("[0-9]{10}")) {
            mensajeError.append("La cédula debe ser un número de 10 dígitos.\n");
        }
        if (!paciente.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            mensajeError.append("El email no es válido.\n");
        }
        if (paciente.getFechaNacimiento() != null && paciente.getFechaNacimiento().isAfter(LocalDate.now())) {
            mensajeError.append("La fecha de nacimiento no puede ser futura.\n");
        }
        if (paciente.getExpediente() <= 0) {
            mensajeError.append("El número de expediente debe ser mayor a cero.\n");
        }
        if (!paciente.getTelefono().matches("[0-9]+") || paciente.getTelefono().length() < 10) {
            mensajeError.append("El teléfono debe contener solo números y tener al menos 10 dígitos.\n");
        }
        if (paciente.getEdad() <= 0 || paciente.getEdad() > 130) {
            mensajeError.append("La edad debe ser un número positivo y razonable (menor o igual a 130).\n");
        }
        return mensajeError.length() > 0 ? mensajeError.toString() : null;
    }
    
    @Override
    public int guardar(Paciente paciente) {
        String message = validarCampos(paciente);
        if(message != null) {
            throw new ValidacionException(message);
        }
        return db.insertPaciente(paciente);
    }

    @Override
    public List<Paciente> obtenerPacientes() { 
        return db.obtenerPacientes();
    }

    @Override
    public void actualizar(Paciente paciente) {
        db.actualizarPaciente(paciente);
    }

    @Override
    public void eliminar(int id) {
        db.deletePaciente(id);
    }

    @Override
    public Paciente obtenerPorId(int id) {
        return db.buscarPacientePorId(id);
    }

    @Override
    public List<Paciente> obtenerPacientesPorCampo(String campo) {
        return db.obtenerPacientesPorCampo(campo);
    }
}
