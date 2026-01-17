package com.mycompany.clinica.presentation.view.utils;

import com.mycompany.clinica.aplication.context.SesionContexto;
import com.mycompany.clinica.presentation.controller.ConsultationController;
import com.mycompany.clinica.presentation.controller.HealthStatusController;
import com.mycompany.clinica.presentation.controller.PatientController;
import com.mycompany.clinica.presentation.controller.RegistroControllerCentral;
import com.mycompany.clinica.presentation.controller.VitalSignsController;
import com.mycompany.clinica.aplication.service.ConsultationServiceImpl;
import com.mycompany.clinica.aplication.service.HealthStatusServiceImpl;
import com.mycompany.clinica.aplication.service.PatientServiceImpl;
import com.mycompany.clinica.aplication.service.VitalSignsServiceImpl;
import com.mycompany.clinica.domain.repo.PatientRepo;
import com.mycompany.clinica.presentation.view.PatientFrame;
import com.mycompany.clinica.domain.service.ConsultationService;
import com.mycompany.clinica.domain.service.PatientService;
import com.mycompany.clinica.domain.service.VitalSignsService;
import com.mycompany.clinica.domain.service.HealthStatusService;
import com.mycompany.clinica.infrastructure.persistence.jdbc.PatientRepoJdbc;

public class ControllerFactory {
    private static ControllerFactory controller;
    PatientRepo patientRepo = new PatientRepoJdbc();
    private final PatientService pacienteService = new PatientServiceImpl(patientRepo);
//    private final ConsultationServiceImpl consultaService = new ConsultationServiceImpl();
//    private final HealthStatusServiceImpl crudEnfermedad = new HealthStatusServiceImpl();
//    private final VitalSignsService signosVitalesService = new VitalSignsServiceImpl();
    private PatientFrame patientFrame;
    
    private ControllerFactory() {
    }
    
    public static ControllerFactory getInstance() {
        if(controller == null) {
            controller = new ControllerFactory();
        }
        return controller;
    }
    
    public PatientFrame getPatientFrame() {
        if(patientFrame == null) {
            patientFrame = new PatientFrame();
        }
        return patientFrame;
    }
//    
//    public VitalSignsController crearSignoVitalController() {
//        SesionContexto sesionContexto = SesionContexto.getInstance();
//        return new VitalSignsController(signosVitalesService, getVistaPaciente(), sesionContexto);
//    }
//    
//    public ConsultationController crearConsultaController() {
//        SesionContexto sesionContexto = SesionContexto.getInstance();
//        return new ConsultationController(consultaService, sesionContexto);
//    }
//    
//    public HealthStatusController crearEnfermedadController() {
//        SesionContexto sesionContexto = SesionContexto.getInstance();
//        return new HealthStatusController(crudEnfermedad, sesionContexto);
//    }
//    
//    public RegistroControllerCentral crearRegistroControllerCentral() {
//        SesionContexto sesionContexto = SesionContexto.getInstance();
//        return new RegistroControllerCentral(crearConsultaController(), crearSignoVitalController(), crearEnfermedadController(), sesionContexto);
//    }
//    
    public PatientController getPatienteController() {
        SesionContexto sesionContexto = SesionContexto.getInstance();
        return new PatientController(pacienteService, patientFrame);
    }
}
