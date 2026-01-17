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
    public int insertHealthStatus(HealthStatus healthStatus, int patientId) {
        try (PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement(
                "INSERT INTO enfermedades_paciente(pathological, noPathological, "
                + "clinical, surgical, hereditary, patient_id) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, healthStatus.getPathological());
            pst.setString(2, healthStatus.getNoPathological());
            pst.setString(3, healthStatus.getClinical());
            pst.setString(4, healthStatus.getSurgical());
            pst.setString(5, healthStatus.getHereditary());
            pst.setInt(6, patientId);
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
    public List<HealthStatus> getAllHealthStatus(int patientId) {
        System.out.println("Buscando enfermedades para paciente ID: " + patientId);
        List<HealthStatus> healthStatusList = new ArrayList<>();
        try (PreparedStatement pst = DatabaseConnection.getInstance()
                .prepareStatement("SELECT * FROM enfermedades_paciente WHERE id_paciente = ?")) {
            pst.setInt(1, patientId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                HealthStatus healthStatus = new HealthStatus(
                        rs.getString("patologico"),
                        rs.getString("no_patologico"),
                        rs.getString("clinico"),
                        rs.getString("quirurjico"),
                        rs.getString("hereditario")
                );
                healthStatus.setHealthStatusId(rs.getInt("id_enfermedad"));
                healthStatusList.add(healthStatus);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error al recuperar la lista del estado de salud de base de datos");
        }
        return healthStatusList;
    }
    
}
