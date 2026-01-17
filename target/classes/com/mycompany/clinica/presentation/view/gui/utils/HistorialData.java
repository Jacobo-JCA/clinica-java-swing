package com.mycompany.clinica.presentation.view.gui.utils;

import com.mycompany.clinica.domain.entity.Consultation;
import com.mycompany.clinica.domain.entity.HealthStatus;
import java.util.List;

public class HistorialData {
    private List<Consultation> consultas;
    private List<HealthStatus> enfermedades;

    public List<Consultation> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consultation> consultas) {
        this.consultas = consultas;
    }

    public List<HealthStatus> getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(List<HealthStatus> enfermedades) {
        this.enfermedades = enfermedades;
    }
}
