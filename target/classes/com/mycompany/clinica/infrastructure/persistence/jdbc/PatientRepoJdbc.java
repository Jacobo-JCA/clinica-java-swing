package com.mycompany.clinica.infrastructure.persistence.jdbc;

import com.mycompany.clinica.domain.entity.Patient;
import com.mycompany.clinica.domain.repo.PatientRepo;
import com.mycompany.clinica.infrastructure.execption.DatabaseException;
import com.mycompany.clinica.infrastructure.persistence.config.DatabaseConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class PatientRepoJdbc implements PatientRepo {

    @Override
    public int insertPatient(Patient patient) {
        try (PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement("INSERT INTO patient (dni, first_name, last_name, address, "
                        + "email, gender, medical_record_number, city, state, phone_number, "
                        + "date_of_birth, occupation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, patient.getDni());
            ps.setString(2, patient.getFirstName());
            ps.setString(3, patient.getLastName());
            ps.setString(4, patient.getAddress());
            ps.setString(5, patient.getEmail());
            ps.setString(6, patient.getGender());
            ps.setInt(7, patient.getMedicalRecordNumber());
            ps.setString(8, patient.getCity());
            ps.setString(9, patient.getState());
            ps.setString(10, patient.getPhoneNumber());
            ps.setDate(11, Date.valueOf(patient.getDateOfBirth()));
            ps.setString(12, patient.getOccupation());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new DatabaseException("Error al ingresar el paciente en base de datos: ");
            }
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                patient.setPatientId(generatedKeys.getInt(1));
                return generatedKeys.getInt(1);
            } else {
                throw new DatabaseException("ID no obtenido: ");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al insertar el paciente en la base de datos!" + e);
        }
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> listPatients = new ArrayList<>();
        try (PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement("SELECT * FROM patient LIMIT 20")) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int patientId = rs.getInt("patient_id");
                String dni = rs.getString("dni");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String gender = rs.getString("gender");
                int medicalRecordNumber = rs.getInt("medical_record_number");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String phoneNumber = rs.getString("phone_number");
                java.sql.Date fechaNacimientoSQL = rs.getDate("date_of_birth");
                LocalDate dateBirth = fechaNacimientoSQL.toLocalDate();
                String occupation = rs.getString("occupation");

                Patient patient = new Patient.Builder()
                        .patientId(patientId)
                        .dni(dni)
                        .firstName(firstName)
                        .lastName(lastName)
                        .address(address)
                        .email(email)
                        .age(Period.between(dateBirth, LocalDate.now()).getYears())
                        .gender(gender)
                        .medicalRecordNumber(medicalRecordNumber)
                        .city(city)
                        .state(state)
                        .dateOfBirth(dateBirth)
                        .phoneNumber(phoneNumber)
                        .occupation(occupation)
                        .build();               
                listPatients.add(patient);
            }
            return listPatients;
        } catch (SQLException e) {
            throw new DatabaseException("Error al obtener pacientes en base de datos!");
        }
    }
    
      @Override
    public Patient getPatientByDni(String dni) {
        Patient patient = null;
        try (PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement("SELECT * FROM paciente WHERE cedula = ?")) {
            pst.setString(1, dni);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int patientId = rs.getInt("patient_id");
                String dniPatient = rs.getString("dni");
                String firstName = rs.getString("nombre");
                String lastName = rs.getString("apellido");
                String address = rs.getString("direccion");
                String email = rs.getString("email");
                String gender = rs.getString("genero");
                int medicalRecordNumber = rs.getInt("expediente");
                String city = rs.getString("ciudad");
                String state = rs.getString("estado");
                LocalDate dateBirth = rs.getDate("fecha_nacimiento").toLocalDate();
                String phoneNumber = rs.getString("telefono");
                String occupation = rs.getString("ocupacion");

                patient = new Patient.Builder()
                        .patientId(patientId)
                        .dni(dniPatient)
                        .firstName(firstName)
                        .lastName(lastName)
                        .address(address)
                        .email(email)
                        .age(Period.between(dateBirth, LocalDate.now()).getYears())
                        .gender(gender)
                        .medicalRecordNumber(medicalRecordNumber)
                        .city(city)
                        .state(state)
                        .dateOfBirth(dateBirth)
                        .phoneNumber(phoneNumber)
                        .occupation(occupation)
                        .build();
                
            }
            return patient;
        } catch (SQLException e) {
            throw new DatabaseException("Error al guardar en base de datos");
        }
    }
    
    @Override
    public Patient getPatientById(int patientId) {
        String sql = "SELECT * FROM paciente WHERE id_paciente = ?";
        Patient patient = null;
        try (PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id_paciente");
                String dniPatient = rs.getString("cedula");
                String firstName = rs.getString("nombre");
                String lastName = rs.getString("apellido");
                String address = rs.getString("direccion");
                String email = rs.getString("email");
                String gender = rs.getString("genero");
                int medicalRecordNumber = rs.getInt("expediente");
                String city = rs.getString("ciudad");
                String state = rs.getString("estado");
                LocalDate dateBirth = rs.getDate("fecha_nacimiento").toLocalDate();
                String phoneNumber = rs.getString("telefono");
                String occupation = rs.getString("ocupacion");

                patient = new Patient.Builder()
                        .patientId(id)
                        .dni(dniPatient)
                        .firstName(firstName)
                        .lastName(lastName)
                        .address(address)
                        .email(email)
                        .age(Period.between(dateBirth, LocalDate.now()).getYears())
                        .gender(gender)
                        .medicalRecordNumber(medicalRecordNumber)
                        .city(city)
                        .state(state)
                        .dateOfBirth(dateBirth)
                        .phoneNumber(phoneNumber)
                        .occupation(occupation)
                        .build();
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al obtener el paciente en la base de datos");
        }
//        obtenerConsultaRelacionada(paciente);
        return patient;
    }

    @Override
    public List<Patient> getAllPatientByField(String field) {
        List<Patient> listPacientes = new ArrayList<>();
        try (PreparedStatement pst = DatabaseConnection.getInstance().
                prepareStatement("SELECT * FROM paciente WHERE nombre LIKE'%" + field + "%'" + "OR apellido LIKE'%" + field + "%'" + "ORDER BY nombre")) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int patientId = rs.getInt("id_paciente");
                String dniPatient = rs.getString("cedula");
                String firstName = rs.getString("nombre");
                String lastName = rs.getString("apellido");
                String address = rs.getString("direccion");
                String email = rs.getString("email");
                String gender = rs.getString("genero");
                int medicalRecordNumber = rs.getInt("expediente");
                String city = rs.getString("ciudad");
                String state = rs.getString("estado");
                java.sql.Date fechaNacimientoSQL = rs.getDate("fecha_nacimiento");
                LocalDate dateBirth = fechaNacimientoSQL.toLocalDate();
                String phoneNumber = rs.getString("telefono");
                String occupation = rs.getString("ocupacion");

                Patient patient = new Patient.Builder()
                        .patientId(patientId)
                        .dni(dniPatient)
                        .firstName(firstName)
                        .lastName(lastName)
                        .address(address)
                        .email(email)
                        .age(Period.between(dateBirth, LocalDate.now()).getYears())
                        .gender(gender)
                        .medicalRecordNumber(medicalRecordNumber)
                        .city(city)
                        .state(state)
                        .dateOfBirth(dateBirth)
                        .phoneNumber(phoneNumber)
                        .occupation(occupation)
                        .build();
                listPacientes.add(patient);
            }
        } catch (SQLException s) {
            throw new DatabaseException("Error al obtener los pacientes en base de datos");
        }
        return listPacientes;
    }

    @Override
    public void updatePatient(Patient patient) {
        try (PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement("UPDATE paciente SET nombre = ?, apellido = ?, "
                + "direccion = ?, expediente = ?, ciudad = ?, genero = ?, "
                + "ocupacion=?, estado=?, telefono=?, email = ? WHERE id_paciente = ?")) {
            pst.setString(1, patient.getFirstName());
            pst.setString(2, patient.getLastName());
            pst.setString(3, patient.getAddress());
            pst.setInt(4, patient.getMedicalRecordNumber());
            pst.setString(5, patient.getCity());
            pst.setString(6, patient.getGender());
            pst.setString(7, patient.getOccupation());
            pst.setString(8, patient.getState());
            pst.setString(9, patient.getPhoneNumber());
            pst.setString(10, patient.getEmail());
            pst.setInt(11, patient.getPatientId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DatabaseException("Error al actualizar en base de datos");
        }
    }

    @Override
    public void deletePatient(int patientId) {
        try (PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement("DELETE FROM paciente WHERE id_paciente = ?")) {
            pst.setInt(1, patientId);
            pst.executeUpdate();
        } catch (SQLException s) {
            throw new DatabaseException("Error al eliminar en base de datos");
        }
    }
}
