package com.mycompany.clinica.infrastructure.persistence.jdbc;

import com.mycompany.clinica.domain.entity.HealthStatus;
import com.mycompany.clinica.domain.repo.HealthStatusRepo;
import com.mycompany.clinica.infrastructure.execption.DatabaseException;
import com.mycompany.clinica.infrastructure.persistence.config.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HealthStatusRepoJdbc implements HealthStatusRepo {

    @Override
    public int insertHealthStatus(HealthStatus healthStatus, int medicalAppointmentId) {
        try (PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement(
                "INSERT INTO enfermedades_paciente(pathological, noPathological, "
                + "clinical, surgical, hereditary, medical_appointmentId) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, healthStatus.getPathological());
            pst.setString(2, healthStatus.getNoPathological());
            pst.setString(3, healthStatus.getClinical());
            pst.setString(4, healthStatus.getSurgical());
            pst.setString(5, healthStatus.getHereditary());
            pst.setInt(6, medicalAppointmentId);
            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserte la enfermedad, filas no afectadas.");
            }
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                healthStatus.setHealthStatusId(generatedKeys.getInt(1));
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Inserte la enfermedad, ID no obtenido.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al guardar enfermedades en base de datos");
        }
    }

    @Override
    public List<HealthStatus> getAllHealthStatus(int medicalAppointmentId) {
        List<HealthStatus> healthStatusList = new ArrayList<>();
        try (PreparedStatement pst = DatabaseConnection.getInstance()
                .prepareStatement("SELECT * FROM health_status WHERE medical_appointmentId = ?")) {
            pst.setInt(1, medicalAppointmentId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                HealthStatus healthStatus = new HealthStatus.BuilderHealthStatus()
                        .pathological(rs.getString("pathological"))
                        .noPathological(rs.getString("no_pathological"))
                        .clinical(rs.getString("clinical"))
                        .surgical(rs.getString("surgical"))
                        .hereditary(rs.getString("hereditary"))
                        .build();
                healthStatus.setHealthStatusId(rs.getInt("health_status_id"));
                healthStatusList.add(healthStatus);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error al recuperar la lista del estado de salud de base de datos");
        }
        return healthStatusList;
    }
    
}
