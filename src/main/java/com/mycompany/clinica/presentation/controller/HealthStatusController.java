package com.mycompany.clinica.presentation.controller;

import com.mycompany.clinica.aplication.context.SesionContexto;
import com.mycompany.clinica.infrastructure.execption.NegocioException;
import com.mycompany.clinica.domain.entity.HealthStatus;
import com.mycompany.clinica.presentation.view.HealthStatusFrame;

import java.util.List;
import javax.swing.JDialog;
import com.mycompany.clinica.domain.service.HealthStatusService;

public class HealthStatusController {
    private HealthStatusService serviceEnfermedad;
    private HealthStatusFrame enfermedadesFrame;
    private SesionContexto sesionContexto;

    public HealthStatusController(HealthStatusService serviceEnfermedad, SesionContexto sesionContexto) {
        this.serviceEnfermedad = serviceEnfermedad;
        this.sesionContexto = sesionContexto;
        this.enfermedadesFrame = new HealthStatusFrame(null, true, this, sesionContexto);
    }
    
    public void mostrarVentana() {
        enfermedadesFrame.setLocationRelativeTo(null);
        enfermedadesFrame.setVisible(true);
        enfermedadesFrame.setLocation(600, 150);
        enfermedadesFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
//    public HealthStatus convertirDatosEnfermedades(String[] campos) {
//        return new HealthStatus(
//            campos[0],
//            campos[1],
//            campos[2],
//            campos[3],
//            campos[4]
//            );
//    }

//    public void guardarEnfermedad(HealthStatus enfermedades) {
//        int idPaciente = sesionContexto.getPaciente().getIdPaciente();
//        if(idPaciente == 0) {
//            throw new NegocioException("Primero seleccione o guarde un paciente");
//        }
//        serviceEnfermedad.guardar(enfermedades, idPaciente);
//    }
//    
//    public List<HealthStatus> obtenerEnfermedades(int idPaciente) {
//        return serviceEnfermedad.obtenerEnfermedades(idPaciente);
//    }
}
