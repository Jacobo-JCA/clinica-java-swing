package com.mycompany.clinica.config;

import com.mycompany.clinica.controller.ConsultaController;
import com.mycompany.clinica.controller.PacienteController;
import com.mycompany.clinica.controller.SignoVitalController;

import com.mycompany.clinica.model.service.impl.ConsultaService;
import com.mycompany.clinica.model.service.impl.PacienteService;
import com.mycompany.clinica.model.service.impl.SignosVitalesService;
import com.mycompany.clinica.view.gui.PacienteFrame;
import com.mycompany.clinica.view.gui.VistaPaciente;

public class ControllerFactory {
    private static ControllerFactory controller;
    private final PacienteService pacienteService = new PacienteService();
    private final ConsultaService consultaService = new ConsultaService();
    private final SignosVitalesService signosVitalesService = new SignosVitalesService();
    private VistaPaciente<PacienteFrame> vistaPaciente;
    
    private ControllerFactory() {
    }
    
    public static ControllerFactory getInstance() {
        if(controller == null) {
            controller = new ControllerFactory();
        }
        return controller;
    }
    
    public VistaPaciente<PacienteFrame> getVistaPaciente() {
        if(vistaPaciente == null) {
            vistaPaciente = new PacienteFrame();
        }
        return vistaPaciente;
    }
    
    public SignoVitalController crearSignoVitalController() {
        return new SignoVitalController(signosVitalesService, getVistaPaciente());
    }
    
    public ConsultaController crearConsultaController() {
        return new ConsultaController(getVistaPaciente(), consultaService, crearSignoVitalController());
    }
    
    public PacienteController crearPacienteController() {
        SignoVitalController signoVitalController = new SignoVitalController(signosVitalesService, vistaPaciente);
        ConsultaController consultaController = new ConsultaController(vistaPaciente, consultaService, signoVitalController);  
        return new PacienteController(pacienteService, getVistaPaciente(), consultaController);
    }
}
