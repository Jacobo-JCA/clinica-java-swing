package com.mycompany.clinica.presentation.view;

import com.mycompany.clinica.aplication.context.SesionContexto;
import com.mycompany.clinica.presentation.view.utils.ControllerFactory;
import com.mycompany.clinica.presentation.controller.PatientController;
import com.mycompany.clinica.presentation.controller.RegistroControllerCentral;
import com.mycompany.clinica.presentation.view.utils.NotificationMessage;
import com.mycompany.clinica.domain.entity.Patient;
import java.beans.PropertyVetoException;
import java.time.Duration;
import java.time.Instant;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author jacob
 */
public class Principal extends javax.swing.JFrame {
    private PatientFrame vistaFrame;
    private PatientController patientController;
    PrintFrame printFrame;

    public Principal() {
        initComponents();
        this.setResizable(false);
        this.setBounds(0, 0, 1375, 750);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoModulos = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btnPaciente = new javax.swing.JToggleButton();
        btnHistorial = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        contenedorModulos = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        grupoModulos.add(btnPaciente);
        btnPaciente.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnPaciente.setText("Paciente");
        btnPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacienteActionPerformed(evt);
            }
        });

        btnHistorial.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnHistorial.setText("Historial Paciente");
        btnHistorial.setToolTipText("Informacion del paciente seleccionado");
        btnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnPrint.setText("Imprimir");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(btnPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(506, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout contenedorModulosLayout = new javax.swing.GroupLayout(contenedorModulos);
        contenedorModulos.setLayout(contenedorModulosLayout);
        contenedorModulosLayout.setHorizontalGroup(
            contenedorModulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 988, Short.MAX_VALUE)
        );
        contenedorModulosLayout.setVerticalGroup(
            contenedorModulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contenedorModulos)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contenedorModulos)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void conectarDependencias() {
        ControllerFactory controllerFactory = ControllerFactory.getInstance();
        if (vistaFrame == null || vistaFrame.isClosed()) {
            vistaFrame = ((PatientFrame)controllerFactory.getPatientFrame());
            patientController = controllerFactory.getPatienteController();
            addContenerdor(vistaFrame);
        }
    }
    
    private void addContenerdor(PatientFrame vistaFrame) {
        contenedorModulos.add(vistaFrame);
        vistaFrame.setVisible(true);
        try {
            vistaFrame.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } 
    }
    
    private void btnPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacienteActionPerformed
        Instant inicio = Instant.now();
        conectarDependencias();
        Instant fin = Instant.now();
        long tiempoui = Duration.between(inicio, fin).toMillis();
        System.out.println("Tiempos de carga de la UI: " + tiempoui + " ms");
        patientController.getAllPatients();
    }//GEN-LAST:event_btnPacienteActionPerformed

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
//        ControllerFactory controllerFctory = ControllerFactory.getInstance();
//        RegistroControllerCentral registroCentral = controllerFctory.crearRegistroControllerCentral();
//        SesionContexto sesionContexto = registroCentral.getSesionContexto();
//        if (sesionContexto.getPaciente() != null) {
//            HistorialFrame historial = new HistorialFrame(registroCentral);
//            historial.setVisible(true);
//        } else {
//            MensajeInformativo.mostrarError("Debes seleccionar un paciente primero!");
//        }
    }//GEN-LAST:event_btnHistorialActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        if (vistaFrame != null) {
            printFrame = new PrintFrame();
            printFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            printFrame.setVisible(true);
        } else {
            vistaFrame.showError("Seleccione un Paciente antes de imprimir la Receta!");
        }
    }//GEN-LAST:event_btnPrintActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);  
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHistorial;
    private javax.swing.JToggleButton btnPaciente;
    private javax.swing.JButton btnPrint;
    private javax.swing.JDesktopPane contenedorModulos;
    private javax.swing.ButtonGroup grupoModulos;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
