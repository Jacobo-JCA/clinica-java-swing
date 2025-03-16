package com.mycompany.clinica.factory;

import com.mycompany.clinica.controller.SignoVitalController;
import com.mycompany.clinica.model.service.CrudSignosVitales;
import com.mycompany.clinica.model.service.impl.SignosVitalesService;
import com.mycompany.clinica.view.gui.PacienteFrame;
import com.mycompany.clinica.view.gui.VistaPaciente;

public class SignoVitalFactory {
    public static SignoVitalController createSignoVitalController() {
        CrudSignosVitales crudSignoVital = new SignosVitalesService();
        VistaPaciente<PacienteFrame> vistaFrame = new PacienteFrame();
        return new SignoVitalController(crudSignoVital, vistaFrame);
    }
}
