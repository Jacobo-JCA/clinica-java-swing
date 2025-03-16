
package com.mycompany.clinica.view.gui;
import com.mycompany.clinica.model.entity.Enfermedades;
import com.mycompany.clinica.model.entity.Paciente;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.swing.JOptionPane;

/**
 *
 * @author jacob
 */
public class EnfermedadesFrame extends javax.swing.JDialog {
    private Paciente paciente;
    private String enfermedad;
    private PacienteFrame pacienteFrame;
    private Map<String, String> descripcionesEnfermedades;
    
    public EnfermedadesFrame(java.awt.Frame parent, boolean modal, PacienteFrame pacienteFrame) {
        super(parent, modal);
        this.pacienteFrame = pacienteFrame;
        this.descripcionesEnfermedades = new HashMap<>();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        btnGuardarEnfermedad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Descripcion:");

        descripcion.setColumns(20);
        descripcion.setRows(5);
        jScrollPane1.setViewportView(descripcion);

        btnGuardarEnfermedad.setText("Guardar");
        btnGuardarEnfermedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEnfermedadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarEnfermedad, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnGuardarEnfermedad, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /*
    private void btnGuardarEnfermedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEnfermedadActionPerformed
        String descript = descripcion.getText();
        if (this.enfermedad.equals("patologico")) {
            descripcionesEnfermedades.put("patologico", descript);
            JOptionPane.showMessageDialog(null, "Descripción de patológico guardada.", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else if (this.enfermedad.equals("no patologico")) {
            descripcionesEnfermedades.put("no patologico", descript);
            JOptionPane.showMessageDialog(null, "Descripción de no patológico guardada.", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else if (this.enfermedad.equals("clinico")) {
            descripcionesEnfermedades.put("clinico", descript);
            JOptionPane.showMessageDialog(null, "Descripción de clínico guardada.", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else if (this.enfermedad.equals("quirurjico")) {
            descripcionesEnfermedades.put("quirurjico", descript);
            JOptionPane.showMessageDialog(null, "Descripción de quirúrgico guardada.", "Información", JOptionPane.INFORMATION_MESSAGE);
            if (pacienteFrame.pacienteSeleccionado != null) {
                this.agregarEnfermedadesPaciente();
                this.dispose();
            }
            this.dispose();
            
        } else if (this.enfermedad.equals("hereditario")) {
            if (pacienteFrame.pacienteSeleccionado == null) {
                descripcionesEnfermedades.put("hereditario", descript);
                this.agregarEnfermedadesPaciente();
                this.dispose();
            }          
        } else {
            JOptionPane.showMessageDialog(null, "Tipo de enfermedad no reconocido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        descripcion.setText("");
    }//GEN-LAST:event_btnGuardarEnfermedadActionPerformed
    */
    private void btnGuardarEnfermedadActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        String descript = descripcion.getText();
        Map<String, String> messages = Map.of(
                "patologico", "Descripción de patologico guardada",
                "no patologico", "Descripción de no patológico guardada.",
                "clinico", "Descripción de clínico guardada.",
                "quirurjico", "Descripción de quirúrgico guardada.",
                "hereditario", "Descripción de hereditario guardada."
        );
        if(messages.containsKey(this.enfermedad)) {
            descripcionesEnfermedades.put(this.enfermedad, descript);
            mostrarMensaje(messages.get(this.enfermedad), "Información", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            
//            if(this.enfermedad.equals("quirurjico") && pacienteFrame.pacienteSeleccionado != null) {
//                this.agregarEnfermedadesPaciente();
//            }
            
//            if(this.enfermedad.equals("hereditario") && pacienteFrame.pacienteSeleccionado == null) {
//                this.agregarEnfermedadesPaciente();
//                this.dispose();
//            }
        } else {
            mostrarMensaje("Tipo de enfermedad no reconocido", "Error", JOptionPane.ERROR_MESSAGE);
        }
        descripcion.setText("");
    }
    
    private void agregarEnfermedadesPaciente() {
        String patologico = obtenerDescripcionEnfermedad(enfermedad);
        String noPatologico = obtenerDescripcionEnfermedad(enfermedad);
        String clinico = obtenerDescripcionEnfermedad(enfermedad);
        String quirurjico = obtenerDescripcionEnfermedad(enfermedad);
        String hereditario = obtenerDescripcionEnfermedad(enfermedad);
        
        /*String patologico = descripcionesEnfermedades.get("patologico");
        String noPatologico = descripcionesEnfermedades.get("no patologico");
        String clinico = descripcionesEnfermedades.get("clinico");
        String quirurjico = descripcionesEnfermedades.get("quirurjico");
        String hereditario = descripcionesEnfermedades.get("hereditario");
        
        if (patologico == null && !paciente.getListEnfermedades().isEmpty()) {
            patologico = paciente.getListEnfermedades().get(0).getPatologico();
        } else if(noPatologico == null && !paciente.getListEnfermedades().isEmpty()) {
            noPatologico = paciente.getListEnfermedades().get(0).getNoPatologico();
        } else if(clinico == null && !paciente.getListEnfermedades().isEmpty()) {
            clinico = paciente.getListEnfermedades().get(0).getClinico();
        } else if(quirurjico == null && !paciente.getListEnfermedades().isEmpty()) {
            quirurjico = paciente.getListEnfermedades().get(0).getQuirurjico();
        }  else if(hereditario == null && !paciente.getListEnfermedades().isEmpty()) {
            hereditario = paciente.getListEnfermedades().get(0).getHereditario();
        }*/
        Enfermedades enfermedad = new Enfermedades(patologico, noPatologico, clinico, quirurjico, hereditario);
        paciente.addEnfermedad(enfermedad);
        mostrarMensaje("Enfermedades agregadas al paciente.", "Información", JOptionPane.INFORMATION_MESSAGE);
        descripcionesEnfermedades.clear();
    }
    
    private String obtenerDescripcionEnfermedad(String clave) {
        String descripcion = this.descripcionesEnfermedades.get(clave);
        return descripcion;
    }
    
    private void mostrarMensaje(String mensaje, String titulo, int tipoMensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, tipoMensaje);
    }
    
    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarEnfermedad;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
