package com.mycompany.clinica.config;

import com.mycompany.clinica.model.service.impl.ConsultaService;
import com.mycompany.clinica.model.service.impl.PacienteService;
import com.mycompany.clinica.model.service.impl.SignosVitalesService;
import com.mycompany.clinica.view.gui.PacienteFrame;
import com.mycompany.clinica.view.gui.VistaPaciente;

public class ProvedorInstacia {
    private static final VistaPaciente<PacienteFrame> vistaPaciente = new PacienteFrame();
    private static final PacienteService pacienteService = new PacienteService();
    private static final ConsultaService consultaService = new ConsultaService();
    private static final SignosVitalesService signosVitalesService = new SignosVitalesService();

    public static VistaPaciente<PacienteFrame> getVistaPaciente() {
        return vistaPaciente;
    }

    public static PacienteService getPacienteService() {
        return pacienteService;
    }

    public static ConsultaService getConsultaService() {
        return consultaService;
    }

    public static SignosVitalesService getSignosVitalesService() {
        return signosVitalesService;
    }
}
