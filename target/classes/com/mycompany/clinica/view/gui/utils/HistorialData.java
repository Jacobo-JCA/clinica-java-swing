package com.mycompany.clinica.view.gui.utils;

import com.mycompany.clinica.model.entity.Consulta;
import com.mycompany.clinica.model.entity.Enfermedades;
import java.util.List;

public class HistorialData {
    private List<Consulta> consultas;
    private List<Enfermedades> enfermedades;

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public List<Enfermedades> getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(List<Enfermedades> enfermedades) {
        this.enfermedades = enfermedades;
    }
}
