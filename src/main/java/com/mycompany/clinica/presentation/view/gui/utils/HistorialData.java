package com.mycompany.clinica.presentation.view.gui.utils;

import com.mycompany.clinica.domain.entity.Consultation;
import com.mycompany.clinica.domain.entity.Enfermedades;
import java.util.List;

public class HistorialData {
    private List<Consultation> consultas;
    private List<Enfermedades> enfermedades;

    public List<Consultation> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consultation> consultas) {
        this.consultas = consultas;
    }

    public List<Enfermedades> getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(List<Enfermedades> enfermedades) {
        this.enfermedades = enfermedades;
    }
}
