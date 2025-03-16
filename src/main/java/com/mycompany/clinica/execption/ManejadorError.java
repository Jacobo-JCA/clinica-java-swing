package com.mycompany.clinica.execption;

import java.sql.SQLException;

public class ManejadorError {
    public static String obtenerMensajeError(Exception e) {
        if (e instanceof NegocioException) {
            return "Error!!: " + e.getMessage();
        } else if(e instanceof ValidacionException) {
            return "Campos requeridos: " + e.getMessage();
        } else if (e instanceof TecnicoException) {
            return "Error técnico. Contacte al soporte: " + e.getCause().getMessage();
        }  else if (e instanceof SQLException) {
            return "Error de base de datos: " + e.getMessage();
        } else {
            return "Error inesperado: " + e.getMessage();
        }
    }
}
