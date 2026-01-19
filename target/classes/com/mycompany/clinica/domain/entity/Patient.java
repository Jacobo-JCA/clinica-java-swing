package com.mycompany.clinica.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Patient {
    private int patientId;
    private String dni;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private int age;
    private String gender;
    private int medicalRecordNumber;
    private String city;
    private String state;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String occupation;
    private List<Consultation> consultationsList;
    private List<HealthStatus> healthStatusList;
    
    private Patient(Builder builder) {
        this.patientId = builder.patientId;
        this.dni = builder.dni;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.address = builder.address;
        this.email = builder.email;
        this.age = builder.age;
        this.gender = builder.gender;
        this.medicalRecordNumber = builder.medicalRecordNumber;
        this.city = builder.city;
        this.state = builder.state;
        this.phoneNumber = builder.phoneNumber;
        this.dateOfBirth = builder.dateOfBirth;
        this.occupation = builder.occupation;
        // listas: inicialización segura
        this.consultationsList = new ArrayList<>(builder.consultationsList);
        this.healthStatusList = new ArrayList<>(builder.healthStatusList);
    }
    
    public static class Builder {
        private int patientId;
        private String dni;
        private String firstName;
        private String lastName;
        private String address;
        private String email;
        private int age;
        private String gender;
        private int medicalRecordNumber;
        private String city;
        private String state;
        private String phoneNumber;
        private LocalDate dateOfBirth;
        private String occupation;
        private List<Consultation> consultationsList = new ArrayList<>();
        private List<HealthStatus> healthStatusList = new ArrayList<>();
        
        public Builder() {
        }

        public Builder patientId(int patientId) {
            this.patientId = patientId;
            return this;
        }

        public Builder dni(String dni) {
            this.dni = dni;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder medicalRecordNumber(int medicalRecordNumber) {
            this.medicalRecordNumber = medicalRecordNumber;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder occupation(String occupation) {
            this.occupation = occupation;
            return this;
        }

        public Builder addConsultation(Consultation consultation) {
            this.consultationsList.add(consultation);
            return this;
        }

        public Builder addHealthStatus(HealthStatus healthStatus) {
            this.healthStatusList.add(healthStatus);
            return this;
        }
        
        public Patient build() {
            return new Patient(this);
        }
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
 
    public String getDni() {
        return dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public int getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getOccupation() {
        return occupation;
    }

    public List<Consultation> getConsultationsList() {
        return Collections.unmodifiableList(consultationsList);
    }

    public List<HealthStatus> getHealthStatusList() {
        return Collections.unmodifiableList(healthStatusList);
    }

    @Override
    public String toString() {
        return "Patient{" + "patientId=" + patientId + ", dni=" + dni 
                + ", firstName=" + firstName + ", lastName=" + lastName 
                + ", address=" + address + ", email=" + email + ", age=" + age 
                + ", gender=" + gender + ", medicalRecordNumber=" + medicalRecordNumber 
                + ", city=" + city + ", state=" + state + ", phoneNumber=" + phoneNumber 
                + ", dateOfBirth=" + dateOfBirth + ", occupation=" + occupation + '}';
    }
}