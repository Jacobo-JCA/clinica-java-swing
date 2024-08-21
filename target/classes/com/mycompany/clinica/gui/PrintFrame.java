
package com.mycompany.clinica.gui;

import com.mycompany.clinica.data.ConnectionDB;
import com.mycompany.clinica.model.Paciente;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.WindowConstants;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author jacob
 */
public class PrintFrame extends javax.swing.JFrame {

    Paciente paciente;
    
    public PrintFrame(Paciente paciente) {
        this.paciente = paciente;
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
            Connection conn = ConnectionDB.getInstance();
            
            JasperReport report = null;
            //InputStream inputStream = getClass().getResourceAsStream("/com/mycompany/clinica/report/recet.jrxml");
            report = (JasperReport) JRLoader.loadObject(getClass().getResource("/com/mycompany/clinica/report/recet.jasper"));
            
            //report = JasperCompileManager.compileReport(inputStream);
            
            String sqlQuery = "SELECT p.nombre AS nombre, p.cedula AS cedula, c.diagnostico AS diagnostico, c.receta AS receta, c.indicaciones AS indicaciones "
                    + "FROM paciente p INNER JOIN consulta c ON p.id_paciente = c.id_paciente WHERE p.id_paciente=?";
            
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, paciente.getIdPaciente());
            ResultSet rs = ps.executeQuery();
            
            
            
            JRDataSource dataSource = new JRResultSetDataSource(rs);
            
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("nombre", paciente.getNombre());
            parameters.put("cedula", paciente.getCedula()); 
            parameters.put("diagnostico", paciente.getListConsultas().get(0).getDiagnostico()); 
            parameters.put("receta", paciente.getListConsultas().get(0).getReceta()); 
            parameters.put("indicaciones", paciente.getListConsultas().get(0).getIndicaciones());
            parameters.put("logo", this.getClass().getResourceAsStream("/images/logo.jpeg")); 
            
            JasperPrint jprint = JasperFillManager.fillReport(report, parameters, dataSource);
            //JasperPrintManager.printReport(jprint, true);
            
            JasperViewer viewer = new JasperViewer(jprint, true);
            viewer.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            viewer.setVisible(true);
            
            
        } catch (JRException jr) {
            jr.printStackTrace();
        } catch (SQLException sq) {
            sq.printStackTrace();
        }

    }//GEN-LAST:event_btnImprimirRecetaActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimirReceta;
    // End of variables declaration//GEN-END:variables
}
