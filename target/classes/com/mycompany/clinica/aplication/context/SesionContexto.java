package com.mycompany.clinica.aplication.context;

import com.mycompany.clinica.domain.entity.Consultation;
import com.mycompany.clinica.domain.entity.HealthStatus;
import com.mycompany.clinica.domain.entity.Patient;
import com.mycompany.clinica.domain.entity.VitalSigns;

public class SesionContexto {
    private Patient paciente;
    private Consultation consulta;
    private HealthStatus enfermedad;
    private VitalSigns signosVitales;
    private static SesionContexto instance;
    
    private SesionContexto() {}

    public static SesionContexto getInstance() {
        if (instance == null) {
            instance = new SesionContexto();
        }
        return instance;
    }

    public Patient getPaciente() {
        return paciente;
    }

    public void setPaciente(Patient paciente) {
        this.paciente = paciente;
    }

    public Consultation getConsulta() {
        return consulta;
    }

    public void setConsulta(Consultation consulta) {
        this.consulta = consulta;
    }

    public HealthStatus getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(HealthStatus enfermedad) {
        this.enfermedad = enfermedad;
    }

    public VitalSigns getSignosVitales() {
        return signosVitales;
    }

    public void setSignosVitales(VitalSigns signosVitales) {
        this.signosVitales = signosVitales;
    }
}
