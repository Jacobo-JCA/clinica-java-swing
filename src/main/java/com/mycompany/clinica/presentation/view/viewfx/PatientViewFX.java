package com.mycompany.clinica.presentation.view.viewfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.mycompany.clinica.domain.entity.Patient;
import com.mycompany.clinica.presentation.view.contract.View;
import javafx.scene.control.TextField;
import java.util.Arrays;

import java.util.List;

public class PatientViewFX implements View {
    // Campos del formulario
    @FXML private TextField campoCedula;
    @FXML private TextField campoNombre;
    @FXML private TextField campoApellido;
    @FXML private TextField campoGenero;
    @FXML private TextField campoFechaNacimiento;
    @FXML private TextField campoCiudad;
    @FXML private TextField campoExpediente;
    @FXML private TextField campoDireccion;
    @FXML private TextField campoEmail;
    @FXML private TextField campoOcupacion;
    @FXML private TextField campoEstado;
    @FXML private TextField campoTelefono;
    // Botones
    @FXML private Button btnGuardar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnEliminar;
    // Tabla de pacientes
    @FXML private TableView<Patient> tablaPaciente;
    @FXML private TableColumn<Patient, Integer> colId;
    @FXML private TableColumn<Patient, String> colNombre;
    @FXML private TableColumn<Patient, String> colApellido;
    @FXML private TableColumn<Patient, String> colGenero;
    @FXML private TableColumn<Patient, String> colCiudad;
    @FXML private TableColumn<Patient, Integer> colExpediente;
    @FXML private TableColumn<Patient, Integer> colEdad;
    // Lista observable para la tabla
    private final ObservableList<Patient> pacientes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        tablaPaciente.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tablaPaciente.setItems(pacientes);
        // Configurar columnas de la tabla
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPatientId()).asObject());
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFirstName()));
        colApellido.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLastName()));
        colGenero.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGender()));
        colCiudad.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCity()));
        colExpediente.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty( data.getValue().getMedicalRecordNumber() ).asObject());
        colEdad.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAge()).asObject());

        tablaPaciente.setItems(pacientes);
    }

    public void addSaveButtonListener(EventHandler<ActionEvent> handler) {
        btnGuardar.setOnAction(handler);
    }
    
    public void addClearButtonListener(EventHandler<ActionEvent> handler) {
        btnLimpiar.setOnAction(handler);
    }

    public void updateTable(List<Patient> patients) {
        pacientes.setAll(patients);
    }
    
    public void addRow(Patient newPatient) {
        pacientes.add(newPatient);
        FXCollections.sort(pacientes, (p1, p2) -> Integer.compare(p2.getPatientId(), p1.getPatientId()));
    }

    @Override
    public void showError(String message) {
        new Alert(Alert.AlertType.ERROR, message).showAndWait();
    }

    @Override
    public void showConfirmationSuccessful(String message) {
        new Alert(Alert.AlertType.INFORMATION, message).showAndWait();
    }

    // Métodos auxiliares para acceder a datos del formulario
    public List<String> getFieldsPatient() {
        List<String> fields = Arrays.asList(
                campoCedula.getText(),
                campoNombre.getText(),
                campoApellido.getText(),
                campoDireccion.getText(),
                campoEmail.getText(),
                campoGenero.getText(),
                campoExpediente.getText(),
                campoCiudad.getText(),
                campoEstado.getText(),
                campoFechaNacimiento.getText(),
                campoTelefono.getText(),
                campoOcupacion.getText()
        );
        return fields;
    }
    
    public void showPatientDetails(Patient patient) {
        campoCedula.setText(patient.getDni());
        campoNombre.setText(patient.getFirstName());
        campoApellido.setText(patient.getLastName());
        campoDireccion.setText(patient.getAddress());
        campoEmail.setText(patient.getEmail());
        campoGenero.setText(patient.getGender());
        campoExpediente.setText(String.valueOf(patient.getMedicalRecordNumber()));
        campoCiudad.setText(patient.getCity());
        campoEstado.setText(patient.getState());
        campoFechaNacimiento.setText(patient.getDateOfBirth().toString());
        campoTelefono.setText(patient.getPhoneNumber());
        campoOcupacion.setText(patient.getOccupation());
    }
    
    public void updateRow(Patient updatedPatient) {
        for (int i = 0; i < pacientes.size(); i++) {
            Patient p = pacientes.get(i);
            if (p.getPatientId() == updatedPatient.getPatientId()) {
                // reemplazar el objeto completo
                pacientes.set(i, updatedPatient);
                break;
            }
        }
    }

    public void clearForm() {
        campoCedula.clear();
        campoNombre.clear();
        campoApellido.clear();
        campoGenero.clear();
        campoFechaNacimiento.clear();
        campoCiudad.clear();
        campoExpediente.clear();
        campoDireccion.clear();
        campoEmail.clear();
        campoOcupacion.clear();
        campoEstado.clear();
        campoTelefono.clear();
    }

    public Patient getSelectedPatient() {
        return tablaPaciente.getSelectionModel().getSelectedItem();
    }
}
