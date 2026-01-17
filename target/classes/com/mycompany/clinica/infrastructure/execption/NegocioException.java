package com.mycompany.clinica.infrastructure.execption;

public class NegocioException extends RuntimeException {
    public NegocioException(String mensaje) {
        super(mensaje);
    }
}
