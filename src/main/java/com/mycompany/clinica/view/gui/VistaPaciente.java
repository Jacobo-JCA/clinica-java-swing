package com.mycompany.clinica.view.gui;

import com.mycompany.clinica.model.entity.Consulta;
import com.mycompany.clinica.model.entity.Paciente;
import com.mycompany.clinica.model.entity.SignosVitales;
import java.util.List;
import javax.swing.JInternalFrame;

public interface VistaPaciente<T extends JInternalFrame> extends PacienteListener {
    void mostrarError(String message);
    void mostrarConfirmacion(String message);
    void mostrarDetallesPaciente(Paciente paciente);
    Paciente obtenerCamposPaciente();
    Consulta obtenerConsulta(int idPaciente);
    List<String> obtenerCamposConsultaVista();
    SignosVitales obtenerCamposSignoVital(int idConsulta);
    void actualizarTabla(List<Paciente> pacientes);
    T getFrame();
}
