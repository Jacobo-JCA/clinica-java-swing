package com.mycompany.clinica.view.gui;

import com.mycompany.clinica.model.entity.Paciente;

public interface PacienteListener {
    // patron observer
    void onPacienteSeleccionado(Paciente paciente);
}
