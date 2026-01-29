package com.mycompany.clinica.presentation.view.viewfx;

import com.mycompany.clinica.domain.entity.Patient;
import com.mycompany.clinica.presentation.controller.MedicalAppointmentController;
import com.mycompany.clinica.presentation.controller.PatientController;
import com.mycompany.clinica.infrastructure.di.DependencyInjector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class MainViewFx {
    @FXML private StackPane contentArea;
    @FXML private Button btnPacientes;
    @FXML private Button btnCitas;
    private final DependencyInjector dependencyInjector;
    private PatientController patientController;
    private PatientViewFX patientViewFX;
    
    public MainViewFx(DependencyInjector dependencyInjector) {
        this.dependencyInjector = dependencyInjector;
    }
   
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
            patientViewFX = loader.getController();
            patientController = dependencyInjector.getPatienteController(patientViewFX);
            
            contentArea.getChildren().setAll(view); 
            patientController.getAllPatients();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error cargando vista de pacientes: " + e.getMessage());
        }
    }
    
    private void loadMedicalAppointmentView() {
        if (patientViewFX == null) {
            System.err.println("⚠️ Debe cargar primero la vista de pacientes");
            return;
        }
        
        Patient selected = patientViewFX.getSelectedPatient();
        if (selected == null) {
            patientViewFX.showError("Seleccione un paciente primero");
            return;
        }
        
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/MedicalAppointmentView.fxml")
            );
            Parent view = loader.load();
            
            MedicalAppointmentViewFX appointmentView = loader.getController();
            MedicalAppointmentController controller = dependencyInjector.getAppointmentController(appointmentView, selected.getPatientId());
            appointmentView.setController(controller);
            contentArea.getChildren().setAll(view);       
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error cargando vista de citas: " + e.getMessage());
        }
    }
}