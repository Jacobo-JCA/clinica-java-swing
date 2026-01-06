package com.mycompany.clinica.aplication.context;

import com.mycompany.clinica.domain.entity.Consultation;
import com.mycompany.clinica.domain.entity.Enfermedades;
import com.mycompany.clinica.domain.entity.Paciente;
import com.mycompany.clinica.domain.entity.VitalSigns;

public class SesionContexto {
    private Paciente paciente;
    private Consultation consulta;
    private Enfermedades enfermedad;
    private VitalSigns signosVitales;
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

    public Consultation getConsulta() {
        return consulta;
    }

    public void setConsulta(Consultation consulta) {
        this.consulta = consulta;
    }

    public Enfermedades getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedades enfermedad) {
        this.enfermedad = enfermedad;
    }

    public VitalSigns getSignosVitales() {
        return signosVitales;
    }

    public void setSignosVitales(VitalSigns signosVitales) {
        this.signosVitales = signosVitales;
    }
}
