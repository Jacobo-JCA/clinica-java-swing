package com.mycompany.clinica.infrastructure.execption;

public class TecnicoException extends RuntimeException {
    public TecnicoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
