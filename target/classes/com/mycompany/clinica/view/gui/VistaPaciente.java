package com.mycompany.clinica.view.gui;

import javax.swing.JInternalFrame;

public interface VistaPaciente<T extends JInternalFrame> {
    PacienteFrame getFrame();
}
