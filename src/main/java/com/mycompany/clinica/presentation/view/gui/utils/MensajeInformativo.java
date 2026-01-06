package com.mycompany.clinica.presentation.view.gui.utils;

import javax.swing.JOptionPane;

public class MensajeInformativo {
    public static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
            null, 
            mensaje, 
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
    
    public static void mostrarConfirmacion(String mensaje) {
        JOptionPane.showMessageDialog(
            null, 
            mensaje, 
            "Éxito!", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
