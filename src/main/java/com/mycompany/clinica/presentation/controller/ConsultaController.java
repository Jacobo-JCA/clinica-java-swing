package com.mycompany.clinica.presentation.controller;

import com.mycompany.clinica.domain.entity.Consultation;
import com.mycompany.clinica.aplication.context.SesionContexto;
import com.mycompany.clinica.infrastructure.execption.NegocioException;
import com.mycompany.clinica.aplication.service.CrudConsulta;
import com.mycompany.clinica.presentation.view.gui.ConsultaFrame;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JDialog;

public class ConsultaController {
    private final CrudConsulta consultaService;
    private final SesionContexto sesionContext;
    private final ConsultaFrame consultaFrame;
    
    public ConsultaController(CrudConsulta consultaService, SesionContexto sesionContext) {
        this.consultaService = consultaService;
        this.sesionContext = sesionContext;
        this.consultaFrame = new ConsultaFrame(null, true, this);
    }
    
    public void mostrarVentana() {
        consultaFrame.setLocationRelativeTo(null);
        consultaFrame.setVisible(true);
        consultaFrame.setLocation(600, 150);
        consultaFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
    public Consultation convertirFormularioAEntidad(List<String> campos) {
        LocalDate fecha = LocalDate.parse(campos.get(1));
        return new Consultation(campos.get(0), fecha, campos.get(2), campos.get(3), campos.get(4));
    }
    
    public void guardarConsulta(Consultation consulta) {
        int idPaciente = sesionContext.getPaciente().getIdPaciente();
        if(idPaciente < 0) {
            throw new NegocioException("ID de Paciente no existe...");
        }
        if(consulta == null) {
            throw new NegocioException("Consulta no existe...");
        }
        consultaService.guardar(consulta, idPaciente);
    }
    
    public List<Consultation> obtenerConsultas(int idPaciente) {
        return consultaService.obtenerConsultas(idPaciente);
    }

    public SesionContexto getSesionContext() {
        return sesionContext;
    }
}
