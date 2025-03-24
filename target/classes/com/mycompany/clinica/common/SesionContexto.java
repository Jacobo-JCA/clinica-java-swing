package com.mycompany.clinica.common;

import com.mycompany.clinica.model.entity.Consulta;
import com.mycompany.clinica.model.entity.Enfermedades;
import com.mycompany.clinica.model.entity.Paciente;
import com.mycompany.clinica.model.entity.SignosVitales;

public class SesionContexto {
    private Paciente paciente;
    private Consulta consulta;
    private Enfermedades enfermedad;
    private SignosVitales signosVitales;
    private static SesionContexto instance;
    
    private SesionContexto() {}

    public static SesionContexto getInstance() {
        if (instance == null) {
            instance = new SesionContexto();
        }
        return instance;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Enfermedades getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedades enfermedad) {
        this.enfermedad = enfermedad;
    }

    public SignosVitales getSignosVitales() {
        return signosVitales;
    }

    public void setSignosVitales(SignosVitales signosVitales) {
        this.signosVitales = signosVitales;
    }
}
