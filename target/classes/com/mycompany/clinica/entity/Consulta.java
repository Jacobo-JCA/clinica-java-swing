
package com.mycompany.clinica.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jacob
 */
public class Consulta {
    private int idConsulta;
    private String motivoConsulta;
    private LocalDate fechaConsulta;
    private String diagnostico;
    private String receta;
    private String indicaciones;
    private List<SignosVitales> signosVitales;

    public Consulta() {
    }
    
    public Consulta(String motivoConsulta, LocalDate fechaConsulta, String diagnostico, String receta, String indicaciones) {
        this.motivoConsulta = motivoConsulta;
        this.fechaConsulta = fechaConsulta;
        this.diagnostico = diagnostico;
        this.receta = receta;
        this.indicaciones = indicaciones;
        this.signosVitales = new ArrayList<>();
    }
    
    public Consulta(String diagnostico, String receta, String indicaciones, LocalDate fechaConsulta) {
        this.diagnostico = diagnostico;
        this.receta = receta;
        this.indicaciones = indicaciones;
        this.fechaConsulta = fechaConsulta;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public LocalDate getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(LocalDate fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public List<SignosVitales> getSignosVitales() {
        return signosVitales;
    }

    public void setSignosVitales(List<SignosVitales> signosVitales) {
        this.signosVitales = signosVitales;
    }
}
