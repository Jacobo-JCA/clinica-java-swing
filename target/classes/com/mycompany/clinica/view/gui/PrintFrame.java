package com.mycompany.clinica.view.gui;

import com.mycompany.clinica.common.MensajeInformativo;
import com.mycompany.clinica.common.SesionContexto;
import com.mycompany.clinica.entity.Consulta;
import com.mycompany.clinica.entity.Paciente;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jacob
 */
public class PrintFrame extends javax.swing.JFrame {
    public PrintFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnImprimirReceta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnImprimirReceta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnImprimirReceta.setText("Click para Imprimir");
        btnImprimirReceta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirRecetaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(btnImprimirReceta, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(301, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(btnImprimirReceta, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(247, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImprimirRecetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirRecetaActionPerformed
        try {
            SesionContexto sesionContexto = SesionContexto.getInstance();
            Paciente paciente = sesionContexto.getPaciente();
            JasperReport report = null;
            Map<String, Object> parameters = new HashMap<>();
            if(paciente == null) {
                MensajeInformativo.mostrarError("No hay Paciente!");
            }
            List<Consulta> consultas = paciente.getListConsultas();
            if (consultas == null || consultas.isEmpty()) {
                MensajeInformativo.mostrarError("No hay consultas registradas para este paciente.");
                return;
            }
            consultas.sort(Comparator.comparing(Consulta::getFechaConsulta).reversed());
            report = (JasperReport) JRLoader.loadObject(getClass().getResource("/com/mycompany/clinica/report/recet.jasper"));        
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(consultas);
            
            Consulta ultimaConsulta = consultas.get(0);
            parameters.put("nombre", paciente.getNombre());
            parameters.put("cedula", paciente.getCedula());
            parameters.put("diagnostico", ultimaConsulta.getDiagnostico());
            parameters.put("receta", ultimaConsulta.getReceta());
            parameters.put("indicaciones", ultimaConsulta.getIndicaciones());
            parameters.put("logo", this.getClass().getResourceAsStream("/images/logo.jpeg"));
            JasperPrint jprint = JasperFillManager.fillReport(report, parameters, dataSource);         
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setVisible(true);
        } catch (JRException e) {
            MensajeInformativo.mostrarError("Error! " + e.getMessage());
        }
    }//GEN-LAST:event_btnImprimirRecetaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimirReceta;
    // End of variables declaration//GEN-END:variables
}
