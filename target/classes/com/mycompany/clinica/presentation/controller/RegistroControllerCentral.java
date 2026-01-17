package com.mycompany.clinica.presentation.controller;

import com.mycompany.clinica.aplication.context.SesionContexto;
import com.mycompany.clinica.domain.entity.Consultation;
import com.mycompany.clinica.domain.entity.HealthStatus;
import com.mycompany.clinica.domain.entity.VitalSigns;
import java.util.List;

public class RegistroControllerCentral {
    private final ConsultationController consultaController;
    private final VitalSignsController signoVitalController;
    private final HealthStatusController enfermedadController;
    private final SesionContexto sesionContexto;

    // Facade/Mediator
    public RegistroControllerCentral(ConsultationController consultaController, VitalSignsController signoVitalController,
            HealthStatusController enfermedadController, SesionContexto sesionContexto) {
        this.consultaController = consultaController;
        this.signoVitalController = signoVitalController;
        this.enfermedadController = enfermedadController;
        this.sesionContexto = sesionContexto;
    }

//    public void guardarRegistroCompleto() {
//        if (sesionContexto.getConsulta() != null) {
//            consultaController.guardarConsulta(sesionContexto.getConsulta());
//        }
//        if (sesionContexto.getSignosVitales() != null) {
//            signoVitalController.guardarSignoVital(sesionContexto.getSignosVitales());
//        }
//        if (sesionContexto.getEnfermedad() != null) {
//            enfermedadController.guardarEnfermedad(sesionContexto.getEnfermedad());
//        }
//    }

//    public List<Consultation> obtenerConsultasConSignos(int idPaciente) {
//        List<Consultation> consultas = consultaController.obtenerConsultas(idPaciente);
//        consultas.forEach(consulta -> {
//            List<VitalSigns> signos = signoVitalController.obtenerSignosVitales(consulta.getIdConsulta());
//            consulta.setSignosVitales(signos);
//        });
//        return consultas;
//    }
//
//    public List<HealthStatus> obtenerEnfermedadesPaciente(int idPaciente) {
//        return enfermedadController.obtenerEnfermedades(idPaciente);
//    }

    public SesionContexto getSesionContexto() {
        return sesionContexto;
    }
}
