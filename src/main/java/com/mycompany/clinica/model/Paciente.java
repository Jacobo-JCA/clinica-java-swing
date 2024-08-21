package com.mycompany.clinica.model;


import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author jacob
 */
public class Paciente {   
    private int idPaciente;
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String email;
    private int edad;
    private String genero;
    private int expediente;
    private String ciudad;
    private String estado;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String ocupacion;
    private ArrayList<Enfermedades> listEnfermedades;
    private ArrayList<Consulta> listConsultas = new ArrayList<>();
    
    public Paciente() {
        this.listEnfermedades = new ArrayList<>();
    }
    
    public Paciente(String cedula, String nombre, String apellido, 
            String direccion, String email, int edad, String genero, int expediente, String ciudad, String estado, 
            LocalDate fechaNacimiento, String telefono, String ocupacion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.email = email;
        this.edad = edad;
        this.genero = genero;
        this.expediente = expediente;
        this.ciudad = ciudad;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.ocupacion = ocupacion;
        this.listEnfermedades = new ArrayList<>();
    }

    public Paciente(int idPaciente, String cedula, String nombre, String apellido, String direccion, String email, int edad, String genero, int expediente, 
            String ciudad, String estado, String telefono, LocalDate fechaNacimiento, String ocupacion) {
        this.idPaciente = idPaciente;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.email = email;
        this.edad = edad;
        this.genero = genero;
        this.expediente = expediente;
        this.ciudad = ciudad;
        this.estado = estado;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.ocupacion = ocupacion;
        this.listEnfermedades = new ArrayList<>();
    }

    public Paciente(int idPaciente, String nombre, String apellido, String genero, String ciudad, int expediente) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.ciudad = ciudad;
        this.expediente = expediente;
        this.email = email;
        this.listEnfermedades = new ArrayList<>();
    }

    
    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getExpediente() {
        return expediente;
    }

    public void setExpediente(int expediente) {
        this.expediente = expediente;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public ArrayList<Enfermedades> getListEnfermedades() {
        return this.listEnfermedades;
    }

    public void setListEnfermedades(ArrayList<Enfermedades> listEnfermedades) {
        this.listEnfermedades = listEnfermedades;
    }

    public void addEnfermedad(Enfermedades enfermedad) {
        this.listEnfermedades.add(enfermedad);
    }

    public ArrayList<Consulta> getListConsultas() {
        return listConsultas;
    }

    public void setListConsultas(ArrayList<Consulta> listConsultas) {
        this.listConsultas = listConsultas;
    }
    
    public void addConsultas(Consulta consulta) {
        this.listConsultas.add(consulta);
        
    }

    @Override
    public String toString() {
        return "Paciente{" + "idPaciente=" + idPaciente + ", cedula=" + cedula + 
                ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + 
                ", email=" + email + ", edad=" + edad + ", genero=" + genero + ", expediente=" + expediente + 
                ", ciudad=" + ciudad + ", estado=" + estado + ", telefono=" + telefono + ", fechaNacimiento=" + fechaNacimiento + 
                ", ocupacion=" + ocupacion + '}';
    }
    
    
}
