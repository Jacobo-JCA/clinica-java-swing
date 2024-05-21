
package com.mycompany.clinica.gui;
import com.mycompany.clinica.model.Enfermedades;
import com.mycompany.clinica.model.Paciente;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author jacob
 */
public class EnfermedadesFrame extends javax.swing.JDialog {

    private Paciente paciente;
    private String enfermedad;
    //Comprobando data
    //private String info = "hola";
    private Map<String, String> descripcionesEnfermedades = new HashMap<>();
    
    public EnfermedadesFrame(java.awt.Frame parent, boolean modal, Paciente paciente) {
        super(parent, modal);
        this.paciente = paciente;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        btnGuardarPatologico = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Descripcion:");

        descripcion.setColumns(20);
        descripcion.setRows(5);
        jScrollPane1.setViewportView(descripcion);

        btnGuardarPatologico.setText("Guardar");
        btnGuardarPatologico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPatologicoActionPerformed(evt);
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
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnGuardarPatologico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(btnGuardarPatologico, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarPatologicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPatologicoActionPerformed
        //System.out.println("valor: " + this.info);
        String descript = descripcion.getText();
        if (this.enfermedad.equals("patologico")) {
            descripcionesEnfermedades.put("patologico", descript);
            JOptionPane.showMessageDialog(null, "Descripción de patológico guardada.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else if (this.enfermedad.equals("no patologico")) {
            descripcionesEnfermedades.put("no patologico", descript);
            JOptionPane.showMessageDialog(null, "Descripción de no patológico guardada.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else if (this.enfermedad.equals("clinico")) {
            descripcionesEnfermedades.put("clinico", descript);
            JOptionPane.showMessageDialog(null, "Descripción de clínico guardada.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else if (this.enfermedad.equals("quirurjico")) {
            descripcionesEnfermedades.put("quirurjico", descript);
            JOptionPane.showMessageDialog(null, "Descripción de quirúrgico guardada.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else if (this.enfermedad.equals("hereditario")) {
            if (descripcionesEnfermedades.size() >= 4) {
                descripcionesEnfermedades.put("hereditario", descript);
                JOptionPane.showMessageDialog(null, "Descripción hereditario guardada.", "Información", JOptionPane.INFORMATION_MESSAGE);
                this.agregarEnfermedadesPaciente();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese todas las enfermedades!!.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Tipo de enfermedad no reconocido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        descripcion.setText("");
    }//GEN-LAST:event_btnGuardarPatologicoActionPerformed

    private void agregarEnfermedadesPaciente() {
        String patologico = descripcionesEnfermedades.get("patologico");
        String noPatologico = descripcionesEnfermedades.get("no patologico");
        String clinico = descripcionesEnfermedades.get("clinico");
        String quirurjico = descripcionesEnfermedades.get("quirurjico");
        String hereditario = descripcionesEnfermedades.get("hereditario");
        

        if (patologico == null || noPatologico == null || clinico == null || quirurjico == null || hereditario == null) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todas las descripciones de enfermedades.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Enfermedades enfermedad = new Enfermedades(patologico, noPatologico, clinico, quirurjico, hereditario);

        paciente.addEnfermedad(enfermedad);
        JOptionPane.showMessageDialog(null, "Enfermedades agregadas al paciente.", "Información", JOptionPane.INFORMATION_MESSAGE);

        descripcionesEnfermedades.clear();
    }
    
    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarPatologico;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
