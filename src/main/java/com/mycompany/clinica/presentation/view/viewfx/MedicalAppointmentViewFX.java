package com.mycompany.clinica.presentation.view.viewfx;

import com.mycompany.clinica.domain.entity.MedicalAppointment;
import com.mycompany.clinica.presentation.controller.MedicalAppointmentController;
import com.mycompany.clinica.presentation.view.contract.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MedicalAppointmentViewFX implements View {
    private MedicalAppointmentController controller; 
    @FXML private DatePicker campoFecha;
    @FXML private ComboBox<LocalTime> campoHora;
    @FXML private TextField campoTipoCita;

    @FXML private Button btnGuardar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnSeleccionar;
    @FXML private Button btnEliminar;

    @FXML private TableView<MedicalAppointment> tablaCitas;
    @FXML private TableColumn<MedicalAppointment, String> colFecha;
    @FXML private TableColumn<MedicalAppointment, String> colHora;
    @FXML private TableColumn<MedicalAppointment, String> colTipo;

    private final ObservableList<MedicalAppointment> citas =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        tablaCitas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Columnas
        colFecha.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getAppointmentDate()
                                .toLocalDate()
                                .toString()
                )
        );

        colHora.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getAppointmentDate()
                                .toLocalTime()
                                .toString()
                )
        );

        colTipo.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getAppointmentType()
                )
        );

        tablaCitas.setItems(citas);

        cargarHoras();
    }

    public void addSaveButtonListener(EventHandler<ActionEvent> handler) {
        btnGuardar.setOnAction(handler);
    }

    public void addSelectButtonListener(EventHandler<ActionEvent> handler) {
        btnSeleccionar.setOnAction(handler);
    }

    public void addDeleteButtonListener(EventHandler<ActionEvent> handler) {
        btnEliminar.setOnAction(handler);
    }

    public void updateTable(List<MedicalAppointment> appointments) {
        citas.setAll(appointments);
    }

    public LocalDateTime getAppointmentDateTime() {
        LocalDate fecha = campoFecha.getValue();
        LocalTime hora = campoHora.getValue();

        if (fecha == null || hora == null) {
            return null;
        }

        return LocalDateTime.of(fecha, hora);
    }

    public String getAppointmentType() {
        return campoTipoCita.getText();
    }

    private void cargarHoras() {
        for (int h = 8; h <= 18; h++) {
            campoHora.getItems().add(LocalTime.of(h, 0));
            campoHora.getItems().add(LocalTime.of(h, 30));
        }
    }

    public MedicalAppointment getSelectedAppointment() {
        return tablaCitas.getSelectionModel().getSelectedItem();
    }

    @Override
    public void showError(String message) {
        new Alert(Alert.AlertType.ERROR, message).showAndWait();
    }

    @Override
    public void showConfirmationSuccessful(String message) {
        new Alert(Alert.AlertType.INFORMATION, message).showAndWait();
    }

    public void clearForm() {
        campoFecha.setValue(null);
        campoHora.setValue(null);
        campoTipoCita.clear();
    }
    
    public void setController(MedicalAppointmentController controller) {
        this.controller = controller;
    }
}