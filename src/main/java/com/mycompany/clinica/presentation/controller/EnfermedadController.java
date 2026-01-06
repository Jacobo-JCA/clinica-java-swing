package com.mycompany.clinica.presentation.controller;

import com.mycompany.clinica.aplication.context.SesionContexto;
import com.mycompany.clinica.infrastructure.execption.NegocioException;
import com.mycompany.clinica.domain.entity.Enfermedades;
import com.mycompany.clinica.aplication.service.CrudEnfermedad;
import com.mycompany.clinica.presentation.view.gui.EnfermedadesFrame;

import java.util.List;
import javax.swing.JDialog;

public class EnfermedadController {
    private CrudEnfermedad serviceEnfermedad;
    private EnfermedadesFrame enfermedadesFrame;
    private SesionContexto sesionContexto;

    public EnfermedadController(CrudEnfermedad serviceEnfermedad, SesionContexto sesionContexto) {
        this.serviceEnfermedad = serviceEnfermedad;
        this.sesionContexto = sesionContexto;
        this.enfermedadesFrame = new EnfermedadesFrame(null, true, this, sesionContexto);
    }
    
    public void mostrarVentana() {
        enfermedadesFrame.setLocationRelativeTo(null);
        enfermedadesFrame.setVisible(true);
        enfermedadesFrame.setLocation(600, 150);
        enfermedadesFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
    public Enfermedades convertirDatosEnfermedades(String[] campos) {
        return new Enfermedades(
            campos[0],
            campos[1],
            campos[2],
            campos[3],
            campos[4]
            );
    }

    public void guardarEnfermedad(Enfermedades enfermedades) {
        int idPaciente = sesionContexto.getPaciente().getIdPaciente();
        if(idPaciente == 0) {
            throw new NegocioException("Primero seleccione o guarde un paciente");
        }
        serviceEnfermedad.guardar(enfermedades, idPaciente);
    }
    
    public List<Enfermedades> obtenerEnfermedades(int idPaciente) {
        return serviceEnfermedad.obtenerEnfermedades(idPaciente);
    }
}
