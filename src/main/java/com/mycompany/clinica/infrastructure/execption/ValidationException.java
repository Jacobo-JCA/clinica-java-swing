package com.mycompany.clinica.infrastructure.execption;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}