package com.mycompany.clinica.service;

import com.mycompany.clinica.entity.Paciente;
import java.util.List;

public interface CrudPaciente {
    int guardar(Paciente paciente);
    List<Paciente> obtenerPacientesPorCampo(String campo);
    Paciente obtenerPorId(int id);
    List<Paciente> obtenerPacientes();
    void actualizar(Paciente paciente);
    void eliminar(int id);
    String validarCampos(Paciente paciente);
}
