package com.mycompany.clinica.aplication.util;

public final class ValidationUtil {
    private ValidationUtil() {}
    
    public static boolean isNullOrBlank(String value) {
        return value == null || value.isBlank();
    } 
}
