package com.mycompany.clinica.controller;

import com.mycompany.clinica.common.GenericSwingWorker;
import com.mycompany.clinica.common.SesionContexto;
import com.mycompany.clinica.execption.ManejadorError;
import com.mycompany.clinica.model.entity.Paciente;
import com.mycompany.clinica.model.service.CrudPaciente;
import com.mycompany.clinica.view.gui.PacienteFrame;
import com.mycompany.clinica.common.MensajeInformativo;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

public class PacienteController {
    private final CrudPaciente pacienteService;
    private final PacienteFrame vistaPaciente;
    private final SesionContexto sesionContexto;
    private final RegistroControllerCentral registroCentral;

    public PacienteController(CrudPaciente pacienteService, PacienteFrame vistaPaciente,
            RegistroControllerCentral registroCentral, SesionContexto sesionContexto) {
        this.pacienteService = pacienteService;
        this.vistaPaciente = vistaPaciente;
        this.registroCentral = registroCentral;
        this.sesionContexto = sesionContexto;
    }

    public void cargarTodosPacientes() {
        GenericSwingWorker<List<Paciente>> worker = new GenericSwingWorker<>(
                () -> {
                    List<Paciente> pacientes = pacienteService.obtenerPacientes();
                    return pacientes;
                },
                pacientes -> vistaPaciente.actualizarTabla(pacientes),
                e -> MensajeInformativo.mostrarError("Error al cargar pacientes: " + e.getMessage())
        );
        worker.execute();
    }

    private void verificarPaciente(Paciente paciente) {
        Paciente pacienteSesion = sesionContexto.getPaciente();
        if (pacienteSesion != null) {
            paciente.setIdPaciente(pacienteSesion.getIdPaciente());
        } else {
            paciente.setIdPaciente(0);
        }
    }

    private Paciente convertirFormularioAEntidad(List<String> campos) {
        if (campos.stream().anyMatch(String::isEmpty)) {
            MensajeInformativo.mostrarError("Todos los campos son obligatorios.");
            return null;
        }
        LocalDate fechaNacimiento;
        try {
            fechaNacimiento = LocalDate.parse(campos.get(9));
        } catch (DateTimeParseException e) {
            MensajeInformativo.mostrarError("La fecha de nacimiento es inválida o no se a ingresado el campo..");
            return null;
        }
        Paciente paciente = new Paciente();
        paciente.setCedula(campos.get(0));
        paciente.setNombre(campos.get(1));
        paciente.setApellido(campos.get(2));
        paciente.setDireccion(campos.get(3));
        paciente.setEmail(campos.get(4));
        paciente.setEdad(Period.between(fechaNacimiento, LocalDate.now()).getYears());
        paciente.setGenero(campos.get(5));
        paciente.setExpediente(Integer.parseInt(campos.get(6)));
        paciente.setCiudad(campos.get(7));
        paciente.setEstado(campos.get(8));
        paciente.setFechaNacimiento(fechaNacimiento);
        paciente.setTelefono(campos.get(10));
        paciente.setOcupacion(campos.get(11));
        verificarPaciente(paciente);
        return paciente;
    }

    private Optional<Boolean> esNuevo(Paciente paciente) {
        if (paciente == null) {
            return Optional.empty();
        }
        if (paciente.getIdPaciente() == -1 || paciente.getIdPaciente() == 0) {
            return Optional.of(true);
        }
        Paciente pacienteDb = pacienteService.obtenerPorId(paciente.getIdPaciente());
        return Optional.of(pacienteDb == null);
    }

    private void guardarPacienteCompleto(Paciente paciente) {
        int idGenereado = pacienteService.guardar(paciente);
        if (idGenereado != -1) {
            sesionContexto.setPaciente(paciente);
            registroCentral.guardarRegistroCompleto();
            MensajeInformativo.mostrarConfirmacion("Paciente y registros guardados exitosamente!");
        } else {
            MensajeInformativo.mostrarError("Error al guardar el paciente.");
        }
    }

    private void actualizarPacienteCompleto(Paciente paciente) {
        pacienteService.actualizar(paciente);
        sesionContexto.setPaciente(paciente);
        registroCentral.guardarRegistroCompleto();
        MensajeInformativo.mostrarConfirmacion("Datos actualizados correctamente");
    }

    public void procesarDatosPaciente(List<String> datos) {
        try {
            Paciente paciente = convertirFormularioAEntidad(datos);
            Optional<Boolean> resultado = esNuevo(paciente);
            if (resultado.isEmpty()) {
                return;
            }
            if (resultado.get()) {
                guardarPacienteCompleto(paciente);
            } else {
                actualizarPacienteCompleto(paciente);
            }
            cargarTodosPacientes();
        } catch (Exception e) {
            String message = ManejadorError.obtenerMensajeError(e);
            MensajeInformativo.mostrarError(message);
        }
    }

    public void seleccionarPaciente(int idPaciente) {
        Paciente paciente = pacienteService.obtenerPorId(idPaciente);
        if (paciente == null) {
            MensajeInformativo.mostrarError("Paciente no encontrado");
            return;
        }
        sesionContexto.setPaciente(paciente);
        vistaPaciente.mostrarDetallesPaciente(paciente);
    }

    public void buscarPacientes(String campo) {
        try {
            List<Paciente> pacientes = pacienteService.obtenerPacientesPorCampo(campo);
            vistaPaciente.actualizarTabla(pacientes);
        } catch (Exception e) {
            MensajeInformativo.mostrarError("Error en la busqueda: " + e.getMessage());
        }
    }

//    public void eliminarPaciente(int idPaciente) {
//        pacienteService.eliminar(idPaciente);
//        vistaPaciente.actualizarTabla(pacienteService.obtenerPacientes());
//    }
}
