package com.mycompany.clinica.execption;

public class ValidacionCampos {
    private static boolean validar(Object objeto) {
        if (objeto == null) {
            return false;
        }
        return true;
    }

    public static boolean validarCampos(Object objeto) {
        return validar(objeto); 
    }
}
