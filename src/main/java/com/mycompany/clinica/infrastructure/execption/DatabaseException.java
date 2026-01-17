package com.mycompany.clinica.infrastructure.execption;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }
}
