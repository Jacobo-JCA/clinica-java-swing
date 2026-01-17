package com.mycompany.clinica.presentation.controller;

import com.mycompany.clinica.aplication.context.SesionContexto;

import com.mycompany.clinica.domain.entity.VitalSigns;
import com.mycompany.clinica.presentation.view.PatientFrame;
import com.mycompany.clinica.presentation.view.VitalSignsFrame;
import java.util.List;
import javax.swing.JDialog;
import com.mycompany.clinica.domain.service.VitalSignsService;

public class VitalSignsController {
    private final VitalSignsService signoVitalService;
    private final VitalSignsFrame signosFrame;
    private final SesionContexto sesionContexto;
    
    public VitalSignsController(VitalSignsService signoVitalService, PatientFrame vistaPaciente, SesionContexto sesionContexto) {
        this.signoVitalService = signoVitalService;
        this.sesionContexto = sesionContexto;
        this.signosFrame = new VitalSignsFrame(null, true, this, sesionContexto);     
    }
    
    public void mostrarVentana() {
        signosFrame.setLocationRelativeTo(null);
        signosFrame.setVisible(true);
        signosFrame.setLocation(600, 150);
        signosFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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

    public VitalSigns convertirDatosSignoVital(String[] campos) {
        double pesoFormat = Double.parseDouble(campos[4]);
        double tallaFormat = Double.parseDouble(campos[5]);
        String imc = calcularImc(pesoFormat, tallaFormat);
        return new VitalSigns(
            campos[0],
            campos[1],
            campos[2],
            campos[3],
            pesoFormat,
            tallaFormat,
            campos[6],
            imc
        );
    }
    
//    public void guardarSignoVital(VitalSigns signosVitales) {
//        int idConsulta = sesionContexto.();
//        if(idConsulta == 0) {
//            throw new NegocioException("Primero seleccione o guarde una consulta");
//        }
//        signoVitalService.guardar(signosVitales, idConsulta);
//    }
    
    public List<VitalSigns> obtenerSignosVitales(int idConsulta) {
        return signoVitalService.obtenerSignosVitales(idConsulta);
    }
}
