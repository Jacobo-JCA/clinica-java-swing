package com.mycompany.clinica.model.entity;

/**
 *
 * @author jacob
 */
public class Enfermedades {
    private int idEnfermedad;
    private String patologico;
    private String noPatologico;
    private String clinico;
    private String quirurjico;
    private String hereditario;

    public Enfermedades() {
        
    }
    
    public Enfermedades(String patologico, String noPatologico, String clinico, String quirurjico, String hereditario) {
        this.patologico = patologico;
        this.noPatologico = noPatologico;
        this.clinico = clinico;
        this.quirurjico = quirurjico;
        this.hereditario = hereditario;
    }
    
    public Enfermedades(int idEnfermedad, String patologico, String noPatologico, String clinico, String quirurjico, String hereditario) {
        this.idEnfermedad = idEnfermedad;
        this.patologico = patologico;
        this.noPatologico = noPatologico;
        this.clinico = clinico;
        this.quirurjico = quirurjico;
        this.hereditario = hereditario;
    }

    public int getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(int idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public String getPatologico() {
        return patologico;
    }

    public void setPatologico(String patologico) {
        this.patologico = patologico;
    }

    public String getNoPatologico() {
        return noPatologico;
    }

    public void setNoPatologico(String noPatologico) {
        this.noPatologico = noPatologico;
    }

    public String getClinico() {
        return clinico;
    }

    public void setClinico(String clinico) {
        this.clinico = clinico;
    }

    public String getQuirurjico() {
        return quirurjico;
    }

    public void setQuirurjico(String quirurjico) {
        this.quirurjico = quirurjico;
    }

    public String getHereditario() {
        return hereditario;
    }

    public void setHereditario(String hereditario) {
        this.hereditario = hereditario;
    }

    @Override
    public String toString() {
        return "%d %s %s %s %s %s".formatted(idEnfermedad, patologico, noPatologico, clinico, quirurjico, hereditario);
    }

}
