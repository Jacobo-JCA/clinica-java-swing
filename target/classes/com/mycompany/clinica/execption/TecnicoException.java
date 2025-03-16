package com.mycompany.clinica.execption;

public class TecnicoException extends RuntimeException {
    public TecnicoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
