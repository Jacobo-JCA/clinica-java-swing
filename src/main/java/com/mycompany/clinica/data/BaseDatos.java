package com.mycompany.clinica.data;

import com.mycompany.clinica.model.Paciente;
import com.mycompany.clinica.model.Patologico;
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
        
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica_praxell", "postgres", "1234");
            String sql = "INSERT INTO paciente (cedula, nombre, apellido, direccion, email, edad, genero, expediente, ciudad, estado,"
                    + "fecha_nacimiento, telefono, ocupacion, motivo_consulta, fecha_consulta)"
                    + "VALUES(?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            st = conn.prepareStatement(sql);
            
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
            
            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
    }
    
    public void insertPatologicos(Patologico patologico) {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica_praxell", "postgres", "1234");
            String sql = "INSERT INTO patologico(id_patologico, descripcion) VALUES (?, ?)";
            
            st = conn.prepareStatement(sql);
            st.setInt(1, patologico.getIdPatologico());
            st.setString(2, patologico.getDescripcion());
            
            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /*public static void main(String[] args) {
        Patologico patologico = new Patologico(1, "consula medica");
        BaseDatos bd = new BaseDatos();
        bd.insertPatologicos(patologico);
    }*/
}
