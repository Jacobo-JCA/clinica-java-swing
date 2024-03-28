package com.mycompany.clinica.data;

import com.mycompany.clinica.model.Enfermedades;
import com.mycompany.clinica.model.Paciente;
import com.mycompany.clinica.model.SignosVitales;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jacob
 */
public class BaseDatos {
    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    
    public BaseDatos() {
        try {
            Class.forName("org.postgresql.Driver"); 
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public void insertPaciente(Paciente paciente) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica_praxell", "postgres", "1234")) {

            String sql = "INSERT INTO paciente (cedula, nombre, apellido, direccion, email, edad, genero, expediente, ciudad, estado, fecha_nacimiento, telefono, ocupacion, motivo_consulta, fecha_consulta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, paciente.getCedula());
            st.setString(2, paciente.getNombre());
            st.setString(3, paciente.getApellido());
            st.setString(4, paciente.getDireccion());
            st.setString(5, paciente.getEmail());
            st.setInt(6, paciente.getEdad());
            st.setString(7, paciente.getGenero());
            st.setInt(8, paciente.getExpediente());
            st.setString(9, paciente.getCiudad());
            st.setString(10, paciente.getEstado());
            st.setDate(11, paciente.getFechaNacimiento());
            st.setString(12, paciente.getTelefono());
            st.setString(13, paciente.getOcupacion());
            st.setString(14, paciente.getMotivoConsulta());
            st.setDate(15, paciente.getFechaConsulta());

            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserte el paciente, filas no afectadas.");
            }

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int patientId = generatedKeys.getInt(1);

                    // Insertamos las enfermedades asociadas con el paciente
                    for (Enfermedades enfermedad : paciente.getListEnfermedades()) {
                        insertEnfermedades(enfermedad, patientId);
                    }

                    for (SignosVitales signosVitales : paciente.getListSignosVitales()) {
                        insertSignosVitales(signosVitales, patientId);
                    }
                } else {
                    throw new SQLException("Inserte el paciente, ID no obtenido.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    
    
    public void insertEnfermedades(Enfermedades enfermedad, int idPaciente) {
        try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica_praxell", "postgres", "1234")) {
            
            String sql = "INSERT INTO enfermedades(enfermedad, descripcion, id_paciente) VALUES (?, ?, ?)";

            st = conn.prepareStatement(sql);
            st.setString(1, enfermedad.getEnfermedad());
            st.setString(2, enfermedad.getDescripcion());
            st.setInt(3, idPaciente);

            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertSignosVitales(SignosVitales signosVitales, int idPaciente) {
        try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica_praxell", "postgres", "1234")) {
            
            String sql = "INSERT INTO signos_vitales(presion_arterial, frecuencia_cardiaca, frecuencia_respiratoria, temperatura, peso, talla, descripcion, imc, id_paciente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            st = conn.prepareStatement(sql);
            
            st.setString(1, signosVitales.getPresionArterial());
            st.setString(2, signosVitales.getFrecuenciaCardiaca());
            st.setString(3, signosVitales.getFrecuenciaRespiratoria());
            st.setString(4, signosVitales.getTemperatura());
            st.setDouble(5, signosVitales.getPeso());
            st.setDouble(6, signosVitales.getTalla());
            st.setString(7, signosVitales.getDescripcion());
            st.setString(8, signosVitales.getImc());
            st.setInt(9, idPaciente);
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    
    /*public static void main(String[] args) {
        Patologico patologico = new Patologico(1, "consula medica");
        BaseDatos bd = new BaseDatos();
        bd.insertPatologicos(patologico);
    }*/
}
