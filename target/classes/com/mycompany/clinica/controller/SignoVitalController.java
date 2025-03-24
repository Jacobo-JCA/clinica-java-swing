package com.mycompany.clinica.controller;

import com.mycompany.clinica.common.SesionContexto;
import com.mycompany.clinica.execption.NegocioException;
import com.mycompany.clinica.model.entity.SignosVitales;
import com.mycompany.clinica.model.service.CrudSignosVitales;
import com.mycompany.clinica.view.gui.PacienteFrame;
import com.mycompany.clinica.view.gui.SignosFrame;
import java.util.List;
import javax.swing.JDialog;

public class SignoVitalController {
    private final CrudSignosVitales signoVitalService;
    private final SignosFrame signosFrame;
    private final SesionContexto sesionContexto;
    
    public SignoVitalController(CrudSignosVitales signoVitalService, PacienteFrame vistaPaciente, SesionContexto sesionContexto) {
        this.signoVitalService = signoVitalService;
        this.sesionContexto = sesionContexto;
        this.signosFrame = new SignosFrame(null, true, this, sesionContexto);     
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

    public SignosVitales convertirDatosSignoVital(String[] campos) {
        double pesoFormat = Double.parseDouble(campos[4]);
        double tallaFormat = Double.parseDouble(campos[5]);
        String imc = calcularImc(pesoFormat, tallaFormat);
        return new SignosVitales(
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
    
    public void guardarSignoVital(SignosVitales signosVitales) {
        int idConsulta = sesionContexto.getConsulta().getIdConsulta();
        if(idConsulta == 0) {
            throw new NegocioException("Primero seleccione o guarde una consulta");
        }
        signoVitalService.guardar(signosVitales, idConsulta);
    }
    
    public List<SignosVitales> obtenerSignosVitales(int idConsulta) {
        return signoVitalService.obtenerSignosVitales(idConsulta);
    }
}
