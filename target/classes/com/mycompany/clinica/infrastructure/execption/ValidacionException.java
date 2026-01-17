package com.mycompany.clinica.infrastructure.execption;

public class ValidacionException extends RuntimeException {
    public ValidacionException(String message) {
        super(message);
    }
}