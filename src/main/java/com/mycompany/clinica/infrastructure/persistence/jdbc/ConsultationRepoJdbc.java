package com.mycompany.clinica.infrastructure.persistence.jdbc;

import com.mycompany.clinica.domain.entity.Consultation;
import com.mycompany.clinica.domain.repo.ConsultationRepo;
import com.mycompany.clinica.infrastructure.execption.DatabaseException;
import com.mycompany.clinica.infrastructure.persistence.config.DatabaseConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConsultationRepoJdbc implements ConsultationRepo {

    @Override
    public int insertConsultation(Consultation consultation, int medicalAppointmentId) {
        try (PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement("INSERT INTO consultation(reason_consultation, "
                + "consultation_date, diagnosis, prescription, instructions, medical_appointmentId) " 
                + "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, consultation.getReasonConsultation()); 
            pst.setDate(2, Date.valueOf(consultation.getConsultationDate())); 
            pst.setString(3, consultation.getDiagnosis()); 
            pst.setString(4, consultation.getPrescription()); 
            pst.setString(5, consultation.getInstructions()); 
            pst.setInt(6, consultation.getMedicalAppointmentId());
            int affectRow = pst.executeUpdate();
            if (affectRow == 0) {
                throw new SQLException("No se afecto la fila");
            }
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                consultation.setConsultationId(generatedKeys.getInt(1));
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el ID generado");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al insertar la consulta en base de datos");
        }
    }

    @Override
    public List<Consultation> getConsultationsByPatient(int medicalAppointmentId) {
        List<Consultation> consultations = new ArrayList<>();
        String sqlConsulta = "SELECT diagnosis, prescription, instructions, consultation_date FROM consultation WHERE medical_appointmentId = ?";
        try (PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(sqlConsulta)) {
            ps.setInt(1, medicalAppointmentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                consultations.add(new Consultation.BuilderConsultation()
                .diagnosis(rs.getString("diagnosis"))
                .prescription(rs.getString("prescription"))
                .instructions(rs.getString("instructions"))
                .consultationDate(rs.getDate("consultation_date").toLocalDate())
                .build());
            }
            return consultations;
        } catch (SQLException e) {
            throw new DatabaseException("Error al obtener las consultas del paciente");
        }
    } 
}
