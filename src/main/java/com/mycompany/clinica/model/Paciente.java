package com.mycompany.clinica.model;

import java.sql.Date;

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
    private Date fechaNacimiento;
    private String telefono;
    private String ocupacion;
    private String motivoConsulta;
    private Date fechaConsulta;

    public Paciente(String cedula, String nombre, String apellido, 
            String direccion, String email, int edad, String genero, int expediente, String ciudad, String estado, 
            Date fechaNacimiento, String telefono, String ocupacion, String motivoConsulta, Date fechaConsulta) {
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
        this.motivoConsulta = motivoConsulta;
        this.fechaConsulta = fechaConsulta;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
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

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    @Override
    public String toString() {
        return  "idPaciente=" + idPaciente + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido=" 
                + apellido + ", direccion=" + direccion + ", email=" + email + ", edad=" + edad + ", genero=" + genero 
                + ", expediente=" + expediente + ", ciudad=" + ciudad + ", estado=" + estado + ", fechaNacimiento=" 
                + fechaNacimiento + ", telefono=" + telefono + ", ocupacion=" + ocupacion + ", motivoConsulta=" + motivoConsulta 
                + ", fechaConsulta=" + fechaConsulta;
    }
    
    
}
