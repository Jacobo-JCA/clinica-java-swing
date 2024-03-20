package com.mycompany.clinica.model;

/**
 *
 * @author jacob
 */
public class Patologico {
    private int idPatologico;
    private String descripcion;

    public Patologico(int idPatologico, String descripcion) {
        this.idPatologico = idPatologico;
        this.descripcion = descripcion;
    }

    public int getIdPatologico() {
        return idPatologico;
    }

    public void setIdPatologico(int idPatologico) {
        this.idPatologico = idPatologico;
    }
    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "idPatologico=" + idPatologico + ", descripcion=" + descripcion;
    }
    
}
