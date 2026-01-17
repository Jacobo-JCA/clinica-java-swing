package com.mycompany.clinica.presentation.view.utils;

import javax.swing.JOptionPane;

public class NotificationMessage {
    public static void showError(String mensaje) {
        JOptionPane.showMessageDialog(
            null, 
            mensaje, 
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
    
    public static void showConfirmationSuccessful(String mensaje) {
        JOptionPane.showMessageDialog(
            null, 
            mensaje, 
            "Éxito!", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
