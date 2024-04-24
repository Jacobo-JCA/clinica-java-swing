
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

    public Consulta(String motivoConsulta, LocalDate fechaConsulta, String diagnostico, String receta) {
        this.motivoConsulta = motivoConsulta;
        this.fechaConsulta = fechaConsulta;
        this.diagnostico = diagnostico;
        this.receta = receta;
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
    
    
}
