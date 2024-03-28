package com.mycompany.clinica.model;

/**
 *
 * @author jacob
 */
public class Enfermedades {
    private int idEnfermedad;
    private String enfermedad;
    private String descripcion;
    private int idPaciente;

    public Enfermedades(String enfermedad, String descripcion) {
        this.enfermedad = enfermedad;
        this.descripcion = descripcion;
    }

    public int getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(int idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }
    

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    
    
}
