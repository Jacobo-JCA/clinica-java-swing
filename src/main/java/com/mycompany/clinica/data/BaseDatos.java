package com.mycompany.clinica.data;

import com.mycompany.clinica.model.Consulta;
import com.mycompany.clinica.model.Enfermedades;
import com.mycompany.clinica.model.Paciente;
import com.mycompany.clinica.model.SignosVitales;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author jacob
 */
public class BaseDatos {
    Connection conn = null;
    PreparedStatement pst = null;
    Statement st = null;
    ResultSet rs = null;
    
    public BaseDatos() {
        try {
            Class.forName("org.postgresql.Driver"); 
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public int insertPaciente(Paciente paciente) {
        int pacientId = 0;

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica_praxell", "postgres", "1234")) {
            // Primero, busco en BD
            String sqlBusqueda = "SELECT id_paciente FROM paciente WHERE cedula = ?";
            pst = conn.prepareStatement(sqlBusqueda);
            pst.setString(1, paciente.getCedula());
            rs = pst.executeQuery();

            if (rs.next()) {
                // Verifico si el paciente ya existe
                pacientId = rs.getInt("id_paciente");
                int consultaId = insertConsulta(paciente.getListConsultas().get(0), pacientId);

                // Inserta los nuevos signos vitales
                for (SignosVitales signosVitales : paciente.getListSignosVitales()) {
                    insertSignosVitales(signosVitales, consultaId);
                }
            }else {
                String sqlInsertar = "INSERT INTO paciente (cedula, nombre, apellido, direccion, email, edad, genero, expediente, ciudad, estado, fecha_nacimiento, telefono, ocupacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                pst = conn.prepareStatement(sqlInsertar, Statement.RETURN_GENERATED_KEYS);

                pst.setString(1, paciente.getCedula());
                pst.setString(2, paciente.getNombre());
                pst.setString(3, paciente.getApellido());
                pst.setString(4, paciente.getDireccion());
                pst.setString(5, paciente.getEmail());
                pst.setInt(6, paciente.getEdad());
                pst.setString(7, paciente.getGenero());
                pst.setInt(8, paciente.getExpediente());
                pst.setString(9, paciente.getCiudad());
                pst.setString(10, paciente.getEstado());
                Date fechaNacimientoSQL = Date.valueOf(paciente.getFechaNacimiento());
                pst.setDate(11, fechaNacimientoSQL);
                pst.setString(12, paciente.getTelefono());
                pst.setString(13, paciente.getOcupacion());

                int affectedRows = pst.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Inserte el paciente, filas no afectadas.");
                }

                try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        pacientId = generatedKeys.getInt(1);

                        // Insertamos las enfermedades asociadas con el paciente
                        for (Enfermedades enfermedad : paciente.getListEnfermedades()) {
                            insertEnfermedades(enfermedad, pacientId);
                        }

                        /*for (Consulta consulta : paciente.getListConsultas()) {
                            insertConsulta(consulta, pacientId);
                        }*/
                        int consultId = insertConsulta(paciente.getListConsultas().get(0), pacientId);

                        for (SignosVitales signosVitales : paciente.getListSignosVitales()) {
                            insertSignosVitales(signosVitales, consultId);
                        }

                    } else {
                        throw new SQLException("Inserte el paciente, ID no obtenido.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }   

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return pacientId;
    }

    public Paciente buscarPacientePorCedula(String cedula) {
        Paciente paciente = null;
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica_praxell", "postgres", "1234")) {

            String sql = "SELECT * FROM paciente WHERE cedula = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, cedula);
            rs = pst.executeQuery();
            if (rs.next()) {
                String dni = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                String email = rs.getString("email");
                int edad = rs.getInt("edad");
                String genero = rs.getString("genero");
                int expediente = rs.getInt("expediente");
                String ciudad = rs.getString("ciudad");
                String estado = rs.getString("estado");
                LocalDate fechaNacimiento = rs.getDate("fecha_nacimiento").toLocalDate();
                String telefono = rs.getString("telefono");
                String ocupacion = rs.getString("ocupacion");

                paciente = new Paciente(dni, nombre, apellido, direccion, email, edad, genero, expediente, ciudad, estado, fechaNacimiento, telefono, ocupacion);
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return paciente;
    }
    
    public void insertEnfermedades(Enfermedades enfermedad, int idPaciente) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica_praxell", "postgres", "1234")) {
            String sql = "INSERT INTO enfermedades_paciente(patologico, no_patologico, clinico, quirurjico, hereditario, id_paciente) VALUES (?, ?, ?, ?, ?, ?)";

            pst = conn.prepareStatement(sql);
            // Asigna la misma descripción a todos los campos
            pst.setString(1, enfermedad.getPatologico());
            pst.setString(2, enfermedad.getNoPatologico());
            pst.setString(3, enfermedad.getClinico());
            pst.setString(4, enfermedad.getQuirurjico());
            pst.setString(5, enfermedad.getHereditario());
            pst.setInt(6, idPaciente);

            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertSignosVitales(SignosVitales signosVitales, int idConsulta) {
        try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica_praxell", "postgres", "1234")) {
            
            String sql = "INSERT INTO signos_vitales(presion_arterial, frecuencia_cardiaca, frecuencia_respiratoria, temperatura, peso, talla, descripcion, imc, id_consulta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, signosVitales.getPresionArterial());
            pst.setString(2, signosVitales.getFrecuenciaCardiaca());
            pst.setString(3, signosVitales.getFrecuenciaRespiratoria());
            pst.setString(4, signosVitales.getTemperatura());
            pst.setDouble(5, signosVitales.getPeso());
            pst.setDouble(6, signosVitales.getTalla());
            pst.setString(7, signosVitales.getDescripcion());
            pst.setString(8, signosVitales.getImc());
            pst.setInt(9, idConsulta);
             
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    public int insertConsulta(Consulta consulta, int idPaciente) {
        int consultaId = 0;
        try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica_praxell", "postgres", "1234")) {
            
            String sql = "INSERT INTO consulta(id_paciente, fecha_consulta, motivo, diagnostico, receta) VALUES (?, ?, ?, ?, ?)";
            
            pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, idPaciente);
            Date fechaConsultaSQL = Date.valueOf(consulta.getFechaConsulta());
            pst.setDate(2, fechaConsultaSQL);
            pst.setString(3, consulta.getMotivoConsulta());
            pst.setString(4, consulta.getDiagnostico());
            pst.setString(5, consulta.getReceta());
            
            int affectRow = pst.executeUpdate();
           
            if (affectRow == 0) {
                throw new SQLException("No se afecto la fila");
            }
            
            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if(generatedKeys.next()) {
                    consultaId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return consultaId;
    }
    
    
    public  ArrayList<Paciente> obtenerPacientes() {
        ArrayList<Paciente> listaPaciente = new ArrayList<>();
        
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica_praxell", "postgres", "1234");
            String sql = "SELECT * FROM paciente";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {

                int idPaciente = rs.getInt("id_paciente");
                String cedula = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                String email = rs.getString("email");
                int edad = rs.getInt("edad");
                String genero = rs.getString("genero");
                int expediente = rs.getInt("expediente");
                String ciudad = rs.getString("ciudad");
                String estado = rs.getString("estado");
                java.sql.Date fechaNacimientoSQL = rs.getDate("fecha_nacimiento");
                LocalDate fechaNacimiento = fechaNacimientoSQL.toLocalDate();
                String telefono = rs.getString("telefono");
                String ocupacion = rs.getString("ocupacion");

                Paciente paciente = new Paciente(idPaciente, cedula, nombre, apellido, direccion, email, edad, genero, 
                        expediente, ciudad, estado, telefono, fechaNacimiento, ocupacion);
                listaPaciente.add(paciente);
            }

        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaPaciente;
    }
    
    public Consulta obtenerConsulta(int idPaciente) {
        Consulta consulta = new Consulta();
        
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica_praxell", "postgres", "1234");
            String sql = "SELECT * FROM consulta WHERE id_paciente = ?";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, idPaciente);
            rs = pst.executeQuery();

            if (rs.next()) {
                Date date = rs.getDate(3);
                LocalDate localDate = date.toLocalDate();
                consulta = new Consulta(rs.getString(4), localDate, rs.getString(5), rs.getString(6));
            }

        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return consulta;
    }
    
    public SignosVitales obtenerSignosVitales(int idConsulta) {
        SignosVitales signosVitales = new SignosVitales();
        
        try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica_praxell", "postgres", "1234")){
            String sql = "SELECT * FROM signos_vitales WHERE id_consulta = ?";
            
            pst = conn.prepareStatement(sql);
            pst.setInt(1, idConsulta);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                signosVitales = new SignosVitales(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
                        rs.getDouble(6), rs.getDouble(7), rs.getString(8), rs.getString(9));
            }
        }catch (SQLException s) {
            s.printStackTrace();
        }
        return signosVitales;
    }
    
    
    public ArrayList<Paciente> obtenerPacientesPorCampo(String campo) {
        ArrayList<Paciente> listPacientes = new ArrayList<>();
        
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica_praxell", "postgres", "1234");
            String sql = "SELECT * FROM paciente WHERE nombre LIKE'%"+campo+"%'" + "OR apellido LIKE'%"+campo+"%'" + "ORDER BY nombre";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()) {
                int idPaciente = rs.getInt("id_paciente");
                String cedula = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                String email = rs.getString("email");
                int edad = rs.getInt("edad");
                String genero = rs.getString("genero");
                int expediente = rs.getInt("expediente");
                String ciudad = rs.getString("ciudad");
                String estado = rs.getString("estado");
                java.sql.Date fechaNacimientoSQL = rs.getDate("fecha_nacimiento");
                LocalDate fechaNacimiento = fechaNacimientoSQL.toLocalDate();
                String telefono = rs.getString("telefono");
                String ocupacion = rs.getString("ocupacion");
                
                Paciente paciente = new Paciente(idPaciente, cedula, nombre, apellido, direccion, email, edad, genero, expediente, ciudad, estado, telefono, fechaNacimiento, ocupacion);
                listPacientes.add(paciente);
            }
        }catch(SQLException s) {
            s.printStackTrace();
        }finally {
            try {
                st.close();
                conn.close();
            }catch(SQLException s) {
                s.printStackTrace();
            }
        }
        return listPacientes;
    }
    
    //Updating
    public void actualizarPaciente(Paciente paciente, int id) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica_praxell", "postgres", "1234")) {
            String sql = "UPDATE paciente SET nombre = ?, apellido = ?, direccion = ?, expediente = ?, ciudad = ?, genero = ?, email = ? WHERE id_paciente = ?";
            pst = conn.prepareStatement(sql);

            pst.setString(1, paciente.getNombre());
            pst.setString(2, paciente.getApellido());
            pst.setString(3, paciente.getDireccion());
            pst.setInt(4, paciente.getExpediente());
            pst.setString(5, paciente.getCiudad());
            pst.setString(6, paciente.getGenero());
            pst.setString(7, paciente.getEmail());
            pst.setInt(8, id);
            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar paciente: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void deletePaciente(Paciente paciente) {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinica_praxell", "postgres", "1234");
            String sql = "DELETE FROM paciente WHERE id_paciente = ?";

            pst = conn.prepareStatement(sql);
            pst.setInt(1, paciente.getIdPaciente());
            pst.executeUpdate();

        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
