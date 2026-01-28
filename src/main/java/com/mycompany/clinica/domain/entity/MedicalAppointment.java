package com.mycompany.clinica.domain.entity;

import java.time.LocalDateTime;

public class MedicalAppointment {
    private int medicalAppointmentId;
    private LocalDateTime appointmentDate;
    private String appointmentType;
    private Consultation consultation;
    private VitalSigns vitalSigns;
    private HealthStatus healthStatus;
    private int patientId;

    public MedicalAppointment(LocalDateTime appointmentDate, String appointmentType) {
        this.appointmentDate = appointmentDate;
        this.appointmentType = appointmentType;
    }

    public int getMedicalAppointmentId() {
        return medicalAppointmentId;
    }

    public void setMedicalAppointmentId(int medicalAppoinmentId) {
        this.medicalAppointmentId = medicalAppoinmentId;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appoinmentDate) {
        this.appointmentDate = appoinmentDate;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public VitalSigns getVitalSigns() {
        return vitalSigns;
    }

    public void setVitalSigns(VitalSigns vitalSigns) {
        this.vitalSigns = vitalSigns;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "MedicalAppointment{" + "medicalAppointmentId=" + medicalAppointmentId + ", appointmentDate=" + 
                appointmentDate + ", appointmentType=" + appointmentType + ", patientId=" + patientId + '}';
    }
}
