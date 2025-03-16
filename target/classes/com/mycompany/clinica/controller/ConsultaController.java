package com.mycompany.clinica.controller;

import com.mycompany.clinica.execption.ManejadorError;
import com.mycompany.clinica.execption.NegocioException;
import com.mycompany.clinica.execption.TecnicoException;
import com.mycompany.clinica.execption.ValidacionObjeto;
import com.mycompany.clinica.model.entity.Consulta;
import com.mycompany.clinica.model.service.CrudConsulta;
import com.mycompany.clinica.view.gui.PacienteFrame;
import com.mycompany.clinica.view.gui.VistaPaciente;

public class ConsultaController {
    private VistaPaciente<PacienteFrame> vistaPaciente;
    private CrudConsulta consultaService;
    private SignoVitalController signoVitalController;
    
    public ConsultaController(VistaPaciente<PacienteFrame> vistaPaciente, CrudConsulta consultaService,
        SignoVitalController signoVitalController) {
        this.vistaPaciente = vistaPaciente;
        this.consultaService = consultaService;
        this.signoVitalController = signoVitalController;
    }
    
    public boolean validarConsulta(int idPaciente) {
        Consulta consultaData = vistaPaciente.obtenerConsulta(idPaciente);
        return ValidacionObjeto.validarCampos(consultaData);
    }

    public int guardarConsulta(int idPaciente) {
        try {
            Consulta consultaData = vistaPaciente.obtenerConsulta(idPaciente);
            if (!signoVitalController.validarSignosVitales(consultaData.getIdConsulta())) {
                throw new NegocioException("Validación de signos vitales fallida");
            }
            return consultaService.guardar(consultaData, idPaciente);
        } catch (NegocioException e) {
            String mensaje = ManejadorError.obtenerMensajeError(e);
            vistaPaciente.mostrarError(mensaje);
            return -1;
        } catch (TecnicoException e) {
            String mensaje = ManejadorError.obtenerMensajeError(e);
            vistaPaciente.mostrarError(mensaje);
            return -1;
        } catch (Exception e) {
            ManejadorError.obtenerMensajeError(e);
            vistaPaciente.mostrarError("Error inesperado");
            return -1;
        }
    }
    
    public void guardarConsultaYSignoVital(int idPaciente) {
        int idConsulta = guardarConsulta(idPaciente);
        if(idConsulta > 0) {
            signoVitalController.guardarSignoVital(idConsulta);
        } else {
            vistaPaciente.mostrarError("No se a ingresado los datos de la Consulta ");
        }
    }

}
