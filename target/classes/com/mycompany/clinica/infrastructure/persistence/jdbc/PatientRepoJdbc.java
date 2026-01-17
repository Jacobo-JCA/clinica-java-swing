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
            throw new DatabaseException("Error al insertar el paciente en la base de datos!");
        }
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> listPatients = new ArrayList<>();
        try (PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement("SELECT * FROM patient LIMIT 20")) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int patient_id = rs.getInt("patient_id");
                String dni  = rs.getString("dni ");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String gender = rs.getString("gender");
                int medical_record_number = rs.getInt("medical_record_number");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String phone_number = rs.getString("phone_number");
                java.sql.Date fechaNacimientoSQL = rs.getDate("date_of_birth ");
                LocalDate dateBirth = fechaNacimientoSQL.toLocalDate();
                String occupation = rs.getString("occupation");
                Patient paciente = new Patient(
                        patient_id, 
                        dni, 
                        first_name, 
                        last_name, address, 
                        email, 
                        Period.between(dateBirth, LocalDate.now()).getYears(), 
                        gender,
                        medical_record_number, 
                        city, state, phone_number, 
                        dateBirth, 
                        occupation);
                listPatients.add(paciente);
            }
            return listPatients;
        } catch (SQLException e) {
            throw new DatabaseException("Error al insertar el paciente en la base de datos!");
        }
    }
    
      @Override
    public Patient getPatientByDni(String dni) {
        Patient paciente = null;
        try (PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement("SELECT * FROM paciente WHERE cedula = ?")) {
            pst.setString(1, dni);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int patient_id = rs.getInt("patient_id");
                String dniPatient = rs.getString("dni");
                String first_name = rs.getString("nombre");
                String last_name = rs.getString("apellido");
                String address = rs.getString("direccion");
                String email = rs.getString("email");
                String gender = rs.getString("genero");
                int medical_record_number = rs.getInt("expediente");
                String city = rs.getString("ciudad");
                String state = rs.getString("estado");
                LocalDate dateBirth = rs.getDate("fecha_nacimiento").toLocalDate();
                String phone_number = rs.getString("telefono");
                String occupation = rs.getString("ocupacion");

                paciente = new Patient(patient_id, dniPatient, first_name, last_name, address, email, Period.between(dateBirth, LocalDate.now()).getYears(), gender,
                        medical_record_number, city, state, phone_number, dateBirth, occupation);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al guardar en base de datos");
        }
        return paciente;
    }
    
    @Override
    public Patient getPatientById(int patientId) {
        String sql = "SELECT * FROM paciente WHERE id_paciente = ?";
        Patient paciente = null;
        try (PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idPaciente = rs.getInt("id_paciente");
                String cedula = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                String email = rs.getString("email");
                String genero = rs.getString("genero");
                int expediente = rs.getInt("expediente");
                String ciudad = rs.getString("ciudad");
                String estado = rs.getString("estado");
                LocalDate dateBirth = rs.getDate("fecha_nacimiento").toLocalDate();
                String telefono = rs.getString("telefono");
                String ocupacion = rs.getString("ocupacion");
                paciente = new Patient(idPaciente, cedula, nombre, apellido, direccion, email, Period.between(dateBirth, LocalDate.now()).getYears(), genero,
                        expediente, ciudad, estado, telefono, dateBirth, ocupacion);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al obtener el paciente en la base de datos");
        }
//        obtenerConsultaRelacionada(paciente);
        return paciente;
    }

    @Override
    public List<Patient> getAllPatientByField(String field) {
        List<Patient> listPacientes = new ArrayList<>();
        try (PreparedStatement pst = DatabaseConnection.getInstance().
                prepareStatement("SELECT * FROM paciente WHERE nombre LIKE'%" + field + "%'" + "OR apellido LIKE'%" + field + "%'" + "ORDER BY nombre")) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int idPaciente = rs.getInt("id_paciente");
                String cedula = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                String email = rs.getString("email");
                String genero = rs.getString("genero");
                int expediente = rs.getInt("expediente");
                String ciudad = rs.getString("ciudad");
                String estado = rs.getString("estado");
                java.sql.Date fechaNacimientoSQL = rs.getDate("fecha_nacimiento");
                LocalDate dateBirth = fechaNacimientoSQL.toLocalDate();
                String telefono = rs.getString("telefono");
                String ocupacion = rs.getString("ocupacion");
                Patient paciente = new Patient(idPaciente, cedula, nombre, apellido, direccion, email, Period.between(dateBirth, LocalDate.now()).getYears(), genero,
                        expediente, ciudad, estado, telefono, dateBirth, ocupacion);
                listPacientes.add(paciente);
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
