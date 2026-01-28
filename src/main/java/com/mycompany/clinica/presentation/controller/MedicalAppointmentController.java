package com.mycompany.clinica.presentation.controller;

import com.mycompany.clinica.domain.entity.MedicalAppointment;
import com.mycompany.clinica.domain.service.MedicalAppointmentService;
import com.mycompany.clinica.presentation.view.viewfx.MedicalAppointmentViewFX;

import java.time.LocalDateTime;
import java.util.List;

public class MedicalAppointmentController {
    private final MedicalAppointmentViewFX view;
    private final MedicalAppointmentService medicalAppointmentService;

    private int patientId;

    public MedicalAppointmentController(
            MedicalAppointmentViewFX view,
            MedicalAppointmentService medicalAppointmentService,
            int patientId
    ) {
        this.view = view;
        this.medicalAppointmentService = medicalAppointmentService;
        this.patientId = patientId;

        initListeners();
        loadAppointments();
    }

    private void initListeners() {
        view.addSaveButtonListener(e -> saveAppointment());
        view.addSelectButtonListener(e -> selectAppointment());
        view.addDeleteButtonListener(e -> deleteAppointment());
    }

    private void saveAppointment() {

        LocalDateTime appointmentDate = view.getAppointmentDateTime();
        String appointmentType = view.getAppointmentType();

        // Validaciones
        if (appointmentDate == null) {
            view.showError("Debe seleccionar fecha y hora.");
            return;
        }

        if (appointmentType == null || appointmentType.isBlank()) {
            view.showError("Debe ingresar el tipo de cita.");
            return;
        }

        if (appointmentDate.isBefore(LocalDateTime.now())) {
            view.showError("La cita no puede ser en el pasado.");
            return;
        }

        try {
            MedicalAppointment appointment = new MedicalAppointment(appointmentDate, appointmentType);

            medicalAppointmentService.saveAppointment(appointment, patientId);

            loadAppointments();
            view.clearForm();

            view.showConfirmationSuccessful("Cita médica registrada correctamente.");

        } catch (Exception ex) {
            view.showError(ex.getMessage());
        }
    }

    private void loadAppointments() {
        try {
            List<MedicalAppointment> appointments =
                    medicalAppointmentService.getAppointmentsByPatient(patientId);

            view.updateTable(appointments);

        } catch (Exception ex) {
            view.showError("Error al cargar las citas médicas.");
        }
    }

    private void selectAppointment() {
        MedicalAppointment selected = view.getSelectedAppointment();

        if (selected == null) {
            view.showError("Debe seleccionar una cita.");
            return;
        }

        view.showConfirmationSuccessful("Cita seleccionada correctamente.");
    }

    private void deleteAppointment() {
        view.showError("Funcionalidad no implementada todavía.");
    }
}
