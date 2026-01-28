package com.mycompany.clinica.presentation.view.utils;

import com.mycompany.clinica.aplication.usecase.MedicalAppoinmentServiceImpl;
import com.mycompany.clinica.aplication.usecase.PatientServiceImpl;
import com.mycompany.clinica.domain.repo.MedicalAppointmentRepo;
import com.mycompany.clinica.domain.repo.PatientRepo;
import com.mycompany.clinica.domain.service.MedicalAppointmentService;
import com.mycompany.clinica.domain.service.PatientService;
import com.mycompany.clinica.infrastructure.persistence.jdbc.MedicalAppointmentJdbc;
import com.mycompany.clinica.infrastructure.persistence.jdbc.PatientRepoJdbc;
import com.mycompany.clinica.presentation.controller.MedicalAppointmentController;
import com.mycompany.clinica.presentation.controller.PatientController;
import com.mycompany.clinica.presentation.view.viewfx.MedicalAppointmentViewFX;
import com.mycompany.clinica.presentation.view.viewfx.PatientViewFX;

public class ControllerFactory {
    private static ControllerFactory instance;
    private final PatientRepo patientRepo = new PatientRepoJdbc();
    private final PatientService patientService = new PatientServiceImpl(patientRepo);
    private final MedicalAppointmentRepo medicalAppointmentRepo = new MedicalAppointmentJdbc();
    private final MedicalAppointmentService appointmentService = new MedicalAppoinmentServiceImpl(medicalAppointmentRepo);

    private ControllerFactory() {}

    public static ControllerFactory getInstance() {
        if (instance == null) {
            instance = new ControllerFactory();
        }
        return instance;
    }

    public PatientController getPatienteController(PatientViewFX patientViewFX) {
        return new PatientController(patientService, patientViewFX);
    }

    public MedicalAppointmentService getAppointmentService() {
        return appointmentService;
    }
}



