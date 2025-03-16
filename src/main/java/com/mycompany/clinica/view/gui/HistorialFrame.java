
package com.mycompany.clinica.view.gui;

import com.mycompany.clinica.model.entity.Consulta;
import com.mycompany.clinica.model.entity.Enfermedades;
import com.mycompany.clinica.model.entity.Paciente;
import com.mycompany.clinica.model.entity.SignosVitales;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class HistorialFrame extends javax.swing.JFrame {
    Paciente paciente;
    DefaultTableModel modeloTablaSignos = new DefaultTableModel();
    DefaultTableModel modeloTablaConsulta = new DefaultTableModel();
    DefaultTableModel modeloTablaEnfermedad = new DefaultTableModel();
    
    
    public HistorialFrame() {
            initComponents();
            this.setLocationRelativeTo(null);
    }
    
    private void cargarModeloSignosVitales() {  
        modeloTablaSignos.addColumn("Presion Arterial");
        modeloTablaSignos.addColumn("Frecuencia Cardiaca");
        modeloTablaSignos.addColumn("Frecuencia Respiratoria");
        modeloTablaSignos.addColumn("Temperatura");
        modeloTablaSignos.addColumn("Peso");
        modeloTablaSignos.addColumn("Talla");
        modeloTablaSignos.addColumn("Descripcion");
        modeloTablaSignos.addColumn("IMC");
        int numSignos = paciente.getListConsultas().size(); //2
        modeloTablaSignos.setNumRows(numSignos);
        
        for (int i = 0; i < numSignos; i++) {
            SignosVitales signos = paciente.getListConsultas().get(i).getSignosVitales();
 
            modeloTablaSignos.setValueAt(signos.getPresionArterial(), i, 0);
            modeloTablaSignos.setValueAt(signos.getFrecuenciaCardiaca(), i, 1);
            modeloTablaSignos.setValueAt(signos.getFrecuenciaRespiratoria(), i, 2);
            modeloTablaSignos.setValueAt(signos.getTemperatura(), i, 3);
            modeloTablaSignos.setValueAt(signos.getPeso(), i, 4);
            modeloTablaSignos.setValueAt(signos.getTalla(), i, 5);
            modeloTablaSignos.setValueAt(signos.getDescripcion(), i, 6);
            modeloTablaSignos.setValueAt(signos.getImc(), i, 7);  
        }
    }
    
    private void cargarModeloConsulta() {        
        modeloTablaConsulta.addColumn("Fecha Consulta");
        modeloTablaConsulta.addColumn("Motivo de Consulta");
        modeloTablaConsulta.addColumn("Diagnostico");
        modeloTablaConsulta.addColumn("Receta");
        modeloTablaConsulta.addColumn("Indicaciones");
        
        int numConsulta = paciente.getListConsultas().size();
        modeloTablaConsulta.setNumRows(numConsulta);
        
        for (int i = 0; i < numConsulta; i++) {
            Consulta consulta = paciente.getListConsultas().get(i);
            
            modeloTablaConsulta.setValueAt(consulta.getFechaConsulta(), i, 0);
            modeloTablaConsulta.setValueAt(consulta.getMotivoConsulta(), i, 1);
            modeloTablaConsulta.setValueAt(consulta.getDiagnostico(), i, 2);
            modeloTablaConsulta.setValueAt(consulta.getReceta(), i, 3);
            modeloTablaConsulta.setValueAt(consulta.getIndicaciones(), i, 4);
        }
    }
    
    public void cargarEnfermedades() {
        modeloTablaEnfermedad.addColumn("Patologico");
        modeloTablaEnfermedad.addColumn("No patologico");
        modeloTablaEnfermedad.addColumn("Clinico");
        modeloTablaEnfermedad.addColumn("Quirurjico");
        modeloTablaEnfermedad.addColumn("Hereditario");
        int numEnfermedad = paciente.getListEnfermedades().size();
        modeloTablaEnfermedad.setNumRows(numEnfermedad);
        
        for (int i = 0; i < numEnfermedad; i++) {
            Enfermedades enfermedad = paciente.getListEnfermedades().get(i);
            modeloTablaEnfermedad.setValueAt(enfermedad.getPatologico(), i, 0);
            modeloTablaEnfermedad.setValueAt(enfermedad.getNoPatologico(), i, 1);
            modeloTablaEnfermedad.setValueAt(enfermedad.getClinico(), i, 2);
            modeloTablaEnfermedad.setValueAt(enfermedad.getQuirurjico(), i, 3);
            modeloTablaEnfermedad.setValueAt(enfermedad.getHereditario(), i, 4);
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaSignos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaConsultas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaEnfermedades = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Informacion Enfermedades");

        tablaSignos.setModel(modeloTablaSignos);
        jScrollPane1.setViewportView(tablaSignos);

        tablaConsultas.setModel(modeloTablaConsulta);
        jScrollPane2.setViewportView(tablaConsultas);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Informacion Consulta");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Informacion Signos Vitales");

        tablaEnfermedades.setModel(modeloTablaEnfermedad);
        jScrollPane3.setViewportView(tablaEnfermedades);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void mostrarDatos() {   
        cargarModeloConsulta();
        cargarModeloSignosVitales();
        cargarEnfermedades();
   
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        if (this.paciente != null) {
            mostrarDatos();  // Cargar los datos solo si el paciente no es null
        }
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaConsultas;
    private javax.swing.JTable tablaEnfermedades;
    private javax.swing.JTable tablaSignos;
    // End of variables declaration//GEN-END:variables
}
