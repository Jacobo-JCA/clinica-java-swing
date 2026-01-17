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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultationRepoJdbc implements ConsultationRepo {

    @Override
    public int insertConsultation(Consultation consultation, int patientId) {
        try (PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement("INSERT INTO consultation(reason_consultation, "
                + "consultation_date, diagnosis, prescription, instructions, patient_id) " 
                + "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, consultation.getReasonConsultation()); 
            pst.setDate(2, Date.valueOf(consultation.getConsultationDate())); 
            pst.setString(3, consultation.getDiagnosis()); 
            pst.setString(4, consultation.getPrescription()); 
            pst.setString(5, consultation.getInstructions()); 
            pst.setInt(6, consultation.getPatientId());
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
    public List<Consultation> getConsultationsByPatient(int patientId) {
        List<Consultation> consultations = new ArrayList<>();
        String sqlConsulta = "SELECT diagnostico, receta, indicaciones, fecha_consulta FROM consulta WHERE id_paciente = ?";
        try (PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(sqlConsulta)) {
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                consultations.add(
                        new Consultation(
                                rs.getString("diagnostico"),
                                rs.getString("receta"),
                                rs.getString("indicaciones"),
                                rs.getDate("fecha_consulta").toLocalDate())
                );
            }
            return consultations;
        } catch (SQLException e) {
            throw new DatabaseException("Error al obtener las consultas del paciente");
        }
    }

    @Override
    public List<Consultation> getAllConsultation(int patientId) {
        List<Consultation> consultations = new ArrayList<>();
        try (PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement("SELECT * FROM consulta WHERE id_paciente = ?")) {
            pst.setInt(1, patientId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Date date = rs.getDate(3);
                LocalDate localDate = date.toLocalDate();
                Consultation consultation = new Consultation(rs.getString(4), localDate, rs.getString(5), rs.getString(6), 
                        rs.getString(7));
                consultation.setConsultationId(rs.getInt(1));
                consultations.add(consultation);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al obtener las consultas de base de datos: ");
        }
        return consultations;
    }
    
}
