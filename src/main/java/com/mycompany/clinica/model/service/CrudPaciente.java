package com.mycompany.clinica.model.service;

import com.mycompany.clinica.model.entity.Paciente;
import java.util.List;

public interface CrudPaciente {
    int guardar(Paciente paciente);
    Paciente obtenerPorCedula(String cedula);
    Paciente obtenerPorId(int id);
    List<Paciente> obtenerPacientes();
    void actualizar(Paciente paciente);
    void eliminar(int id);
    String validarCampos(Paciente paciente);
}
