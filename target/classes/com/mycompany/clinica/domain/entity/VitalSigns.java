
package com.mycompany.clinica.domain.entity;

public class VitalSigns {
    private int vitalSignsId;
    private String bloodPressure;
    private String heartRate;
    private String respiratoryRate;
    private String bodyTemperature;
    private double weight;
    private double height;
    private String description;
    private String imc;
    private int consultationId;
    
    public VitalSigns(String bloodPressure, String heartRate, String respiratoryRate, 
            String bodyTemperature, double weight, double height, String description, String imc) {
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.respiratoryRate = respiratoryRate;
        this.bodyTemperature = bodyTemperature;
        this.weight = weight;
        this.height = height;
        this.description = description;
        this.imc = imc;
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

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(String respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public String getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(String bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImc() {
        return imc;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }

    @Override
    public String toString() {
        return "VitalSigns{" + "vitalSignsId=" + vitalSignsId + ", bloodPressure=" 
                + bloodPressure + ", heartRate=" + heartRate + ", respiratoryRate=" 
                + respiratoryRate + ", bodyTemperature=" + bodyTemperature + ", weight=" + weight 
                + ", height=" + height + ", description=" + description + ", imc=" + imc + ", consultationId=" 
                + consultationId + '}';
    }
}
