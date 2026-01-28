package com.mycompany.clinica.domain.entity;

public class HealthStatus {
    private int healthStatusId;
    private final String pathological;
    private final String noPathological;
    private final String clinical;
    private final String surgical;
    private final String hereditary;
    private int medicalAppointmentId;
    
    private HealthStatus(BuilderHealthStatus builder) {
        this.healthStatusId = builder.healthStatusId;
        this.pathological = builder.pathological;
        this.noPathological = builder.noPathological;
        this.clinical = builder.clinical;
        this.surgical = builder.surgical;
        this.hereditary = builder.hereditary;
        this.medicalAppointmentId = builder.medicalAppointmentId;
    }
    
    public static class BuilderHealthStatus {
        private int healthStatusId;
        private String pathological;
        private String noPathological;
        private String clinical;
        private String surgical;
        private String hereditary;
        private int medicalAppointmentId;

        public BuilderHealthStatus() {
        }

        public BuilderHealthStatus healthStatusId(int healthStatusId) {
            this.healthStatusId = healthStatusId;
            return this;
        }

        public BuilderHealthStatus pathological(String pathological) {
            this.pathological = pathological;
            return this;
        }

        public BuilderHealthStatus noPathological(String noPathological) {
            this.noPathological = noPathological;
            return this;
        }

        public BuilderHealthStatus clinical(String clinical) {
            this.clinical = clinical;
            return this;
        }

        public BuilderHealthStatus surgical(String surgical) {
            this.surgical = surgical;
            return this;
        }

        public BuilderHealthStatus hereditary(String hereditary) {
            this.hereditary = hereditary;
            return this;
        }

        public BuilderHealthStatus medicalAppointmentId(int medicalAppointmentId) {
            this.medicalAppointmentId = medicalAppointmentId;
            return this;
        }
        
        public HealthStatus build() {
            return new HealthStatus(this);
        }
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

    public String getNoPathological() {
        return noPathological;
    }

    public String getClinical() {
        return clinical;
    }

    public String getSurgical() {
        return surgical;
    }

    public String getHereditary() {
        return hereditary;
    }

    public int getMedicalAppointmentId() {
        return medicalAppointmentId;
    }

    public void setmedicalAppointmentId(int medicalAppointmentId) {
        this.medicalAppointmentId = medicalAppointmentId;
    }

    @Override
    public String toString() {
        return "Disease{" + "diseaseId=" + healthStatusId + ", pathological=" + pathological 
                + ", noPathological=" + noPathological + ", clinical=" + clinical 
                + ", surgical=" + surgical + ", hereditary=" + hereditary + ", medicalAppointmentId=" + medicalAppointmentId + '}';
    }
}
