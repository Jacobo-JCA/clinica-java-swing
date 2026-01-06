package com.mycompany.clinica.view.gui;

import com.mycompany.clinica.common.GenericSwingWorker;
import com.mycompany.clinica.common.SesionContexto;
import com.mycompany.clinica.controller.RegistroControllerCentral;
import com.mycompany.clinica.entity.Consulta;
import com.mycompany.clinica.entity.Enfermedades;
import com.mycompany.clinica.entity.SignosVitales;
import com.mycompany.clinica.common.MensajeInformativo;
import com.mycompany.clinica.view.gui.utils.HistorialData;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class HistorialFrame extends javax.swing.JFrame {
    private final RegistroControllerCentral registroCentral;
    private final DefaultTableModel modeloTablaSignos = new DefaultTableModel();
    private final DefaultTableModel modeloTablaConsulta = new DefaultTableModel();
    private final DefaultTableModel modeloTablaEnfermedad = new DefaultTableModel();

    public HistorialFrame(RegistroControllerCentral registroCentral) {
        initComponents();
        this.registroCentral = registroCentral;
        configurarTablas();
        cargarDatos();
        this.setLocationRelativeTo(null);
    }

    private void configurarTablas() {
        modeloTablaConsulta.setColumnIdentifiers(new Object[]{
            "Fecha Consulta", "Motivo de Consulta", "Diagnostico", "Receta", "Indicaciones"
        });
        tablaConsultas.setModel(modeloTablaConsulta);

        // Tabla de Signos Vitales
        modeloTablaSignos.setColumnIdentifiers(new Object[]{
            "Presion Arterial", "Frecuencia Cardiaca", "Frecuencia Respiratoria",
            "Temperatura", "Peso", "Talla", "Descripcion", "IMC"
        });
        tablaSignos.setModel(modeloTablaSignos);

        // Tabla de Enfermedades
        modeloTablaEnfermedad.setColumnIdentifiers(new Object[]{
            "Patologico", "No patologico", "Clinico", "Quirurjico", "Hereditario"
        });
        tablaEnfermedades.setModel(modeloTablaEnfermedad);
    }

    private void cargarDatos() {
        SesionContexto sesionContexto = registroCentral.getSesionContexto();
        if (sesionContexto.getPaciente() == null) {
            MensajeInformativo.mostrarError("No hay paciente seleccionado");
            return;
        }
        int idPaciente = sesionContexto.getPaciente().getIdPaciente();
        GenericSwingWorker<HistorialData> worker = new GenericSwingWorker<>(
                () -> {
                    HistorialData data = new HistorialData();
                    data.setConsultas(registroCentral.obtenerConsultasConSignos(idPaciente));
                    data.setEnfermedades(registroCentral.obtenerEnfermedadesPaciente(idPaciente));
                    return data;
                },
                data -> {
                    limpiarTablas();
                    cargarConsultas(data.getConsultas());
                    cargarSignosVitales(data.getConsultas());
                    cargarEnfermedades(data.getEnfermedades());
                },
                e -> MensajeInformativo.mostrarError("Error al cargar datos: " + e.getMessage())
        );
        worker.execute();
    }
    
    private void limpiarTablas() {
        modeloTablaConsulta.setRowCount(0);
        modeloTablaSignos.setRowCount(0);
        modeloTablaEnfermedad.setRowCount(0);
    }

    private void cargarConsultas(List<Consulta> consultas) {
        modeloTablaConsulta.setRowCount(0);
        for (Consulta consulta : consultas) {
            modeloTablaConsulta.addRow(new Object[]{
                consulta.getFechaConsulta(),
                consulta.getMotivoConsulta(),
                consulta.getDiagnostico(),
                consulta.getReceta(),
                consulta.getIndicaciones()
            });
        }
    }

    private void cargarSignosVitales(List<Consulta> consultas) {
        modeloTablaSignos.setRowCount(0);
        for (Consulta consulta : consultas) {
            for (SignosVitales signos : consulta.getSignosVitales()) {
                modeloTablaSignos.addRow(new Object[]{
                    signos.getPresionArterial(),
                    signos.getFrecuenciaCardiaca(),
                    signos.getFrecuenciaRespiratoria(),
                    signos.getTemperatura(),
                    signos.getPeso(),
                    signos.getTalla(),
                    signos.getDescripcion(),
                    signos.getImc()
                });
            }
        }
    }

    private void cargarEnfermedades(List<Enfermedades> enfermedades) {
        modeloTablaEnfermedad.setRowCount(0);
        for (Enfermedades enfermedad : enfermedades) {
            modeloTablaEnfermedad.addRow(new Object[]{
                enfermedad.getPatologico(),
                enfermedad.getNoPatologico(),
                enfermedad.getClinico(),
                enfermedad.getQuirurjico(),
                enfermedad.getHereditario()
            });
        }
    }

//    private void cargarModeloConsulta() {
//        modeloTablaConsulta.addColumn("Fecha Consulta");
//        modeloTablaConsulta.addColumn("Motivo de Consulta");
//        modeloTablaConsulta.addColumn("Diagnostico");
//        modeloTablaConsulta.addColumn("Receta");
//        modeloTablaConsulta.addColumn("Indicaciones");
//
//        int numConsulta = paciente.getListConsultas().size();
//        modeloTablaConsulta.setNumRows(numConsulta);
//
//        for (int i = 0; i < numConsulta; i++) {
//            Consulta consulta = paciente.getListConsultas().get(i);
//
//            modeloTablaConsulta.setValueAt(consulta.getFechaConsulta(), i, 0);
//            modeloTablaConsulta.setValueAt(consulta.getMotivoConsulta(), i, 1);
//            modeloTablaConsulta.setValueAt(consulta.getDiagnostico(), i, 2);
//            modeloTablaConsulta.setValueAt(consulta.getReceta(), i, 3);
//            modeloTablaConsulta.setValueAt(consulta.getIndicaciones(), i, 4);
//        }
//    }
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
