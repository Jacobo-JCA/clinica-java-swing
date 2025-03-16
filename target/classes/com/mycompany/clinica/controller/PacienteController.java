package com.mycompany.clinica.controller;

import com.mycompany.clinica.execption.ManejadorError;
import com.mycompany.clinica.model.entity.Consulta;
import com.mycompany.clinica.model.entity.Enfermedades;
import com.mycompany.clinica.model.entity.Paciente;
import com.mycompany.clinica.model.entity.SignosVitales;
import com.mycompany.clinica.model.service.CrudPaciente;
import com.mycompany.clinica.view.gui.PacienteFrame;
import com.mycompany.clinica.view.gui.PacienteListener;
import com.mycompany.clinica.view.gui.VistaPaciente;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.SwingWorker;


public class PacienteController {
    private CrudPaciente pacienteService;
    private ConsultaController consultaController;
    private VistaPaciente<PacienteFrame> vistaPaciente;
    private PacienteListener listener;

    public PacienteController(CrudPaciente pacienteService, VistaPaciente<PacienteFrame> vistaPaciente,
            ConsultaController consultaController) {
        this.pacienteService = pacienteService;
        this.vistaPaciente = vistaPaciente;
        this.consultaController = consultaController;
    }
    
//    public PacienteController(CrudPaciente pacienteService, VistaPaciente<PacienteFrame> vistaPaciente) {
//        this.pacienteService = pacienteService;
//        this.vistaPaciente = vistaPaciente;
//    }

    public void cargarTodosPacientes() {
        SwingWorker<List<Paciente>, Void> worker = new SwingWorker<List<Paciente>, Void>() {
            @Override
            protected List<Paciente> doInBackground() throws Exception {
                Instant inicio = Instant.now();
                List<Paciente> pacientes = pacienteService.obtenerPacientes();
                Instant fin = Instant.now();
                long tiemposMs = Duration.between(inicio, fin).toMillis();
                System.out.println("Tiempos de carga de pacientes: " + tiemposMs + " ms");
                return pacientes;
            }
            
            @Override
            protected void done() {
                try {
                    List<Paciente> pacientes = get();
                    vistaPaciente.actualizarTabla(pacientes);
                } catch (Exception e) {
                    vistaPaciente.mostrarError("Error al cargar pacientes: " + e.getMessage());
                }
            }
        };
        worker.execute();
    }
    
    public Paciente convertirFormularioAEntidad(String cedula, String nombre, String apellido, String direccion, 
                               String email, String genero, String expedienteStr, String ciudad, 
                               String estado, String fechaNacStr, String telefono, String ocupacion)
        throws NumberFormatException, DateTimeParseException {
        
        return new Paciente(
            cedula,
            nombre,
            apellido,
            direccion,
            email,
            Period.between(LocalDate.parse(fechaNacStr), LocalDate.now()).getYears(),
            genero,
            Integer.parseInt(expedienteStr),
            ciudad,
            estado,
            LocalDate.parse(fechaNacStr),
            telefono,
            ocupacion
        );
    }
    
    private int guardarPaciente() {
        int idPaciente = 0;
        try {
            Paciente paciente = vistaPaciente.obtenerCamposPaciente();
            if(consultaController.validarConsulta(paciente.getIdPaciente())) {
                idPaciente = pacienteService.guardar(paciente);
                vistaPaciente.mostrarConfirmacion("Paciente guardado exitosamente");
            }
            return idPaciente;
        } catch (Exception e) {
            String mensajeError = ManejadorError.obtenerMensajeError(e);
            vistaPaciente.mostrarError(mensajeError);
            return -1;
        }
    }
    
    public void registroCompleto() {
        try {
            int idPaciente = guardarPaciente();
            if (idPaciente > 0) {
                consultaController.guardarConsultaYSignoVital(idPaciente);
            }
        } catch (Exception e) {
            String mensajeError = ManejadorError.obtenerMensajeError(e);
            vistaPaciente.mostrarError(mensajeError);
        }
    }
    
    public void seleccionarPaciente(int idPaciente) {
        Paciente paciente = pacienteService.obtenerPorId(idPaciente);
        if (paciente != null) {
            listener.onPacienteSeleccionado(paciente);
            vistaPaciente.mostrarDetallesPaciente(paciente);
        } else {
            vistaPaciente.mostrarError("Paciente no encontrado");
        }
    }
    
    public void cargarPacienteSeleccionado(int idPaciente) {
        Paciente paciente = pacienteService.obtenerPorId(idPaciente);
        if (paciente != null) {
            vistaPaciente.mostrarDetallesPaciente(paciente);
        } else {
            vistaPaciente.mostrarError("Paciente no encontrado");
        }
    }

    public void eliminarPaciente(int idPaciente) {
        pacienteService.eliminar(idPaciente);
        vistaPaciente.actualizarTabla(pacienteService.obtenerPacientes());
    }
    
    public void agregarConsultaYSignosVitales(int idPaciente, Consulta consulta, SignosVitales signos) {
        //consultaService.guardarConsulta(idPaciente, consulta, signos);
        //vistaPaciente.mostrarConfirmacion("Enfermedad agregada");
    }

    public void agregarEnfermedad(int idPaciente, Enfermedades enfermedad) {
        //enfermedadService.guardarEnfermedad(idPaciente, enfermedad);
        //vistaPaciente.mostrarConfirmacion("Enfermedad agregada");
    }
}
