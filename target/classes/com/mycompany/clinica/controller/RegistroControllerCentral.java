package com.mycompany.clinica.controller;

import com.mycompany.clinica.common.SesionContexto;
import com.mycompany.clinica.entity.Consulta;
import com.mycompany.clinica.entity.Enfermedades;
import com.mycompany.clinica.entity.SignosVitales;
import java.util.List;

public class RegistroControllerCentral {
    private final ConsultaController consultaController;
    private final SignoVitalController signoVitalController;
    private final EnfermedadController enfermedadController;
    private final SesionContexto sesionContexto;

    // Facade/Mediator
    public RegistroControllerCentral(ConsultaController consultaController, SignoVitalController signoVitalController,
            EnfermedadController enfermedadController, SesionContexto sesionContexto) {
        this.consultaController = consultaController;
        this.signoVitalController = signoVitalController;
        this.enfermedadController = enfermedadController;
        this.sesionContexto = sesionContexto;
    }

    public void guardarRegistroCompleto() {
        if (sesionContexto.getConsulta() != null) {
            consultaController.guardarConsulta(sesionContexto.getConsulta());
        }
        if (sesionContexto.getSignosVitales() != null) {
            signoVitalController.guardarSignoVital(sesionContexto.getSignosVitales());
        }
        if (sesionContexto.getEnfermedad() != null) {
            enfermedadController.guardarEnfermedad(sesionContexto.getEnfermedad());
        }
    }

    public List<Consulta> obtenerConsultasConSignos(int idPaciente) {
        List<Consulta> consultas = consultaController.obtenerConsultas(idPaciente);
        consultas.forEach(consulta -> {
            List<SignosVitales> signos = signoVitalController.obtenerSignosVitales(consulta.getIdConsulta());
            consulta.setSignosVitales(signos);
        });
        return consultas;
    }

    public List<Enfermedades> obtenerEnfermedadesPaciente(int idPaciente) {
        return enfermedadController.obtenerEnfermedades(idPaciente);
    }

    public SesionContexto getSesionContexto() {
        return sesionContexto;
    }
}
