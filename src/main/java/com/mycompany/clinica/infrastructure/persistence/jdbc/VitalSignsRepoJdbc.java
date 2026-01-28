package com.mycompany.clinica.infrastructure.persistence.jdbc;

import com.mycompany.clinica.domain.entity.VitalSigns;
import com.mycompany.clinica.domain.repo.VitalSignsRepo;
import com.mycompany.clinica.infrastructure.execption.DatabaseException;
import com.mycompany.clinica.infrastructure.persistence.config.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VitalSignsRepoJdbc implements VitalSignsRepo {

    @Override
    public int insertVitalSigns(VitalSigns vitalSigns, int medicalAppointmentId) {
        try (PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement("INSERT INTO signos_vitales(bloodPressure, "
                + "heartRate, respiratoryRate, "
                + "bodyTemperature, weight, height, description, imc, medical_appointmentId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, vitalSigns.getBloodPressure());
            pst.setString(2, vitalSigns.getHeartRate());
            pst.setString(3, vitalSigns.getRespiratoryRate());
            pst.setString(4, vitalSigns.getBodyTemperature());
            pst.setDouble(5, vitalSigns.getWeight());
            pst.setDouble(6, vitalSigns.getHeight());
            pst.setString(7, vitalSigns.getDescription());
            pst.setString(8, vitalSigns.getImc());
            pst.setInt(9, medicalAppointmentId);
            int affectRows = pst.executeUpdate();
            if (affectRows == 0) {
                throw new SQLException("Inserte los Signos Vitales, filas no afectadas.");
            }
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                vitalSigns.setVitalSignsId(generatedKeys.getInt(1));
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Inserte los signos vitales, ID no obtenido.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al guardar Signos vitales en base de datos");
        }
    }

    @Override
    public List<VitalSigns> getAllVitalSigns(int medicalAppointmentId) {
        List<VitalSigns> vitalSignsList = new ArrayList<>();
        try (PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement("SELECT * FROM signos_vitales WHERE medical_appointmentId = ?")) {
            pst.setInt(1, medicalAppointmentId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                VitalSigns vitalSigns = new VitalSigns.BuilderVitalSigns()
                        .bloodPressure(rs.getString(2))
                        .heartRate(rs.getString(3))
                        .respiratoryRate(rs.getString(4))
                        .bodyTemperature(rs.getString(5))
                        .weight(rs.getDouble(6))
                        .height(rs.getDouble(7))
                        .description(rs.getString(8))
                        .imc(rs.getString(9))
                        .build();
                vitalSigns.setVitalSignsId(rs.getInt(1));
                vitalSignsList.add(vitalSigns);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al consultar la lista de Signos Vitales de base de datos: ");
        }
        return vitalSignsList;
    }
}