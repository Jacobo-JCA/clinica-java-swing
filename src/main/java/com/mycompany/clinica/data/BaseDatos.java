package com.mycompany.clinica.data;

import com.mycompany.clinica.gui.PacienteFrame;
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
    
    private Connection getConnection() throws SQLException {
        return ConnectionDB.getInstance();
    }
    
    public BaseDatos() {
        
    }
    
    public void insertPaciente(Paciente paciente) {
        
        try (PreparedStatement sqlInsertar = this.getConnection().prepareStatement("INSERT INTO paciente (cedula, nombre, apellido, direccion, "
                + "email, edad, genero, expediente, ciudad, estado, fecha_nacimiento, telefono, ocupacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {


            sqlInsertar.setString(1, paciente.getCedula());
            sqlInsertar.setString(2, paciente.getNombre());
            sqlInsertar.setString(3, paciente.getApellido());
            sqlInsertar.setString(4, paciente.getDireccion());
            sqlInsertar.setString(5, paciente.getEmail());
            sqlInsertar.setInt(6, paciente.getEdad());
            sqlInsertar.setString(7, paciente.getGenero());
            sqlInsertar.setInt(8, paciente.getExpediente());
            sqlInsertar.setString(9, paciente.getCiudad());
            sqlInsertar.setString(10, paciente.getEstado());
            Date fechaNacimientoSQL = Date.valueOf(paciente.getFechaNacimiento());
            sqlInsertar.setDate(11, fechaNacimientoSQL);
            sqlInsertar.setString(12, paciente.getTelefono());
            sqlInsertar.setString(13, paciente.getOcupacion());

            int affectedRows = sqlInsertar.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Inserte el paciente, filas no afectadas.");
            }

            try (ResultSet generatedKeys = sqlInsertar.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    paciente.setIdPaciente(generatedKeys.getInt(1));

                    // Insertamos las enfermedades asociadas con el paciente
                    insertEnfermedades(paciente.getListEnfermedades().get(paciente.getListEnfermedades().size()-1), paciente.getIdPaciente());

                    insertarConsultaAndSignosVitales(paciente);

                } else {
                    throw new SQLException("Inserte el paciente, ID no obtenido.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void insertarConsultaAndSignosVitales(Paciente paciente) {
        Consulta consulta = paciente.getListConsultas().get(paciente.getListConsultas().size()-1);
        insertConsulta(consulta, paciente.getIdPaciente());
        insertSignosVitales(consulta.getSignosVitales(), consulta.getIdConsulta());
    }

    public Paciente buscarPacientePorCedula(String cedula) {
        Paciente paciente = null;
        try (PreparedStatement pst = this.getConnection().prepareStatement("SELECT * FROM paciente WHERE cedula = ?")) {

            pst.setString(1, cedula);
            ResultSet rs = pst.executeQuery();
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
        try (PreparedStatement pst = this.getConnection().prepareStatement("INSERT INTO enfermedades_paciente(patologico, no_patologico, clinico, quirurjico, hereditario, id_paciente) VALUES (?, ?, ?, ?, ?, ?)")) {

            // Asigna la misma descripción a todos los campos
            pst.setString(1, enfermedad.getPatologico());
            pst.setString(2, enfermedad.getNoPatologico());
            pst.setString(3, enfermedad.getClinico());
            pst.setString(4, enfermedad.getQuirurjico());
            pst.setString(5, enfermedad.getHereditario());
            pst.setInt(6, idPaciente);

            int row = pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertSignosVitales(SignosVitales signosVitales, int idConsulta) {
        try(PreparedStatement pst = this.getConnection().prepareStatement("INSERT INTO signos_vitales(presion_arterial, frecuencia_cardiaca, frecuencia_respiratoria, "
                + "temperatura, peso, talla, descripcion, imc, id_consulta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            
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
    
    public void insertConsulta(Consulta consulta, int idPaciente) {
        
        try(PreparedStatement pst = this.getConnection().prepareStatement("INSERT INTO consulta(id_paciente, fecha_consulta, motivo, "
                + "diagnostico, receta, indicaciones) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            
            
            pst.setInt(1, idPaciente);
            Date fechaConsultaSQL = Date.valueOf(consulta.getFechaConsulta());
            pst.setDate(2, fechaConsultaSQL);
            pst.setString(3, consulta.getMotivoConsulta());
            pst.setString(4, consulta.getDiagnostico());
            pst.setString(5, consulta.getReceta());
            pst.setString(6, consulta.getIndicaciones());
            
            int affectRow = pst.executeUpdate();
            System.out.println("se guardo" + affectRow);
           
            if (affectRow == 0) {
                throw new SQLException("No se afecto la fila");
            }
            
            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if(generatedKeys.next()) {
                    consulta.setIdConsulta(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("No se pudo obtener el ID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    public  ArrayList<Paciente> obtenerPacientes() {
        ArrayList<Paciente> listaPaciente = new ArrayList<>();
        
        try(PreparedStatement pst = this.getConnection().prepareStatement("SELECT * FROM paciente")) {

            ResultSet rs = pst.executeQuery();

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
                
                paciente.setListEnfermedades(obtenerEnfermedades(paciente.getIdPaciente()));
                paciente.setListConsultas(obtenerConsulta(paciente.getIdPaciente()));
                
                listaPaciente.add(paciente);
                
            }  

        } catch (SQLException s) {
            s.printStackTrace();
        } 
        return listaPaciente;
    }
    
    public ArrayList<Consulta> obtenerConsulta(int idPaciente) {
        ArrayList<Consulta> consultas = new ArrayList<>();
        
        try(PreparedStatement pst = this.getConnection().prepareStatement("SELECT * FROM consulta WHERE id_paciente = ?")) {
            
            pst.setInt(1, idPaciente);
            ResultSet rs = pst.executeQuery();

            
            while(rs.next()) {
                Date date = rs.getDate(3);
                LocalDate localDate = date.toLocalDate();
                
                Consulta consulta = new Consulta(rs.getString(4), localDate, rs.getString(5), rs.getString(6), rs.getString(7));
                consulta.setIdConsulta(rs.getInt(1));
                consulta.setSignosVitales(obtenerSignosVitales(consulta.getIdConsulta()));
                
                consultas.add(consulta);
            }   

        } catch (SQLException s) {
            s.printStackTrace();
        } 
        return consultas;
    }
    
    public SignosVitales obtenerSignosVitales(int idConsulta) {
        SignosVitales signosVitales = new SignosVitales();
        
        try(PreparedStatement pst = this.getConnection().prepareStatement("SELECT * FROM signos_vitales WHERE id_consulta = ?")){

            pst.setInt(1, idConsulta);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                signosVitales = new SignosVitales(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
                        rs.getDouble(6), rs.getDouble(7), rs.getString(8), rs.getString(9));
                signosVitales.setIdSignosVitales(rs.getInt(1));
                
            }else {
                System.out.println("No hay info de signos");
            }
        }catch (SQLException s) {
            s.printStackTrace();
        }
        return signosVitales;
    }
    
    public ArrayList<Enfermedades> obtenerEnfermedades(int idPaciente) {
        ArrayList<Enfermedades> enfermedades = new ArrayList<>();
        
        try(PreparedStatement pst = this.getConnection().prepareStatement("SELECT * FROM enfermedades_paciente WHERE id_paciente = ?")) {
            
            pst.setInt(1, idPaciente);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                Enfermedades enfermedad = 
                        new Enfermedades(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7));
                
                enfermedades.add(enfermedad);
            }   
           
        } catch (SQLException s) {
            s.printStackTrace();
        } 
        return enfermedades;
    }
    
    public ArrayList<Paciente> obtenerPacientesPorCampo(String campo) {
        ArrayList<Paciente> listPacientes = new ArrayList<>();
        
        try(PreparedStatement pst = this.getConnection().prepareStatement("SELECT * FROM paciente WHERE nombre LIKE'%"+campo+"%'" + "OR apellido LIKE'%"+campo+"%'" + "ORDER BY nombre")) {
           
            ResultSet rs = pst.executeQuery();
            
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
        }
        return listPacientes;
    }
    
    //Updating
    public void actualizarPaciente(Paciente paciente) {
        try (PreparedStatement pst = this.getConnection().prepareStatement("UPDATE paciente SET nombre = ?, apellido = ?, direccion = ?, expediente = ?, ciudad = ?, genero = ?, "
                + "ocupacion=?, estado=?, telefono=?, email = ? WHERE id_paciente = ?")) {

            pst.setString(1, paciente.getNombre());
            pst.setString(2, paciente.getApellido());
            pst.setString(3, paciente.getDireccion());
            pst.setInt(4, paciente.getExpediente());
            pst.setString(5, paciente.getCiudad());
            pst.setString(6, paciente.getGenero());
            pst.setString(7, paciente.getOcupacion());
            pst.setString(8, paciente.getEstado());
            pst.setString(9, paciente.getTelefono());
            pst.setString(10, paciente.getEmail());
            pst.setInt(11, paciente.getIdPaciente());
            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar paciente: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void deletePaciente(Paciente paciente) {
        try(PreparedStatement pst = this.getConnection().prepareStatement("DELETE FROM paciente WHERE id_paciente = ?")) {

            pst.setInt(1, paciente.getIdPaciente());
            pst.executeUpdate();

        } catch (SQLException s) {
            s.printStackTrace();
        } 
    }

}
