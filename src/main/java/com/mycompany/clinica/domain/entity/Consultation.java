
package com.mycompany.clinica.domain.entity;

import java.time.LocalDate;

public class Consultation {
    private int consultationId;
    private final String reasonConsultation;
    private final LocalDate consultationDate;
    private final String diagnosis;
    private final String prescription;
    private final String instructions;
    private int medicalAppointmentId;
    private final VitalSigns vitalSigns;
    
    private Consultation(BuilderConsultation builder) {
        this.consultationId = builder.consultationId;
        this.reasonConsultation = builder.reasonConsultation;
        this.consultationDate = builder.consultationDate;
        this.diagnosis = builder.diagnosis;
        this.prescription = builder.prescription;
        this.instructions = builder.instructions;
        this.medicalAppointmentId = builder.medicalAppointmentId;
        this.vitalSigns = builder.vitalSigns; 
    }
    
    public static class BuilderConsultation {
        private int consultationId;
        private String reasonConsultation;
        private LocalDate consultationDate;
        private String diagnosis;
        private String prescription;
        private String instructions;
        private int medicalAppointmentId;
        private VitalSigns vitalSigns;
        
        public BuilderConsultation() {
        }

        public BuilderConsultation consultationId(int consultationId) {
            this.consultationId = consultationId;
            return this;
        }

        public BuilderConsultation reasonConsultation(String reasonConsultation) {
            this.reasonConsultation = reasonConsultation;
            return this;
        }

        public BuilderConsultation consultationDate(LocalDate consultationDate) {
            this.consultationDate = consultationDate;
            return this;
        }

        public BuilderConsultation diagnosis(String diagnosis) {
            this.diagnosis = diagnosis;
            return this;
        }

        public BuilderConsultation prescription(String prescription) {
            this.prescription = prescription;
            return this;
        }

        public BuilderConsultation instructions(String instructions) {
            this.instructions = instructions;
            return this;
        }

        public BuilderConsultation medicalAppointmentId(int medicalAppointmentId) {
            this.medicalAppointmentId = medicalAppointmentId;
            return this;
        }

        public BuilderConsultation vitalSigns(VitalSigns vitalSigns) {
            this.vitalSigns = vitalSigns;
            return this;
        }
        
        public Consultation build() {
            return new Consultation(this);
        }
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

    public LocalDate getConsultationDate() {
        return consultationDate;
    }


    public String getDiagnosis() {
        return diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public String getInstructions() {
        return instructions;
    }

    public int getMedicalAppointmentId() {
        return medicalAppointmentId;
    }

    public void setMedicalAppointmentId(int medicalAppointmentId) {
        this.medicalAppointmentId = medicalAppointmentId;
    }

    public VitalSigns getVitalSigns() {
        return vitalSigns;
    }
}
