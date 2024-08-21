
package com.mycompany.clinica.model;

import java.time.LocalDate;

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
    private SignosVitales signosVitales;

    public Consulta() {
    }

    public Consulta(String motivoConsulta, LocalDate fechaConsulta, String diagnostico, String receta, String indicaciones, SignosVitales signosVitales) {
        this.motivoConsulta = motivoConsulta;
        this.fechaConsulta = fechaConsulta;
        this.diagnostico = diagnostico;
        this.receta = receta;
        this.indicaciones = indicaciones;
        this.signosVitales = signosVitales;
    }
    

    public Consulta(String motivoConsulta, LocalDate fechaConsulta, String diagnostico, String receta, String indicaciones) {
        this.motivoConsulta = motivoConsulta;
        this.fechaConsulta = fechaConsulta;
        this.diagnostico = diagnostico;
        this.receta = receta;
        this.indicaciones = indicaciones;
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

    public SignosVitales getSignosVitales() {
        return signosVitales;
    }

    public void setSignosVitales(SignosVitales signosVitales) {
        this.signosVitales = signosVitales;
    }

    
}
