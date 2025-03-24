package com.mycompany.clinica.view.gui.utils;

import com.mycompany.clinica.common.SesionContexto;
import com.mycompany.clinica.controller.ConsultaController;
import com.mycompany.clinica.controller.EnfermedadController;
import com.mycompany.clinica.controller.PacienteController;
import com.mycompany.clinica.controller.RegistroControllerCentral;
import com.mycompany.clinica.controller.SignoVitalController;
import com.mycompany.clinica.model.service.CrudConsulta;
import com.mycompany.clinica.model.service.CrudEnfermedad;
import com.mycompany.clinica.model.service.CrudPaciente;
import com.mycompany.clinica.model.service.CrudSignosVitales;

import com.mycompany.clinica.model.service.impl.ConsultaService;
import com.mycompany.clinica.model.service.impl.EnfermedadService;
import com.mycompany.clinica.model.service.impl.PacienteService;
import com.mycompany.clinica.model.service.impl.SignosVitalesService;
import com.mycompany.clinica.view.gui.PacienteFrame;

public class ControllerFactory {
    private static ControllerFactory controller;
    private final CrudPaciente pacienteService = new PacienteService();
    private final CrudConsulta consultaService = new ConsultaService();
    private final CrudEnfermedad crudEnfermedad = new EnfermedadService();
    private final CrudSignosVitales signosVitalesService = new SignosVitalesService();
    private PacienteFrame vistaPaciente;
    
    private ControllerFactory() {
    }
    
    public static ControllerFactory getInstance() {
        if(controller == null) {
            controller = new ControllerFactory();
        }
        return controller;
    }
    
    public PacienteFrame getVistaPaciente() {
        if(vistaPaciente == null) {
            vistaPaciente = new PacienteFrame();
        }
        return vistaPaciente;
    }
    
    public SignoVitalController crearSignoVitalController() {
        SesionContexto sesionContexto = SesionContexto.getInstance();
        return new SignoVitalController(signosVitalesService, getVistaPaciente(), sesionContexto);
    }
    
    public ConsultaController crearConsultaController() {
        SesionContexto sesionContexto = SesionContexto.getInstance();
        return new ConsultaController(consultaService, sesionContexto);
    }
    
    public EnfermedadController crearEnfermedadController() {
        SesionContexto sesionContexto = SesionContexto.getInstance();
        return new EnfermedadController(crudEnfermedad, sesionContexto);
    }
    
    public RegistroControllerCentral crearRegistroControllerCentral() {
        SesionContexto sesionContexto = SesionContexto.getInstance();
        return new RegistroControllerCentral(crearConsultaController(), crearSignoVitalController(), crearEnfermedadController(), sesionContexto);
    }
    
    public PacienteController crearPacienteController() {
        SesionContexto sesionContexto = SesionContexto.getInstance();
        RegistroControllerCentral registroCentral = new RegistroControllerCentral(crearConsultaController(), 
                crearSignoVitalController(), crearEnfermedadController(), sesionContexto);
        return new PacienteController(pacienteService, getVistaPaciente(), registroCentral, sesionContexto);
    }
}
