package com.mycompany.clinica.common;

import com.mycompany.clinica.model.entity.Consulta;
import com.mycompany.clinica.model.entity.Enfermedades;
import com.mycompany.clinica.model.entity.Paciente;
import com.mycompany.clinica.model.entity.SignosVitales;

public class SesionContext {
    private static Paciente paciente;
    private static Consulta consulta;
    private static Enfermedades enfermedad;
    private static SignosVitales signosVitales;

    public static Paciente getPaciente() {
        return paciente;
    }

    public static void setPaciente(Paciente paciente) {
        SesionContext.paciente = paciente;
    }

    public static Consulta getConsulta() {
        return consulta;
    }

    public static void setConsulta(Consulta consulta) {
        SesionContext.consulta = consulta;
    }

    public static Enfermedades getEnfermedad() {
        return enfermedad;
    }

    public static void setEnfermedad(Enfermedades enfermedad) {
        SesionContext.enfermedad = enfermedad;
    }

    public static SignosVitales getSignosVitales() {
        return signosVitales;
    }

    public static void setSignosVitales(SignosVitales signosVitales) {
        SesionContext.signosVitales = signosVitales;
    }
    
    
}
