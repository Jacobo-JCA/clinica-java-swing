package com.mycompany.clinica.gui;

import com.mycompany.clinica.model.Consulta;
import com.mycompany.clinica.model.Paciente;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author jacob
 */
public class ConsultaFrame extends javax.swing.JDialog {
    
    PacienteFrame pacienteFrame;
    Paciente paciente;
    
    public ConsultaFrame(java.awt.Frame parent, boolean modal, PacienteFrame pacienteFrame, Paciente paciente) {
        super(parent, modal);
        this.pacienteFrame = pacienteFrame;
        this.paciente = paciente;
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textDiagnostico = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textReceta = new javax.swing.JTextArea();
        btnGuadarConsulta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Diagnostico:");

        textDiagnostico.setColumns(20);
        textDiagnostico.setRows(5);
        jScrollPane1.setViewportView(textDiagnostico);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Receta:");

        textReceta.setColumns(20);
        textReceta.setRows(5);
        jScrollPane2.setViewportView(textReceta);

        btnGuadarConsulta.setText("Guadar");
        btnGuadarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuadarConsultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(339, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuadarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnGuadarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuadarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuadarConsultaActionPerformed
        LocalDate fechaConsulta;
        String motivo = pacienteFrame.getCampoMotivo();
        String consultaFecha = pacienteFrame.getFechaConsulta();
        try {
            fechaConsulta = LocalDate.parse(consultaFecha);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una fecha de nacimiento válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String diagnostic = textDiagnostico.getText();
        String recet = textReceta.getText();
        
        paciente.addConsultas(new Consulta(motivo, fechaConsulta, diagnostic, recet));
        
        JOptionPane.showMessageDialog(null, "Enfermedad insertada correctamente!", "Información", JOptionPane.INFORMATION_MESSAGE);
         this.dispose();
    }//GEN-LAST:event_btnGuadarConsultaActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuadarConsulta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea textDiagnostico;
    private javax.swing.JTextArea textReceta;
    // End of variables declaration//GEN-END:variables

    String getTextDiagnostico() {
        return textDiagnostico.getText();
    }
    
    String getTextReceta() {
        return textReceta.getText();
    }
    
    
}
