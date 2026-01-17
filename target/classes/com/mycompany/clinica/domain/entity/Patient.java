package com.mycompany.clinica.domain.entity;

import java.time.LocalDate;
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
    private List<HealthStatus> diseasesList;
    
    public Patient(String dni, String firstName, String lastName, 
            String address, String email, String gender, int medicalRecordNumber, String city, String state, 
            String phoneNumber, LocalDate dateOfBirth, String occupation) {
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.gender = gender;
        this.medicalRecordNumber = medicalRecordNumber;
        this.city = city;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.occupation = occupation;
    }

    public Patient(int patientId, String dni, String firstName, String lastName, String address, String email, int age,
            String gender, int medicalRecordNumber, String city, String state, String phoneNumber,
            LocalDate dateOfBirth, String occupation) {
        this.patientId = patientId;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.medicalRecordNumber = medicalRecordNumber;
        this.city = city;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.occupation = occupation;
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

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(int medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public List<Consultation> getConsultationsList() {
        return consultationsList;
    }

    public void setConsultationsList(List<Consultation> consultationsList) {
        this.consultationsList = consultationsList;
    }

    public List<HealthStatus> getDiseasesList() {
        return diseasesList;
    }

    public void setDiseasesList(List<HealthStatus> diseasesList) {
        this.diseasesList = diseasesList;
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
