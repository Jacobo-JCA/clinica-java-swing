package com.mycompany.clinica.controller;

import com.mycompany.clinica.execption.ValidacionObjeto;
import com.mycompany.clinica.execption.ValidacionException;
import com.mycompany.clinica.model.entity.SignosVitales;
import com.mycompany.clinica.model.service.CrudSignosVitales;
import com.mycompany.clinica.view.gui.PacienteFrame;
import com.mycompany.clinica.view.gui.VistaPaciente;

public class SignoVitalController {
    private final CrudSignosVitales signoVitalService;
    private VistaPaciente<PacienteFrame> vistaPaciente;
    
    public SignoVitalController(CrudSignosVitales signoVitalService, VistaPaciente<PacienteFrame> vistaPaciente) {
        this.signoVitalService = signoVitalService;
        this.vistaPaciente = vistaPaciente;
    }
    
    private String calcularImc(double peso, double talla) {
        double imcValue = peso / (talla * talla);

        if (imcValue < 18.5) {
            return "Peso bajo";
        } else if (imcValue >= 18.5 && imcValue < 25) {
            return "Normal";
        } else if (imcValue >= 25 && imcValue < 30) {
            return "Sobrepeso";
        }
        return "Obesidad";
    }
    
    public SignosVitales convertirFormularioASignoVital(String presionArterial, String frecuenciaCardiaca, 
                                                  String frecuenciaRespiratoria, String temperatura, 
                                                  String peso, String talla, String descripcion) throws NumberFormatException {
        double pesoFormat = Double.parseDouble(peso);
        double tallaFormat = Double.parseDouble(talla);
        String imc = calcularImc(pesoFormat, tallaFormat);
        return new SignosVitales(
            presionArterial,
            frecuenciaCardiaca,
            frecuenciaRespiratoria,
            temperatura,
            pesoFormat,
            tallaFormat,
            descripcion,
            imc
        );
    }
    
    public boolean validarSignosVitales(int idConsulta) {
        SignosVitales signoVital = vistaPaciente.obtenerCamposSignoVital(idConsulta);
        return ValidacionObjeto.validarCampos(signoVital);
    }
    
    public void guardarSignoVital(int idConsulta) {
        try {
            SignosVitales signoVital = vistaPaciente.obtenerCamposSignoVital(idConsulta);
            signoVitalService.guardar(signoVital, idConsulta);
        } catch (NumberFormatException e) {
            vistaPaciente.mostrarError("Error en los campos: " + e.getMessage());
        }
    }
}
