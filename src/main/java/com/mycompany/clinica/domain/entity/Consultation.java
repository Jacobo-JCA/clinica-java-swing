
package com.mycompany.clinica.domain.entity;

import java.time.LocalDate;

public class Consultation {
    private int consultationId;
    private String reasonConsultation;
    private LocalDate consultationDate;
    private String diagnosis;
    private String prescription;
    private String instructions;
    private VitalSigns vitalSigns;
    
    public Consultation(String reasonConsultation, LocalDate consultationDate, String diagnosis, String prescription, String instructions, 
            VitalSigns vitalSigns) {
        this.reasonConsultation = reasonConsultation;
        this.consultationDate = consultationDate;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.instructions = instructions;
        this.vitalSigns = vitalSigns;
    }
    
    public Consultation(String diagnosis, String prescription, String instructions, LocalDate consultationDate) {
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.instructions = instructions;
        this.consultationDate = consultationDate;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }

    public String getReasonConsultation() {
        return reasonConsultation;
    }

    public void setReasonConsultation(String reasonConsultation) {
        this.reasonConsultation = reasonConsultation;
    }

    public LocalDate getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(LocalDate consultationDate) {
        this.consultationDate = consultationDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public VitalSigns getVitalSigns() {
        return vitalSigns;
    }

    public void setVitalSigns(VitalSigns vitalSigns) {
        this.vitalSigns = vitalSigns;
    }
}
