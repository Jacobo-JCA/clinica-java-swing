package com.mycompany.clinica.view.gui;

import com.mycompany.clinica.config.ControllerFactory;
import com.mycompany.clinica.execption.ManejadorError;
import com.mycompany.clinica.execption.ValidacionException;
import com.mycompany.clinica.model.entity.Consulta;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jacob
 */
public class ConsultaFrame extends javax.swing.JDialog {
    private final ControllerFactory controller = ControllerFactory.getInstance();
    
    public ConsultaFrame(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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
        jScrollPane3 = new javax.swing.JScrollPane();
        txtIndicaciones = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Diagnostico:");

        textDiagnostico.setColumns(20);
        textDiagnostico.setRows(5);
        jScrollPane1.setViewportView(textDiagnostico);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("RP:");

        textReceta.setColumns(20);
        textReceta.setRows(5);
        jScrollPane2.setViewportView(textReceta);

        btnGuadarConsulta.setText("Guadar");
        btnGuadarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuadarConsultaActionPerformed(evt);
            }
        });

        txtIndicaciones.setColumns(20);
        txtIndicaciones.setRows(5);
        jScrollPane3.setViewportView(txtIndicaciones);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Indicaciones");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane3))
                        .addGap(78, 78, 78)
                        .addComponent(btnGuadarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(jLabel3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(49, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuadarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuadarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuadarConsultaActionPerformed
        try {
            Consulta consulta = obtenerCamposConsulta();
            if (consulta != null) {
                JOptionPane.showMessageDialog(null, "Consulta insertada correctamente!", "Información", 
                        JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
        } catch (ValidacionException e) {
            String error = ManejadorError.obtenerMensajeError(e);
            controller.getVistaPaciente().mostrarError(error);
        }
    }//GEN-LAST:event_btnGuadarConsultaActionPerformed

    public Consulta obtenerCamposConsulta() {
        List<String> camposVista = controller.getVistaPaciente().obtenerCamposConsultaVista();
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(camposVista.get(1));
        } catch(DateTimeParseException e) {
            throw new ValidacionException("Formato de fecha inválido. Por favor, use el formato YYYY-MM-dd, o ingrese el campo fecha");
        }
        List<String> campos = Arrays.asList(
            textDiagnostico.getText(),
            textReceta.getText(),
            txtIndicaciones.getText()
        );
        if(campos.stream().anyMatch(String::isEmpty) && camposVista.stream().anyMatch(String::isEmpty)) {
            throw new ValidacionException("Todos los campos son requeridos");
        }
        return new Consulta(
            camposVista.get(0),
            fecha,
            campos.get(0),
            campos.get(1),
            campos.get(2)
        );
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuadarConsulta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea textDiagnostico;
    private javax.swing.JTextArea textReceta;
    private javax.swing.JTextArea txtIndicaciones;
    // End of variables declaration//GEN-END:variables

}
