package com.mycompany.clinica.infrastructure.execption;

import java.sql.SQLException;

public class ManejadorError {
    public static String obtenerMensajeError(Exception e) {
        if (e instanceof NegocioException) {
            return "Error!!: " + e.getMessage();
        } else if(e instanceof ValidationException) {
            return "Campos requeridos: " + e.getMessage();
        }  else if (e instanceof SQLException) {
            return "Error de base de datos: " + e.getMessage();
        } else {
            return e.getMessage();
        }
    }
}
