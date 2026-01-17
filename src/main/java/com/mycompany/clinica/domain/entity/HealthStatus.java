package com.mycompany.clinica.domain.entity;

public class HealthStatus {
    private int healthStatusId;
    private String pathological;
    private String noPathological;
    private String clinical;
    private String surgical;
    private String hereditary;
    private int patientId;
    
    public HealthStatus(String pathological, String noPathological, String clinical, String surgical, String hereditary) {
        this.pathological = pathological;
        this.noPathological = noPathological;
        this.clinical = clinical;
        this.surgical = surgical;
        this.hereditary = hereditary;
    }
    
    public HealthStatus(int healthStatusId, String pathological, String noPathological, String clinical, String surgical, String hereditary) {
        this.healthStatusId = healthStatusId;
        this.pathological = pathological;
        this.noPathological = noPathological;
        this.clinical = clinical;
        this.surgical = surgical;
        this.hereditary = hereditary;
    }

    public int getHealthStatusId() {
        return healthStatusId;
    }

    public void setHealthStatusId(int healthStatusId) {
        this.healthStatusId = healthStatusId;
    }

    public String getPathological() {
        return pathological;
    }

    public void setPathological(String pathological) {
        this.pathological = pathological;
    }

    public String getNoPathological() {
        return noPathological;
    }

    public void setNoPathological(String noPathological) {
        this.noPathological = noPathological;
    }

    public String getClinical() {
        return clinical;
    }

    public void setClinical(String clinical) {
        this.clinical = clinical;
    }

    public String getSurgical() {
        return surgical;
    }

    public void setSurgical(String surgical) {
        this.surgical = surgical;
    }

    public String getHereditary() {
        return hereditary;
    }

    public void setHereditary(String hereditary) {
        this.hereditary = hereditary;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "Disease{" + "diseaseId=" + healthStatusId + ", pathological=" + pathological 
                + ", noPathological=" + noPathological + ", clinical=" + clinical 
                + ", surgical=" + surgical + ", hereditary=" + hereditary + ", patientId=" + patientId + '}';
    }
}
