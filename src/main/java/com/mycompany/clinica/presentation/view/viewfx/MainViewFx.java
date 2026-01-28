package com.mycompany.clinica.presentation.view.viewfx;

import com.mycompany.clinica.domain.entity.Patient;
import com.mycompany.clinica.presentation.controller.MedicalAppointmentController;
import com.mycompany.clinica.presentation.controller.PatientController;
import com.mycompany.clinica.presentation.view.utils.ControllerFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class MainViewFx {
    @FXML private StackPane contentArea;
    @FXML private Button btnPacientes;
    @FXML private Button btnCitas;
    
    private PatientController patientController;
    private PatientViewFX patientViewFX; // 👈 Guardar referencia
    
    @FXML
    public void initialize() {
        btnPacientes.setOnAction(e -> loadPatientView());
        btnCitas.setOnAction(e -> loadMedicalAppointmentView());
        addHoverEffect(btnPacientes, "#667eea", "#5568d3");
        addHoverEffect(btnCitas, "#F8F9FA", "#E2E8F0");
    }
    
    private void addHoverEffect(Button button, String normalColor, String hoverColor) {
        String originalStyle = button.getStyle();
        button.setOnMouseEntered(e
                -> button.setStyle(originalStyle.replace(normalColor, hoverColor))
        );
        button.setOnMouseExited(e
                -> button.setStyle(originalStyle)
        );
    }
    
    private void loadPatientView() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/PatientView.fxml")
            );
            Parent view = loader.load();
            
            // 👇 GUARDAR LA REFERENCIA
            patientViewFX = loader.getController();
            
            // Crear el controller
            patientController = ControllerFactory.getInstance()
                .getPatienteController(patientViewFX);
            
            // Cambiar la vista
            contentArea.getChildren().setAll(view);
            
            // 👇 CARGAR DATOS DE LA BASE DE DATOS
            patientController.getAllPatients();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error cargando vista de pacientes: " + e.getMessage());
        }
    }
    
    private void loadMedicalAppointmentView() {
        // Verificar que se haya cargado la vista de pacientes
        if (patientViewFX == null) {
            System.err.println("⚠️ Debe cargar primero la vista de pacientes");
            return;
        }
        
        // Obtener el paciente seleccionado
        Patient selected = patientViewFX.getSelectedPatient();
        if (selected == null) {
            patientViewFX.showError("Seleccione un paciente primero");
            return;
        }
        
        int patientId = selected.getPatientId();
        
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/MedicalAppointmentView.fxml")
            );
            Parent view = loader.load();
            
            MedicalAppointmentViewFX appointmentView = loader.getController();
            
            MedicalAppointmentController controller = new MedicalAppointmentController(
                appointmentView,
                ControllerFactory.getInstance().getAppointmentService(),
                patientId
            );
            
            appointmentView.setController(controller);
            contentArea.getChildren().setAll(view);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error cargando vista de citas: " + e.getMessage());
        }
    }
}