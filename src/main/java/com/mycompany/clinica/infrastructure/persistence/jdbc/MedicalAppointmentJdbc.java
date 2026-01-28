package com.mycompany.clinica.infrastructure.persistence.jdbc;

import com.mycompany.clinica.domain.entity.MedicalAppointment;
import com.mycompany.clinica.domain.repo.MedicalAppointmentRepo;
import com.mycompany.clinica.infrastructure.execption.DatabaseException;
import com.mycompany.clinica.infrastructure.persistence.config.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class MedicalAppointmentJdbc implements MedicalAppointmentRepo {

    @Override
    public int saveAppointment(MedicalAppointment medicalAppointment, int patientId) {
        String sql = "INSERT INTO medical_appointment (appointment_date, appointment_type, patient_id) VALUES (?,?,?)";
        try (PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setTimestamp(1, Timestamp.valueOf(medicalAppointment.getAppointmentDate())); 
            ps.setString(2, medicalAppointment.getAppointmentType()); 
            ps.setInt(3, patientId);
            int affectRow = ps.executeUpdate();
            if (affectRow == 0) {
                throw new SQLException("No se afecto la fila");
            }
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                medicalAppointment.setMedicalAppointmentId(generatedKeys.getInt(1));
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el ID generado");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al insertar la cita médica en base de datos");
        }
    }

    @Override
    public MedicalAppointment getAppointment(int patientId) {
        MedicalAppointment medicalAppointment = null;
        String sql = "SELECT * FROM medical_appointment WHERE patient_id = ?";
        try (PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                medicalAppointment = new MedicalAppointment(
                        rs.getTimestamp("appointment_date").toLocalDateTime(), 
                        rs.getString("appointment_type"));
            }
            return medicalAppointment;
        } catch (SQLException e) {
            throw new DatabaseException("Error al obtener la cita medica por su ID en base de datos");
        }
    }

    @Override
    public List<MedicalAppointment> getAppointmentsByPatient(int patientId) {
        List<MedicalAppointment> appointments = new ArrayList<>();
        String sqlConsulta = "SELECT appointment_date, appointment_type FROM medical_appointment WHERE patient_id = ?";
        try (PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(sqlConsulta)) {
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                appointments.add(new MedicalAppointment(
                        rs.getTimestamp("appointment_date").toLocalDateTime(), 
                        rs.getString("appointment_type")));
            }
            return appointments;
        } catch (SQLException e) {
            throw new DatabaseException("Error al obtener las citas médicas del paciente");
        }
    }

    @Override
    public void updateAppointment(MedicalAppointment appointment) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAppointment(int appointmentId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
