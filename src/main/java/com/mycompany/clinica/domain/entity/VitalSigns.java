
package com.mycompany.clinica.domain.entity;

public class VitalSigns {
    private int vitalSignsId;
    private final String bloodPressure;
    private final String heartRate;
    private final String respiratoryRate;
    private final String bodyTemperature;
    private final double weight;
    private final double height;
    private final String description;
    private final String imc;
    private int medicalAppointmentId;
    
    private VitalSigns(BuilderVitalSigns builder) {
        this.vitalSignsId = builder.vitalSignsId;
        this.bloodPressure = builder.bloodPressure;
        this.heartRate = builder.heartRate;
        this.respiratoryRate = builder.respiratoryRate;
        this.bodyTemperature = builder.bodyTemperature;
        this.weight = builder.weight;
        this.height = builder.height;
        this.description = builder.description;
        this.imc = builder.imc;
        this.medicalAppointmentId = builder.medicalAppointmentId;
    }

    public static class BuilderVitalSigns {
        private int vitalSignsId;
        private String bloodPressure;
        private String heartRate;
        private String respiratoryRate;
        private String bodyTemperature;
        private double weight;
        private double height;
        private String description;
        private String imc;
        private int medicalAppointmentId;
        
        public BuilderVitalSigns() {}

        public BuilderVitalSigns vitalSignsId(int vitalSignsId) {
            this.vitalSignsId = vitalSignsId;
            return this;
        }

        public BuilderVitalSigns bloodPressure(String bloodPressure) {
            this.bloodPressure = bloodPressure;
            return this;
        }

        public BuilderVitalSigns heartRate(String heartRate) {
            this.heartRate = heartRate;
            return this;
        }

        public BuilderVitalSigns respiratoryRate(String respiratoryRate) {
            this.respiratoryRate = respiratoryRate;
            return this;
        }

        public BuilderVitalSigns bodyTemperature(String bodyTemperature) {
            this.bodyTemperature = bodyTemperature;
            return this;
        }

        public BuilderVitalSigns weight(double weight) {
            this.weight = weight;
            return this;
        }

        public BuilderVitalSigns height(double height) {
            this.height = height;
            return this;
        }

        public BuilderVitalSigns description(String description) {
            this.description = description;
            return this;
        }

        public BuilderVitalSigns imc(String imc) {
            this.imc = imc;
            return this;
        }

        public BuilderVitalSigns medicalAppointmentId(int medicalAppointmentId) {
            this.medicalAppointmentId = medicalAppointmentId;
            return this;
        }
        
        public VitalSigns build() {
            return new VitalSigns(this);
        }
    }

    public int getVitalSignsId() {
        return vitalSignsId;
    }

    public void setVitalSignsId(int vitalSignsId) {
        this.vitalSignsId = vitalSignsId;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public String getRespiratoryRate() {
        return respiratoryRate;
    }

    public String getBodyTemperature() {
        return bodyTemperature;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String getDescription() {
        return description;
    }

    public String getImc() {
        return imc;
    }

    public int getMedicalAppointmentId() {
        return medicalAppointmentId;
    }

    public void setMedicalAppointmentId(int medicalAppointmentId) {
        this.medicalAppointmentId = medicalAppointmentId;
    }

    @Override
    public String toString() {
        return "VitalSigns{" + "vitalSignsId=" + vitalSignsId + ", bloodPressure=" 
                + bloodPressure + ", heartRate=" + heartRate + ", respiratoryRate=" 
                + respiratoryRate + ", bodyTemperature=" + bodyTemperature + ", weight=" + weight 
                + ", height=" + height + ", description=" + description + ", imc=" + imc + ", medicalAppointmentId=" 
                + medicalAppointmentId + '}';
    }
}
