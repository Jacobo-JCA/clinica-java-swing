package com.mycompany.clinica.presentation.controller;

import com.mycompany.clinica.presentation.view.utils.GenericSwingWorker;
import com.mycompany.clinica.domain.entity.Patient;
import com.mycompany.clinica.presentation.view.PatientFrame;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import com.mycompany.clinica.domain.service.PatientService;
import com.mycompany.clinica.infrastructure.execption.DatabaseException;
import com.mycompany.clinica.infrastructure.execption.ValidationException;

public class PatientController {
    private final PatientService patientService;
    private final PatientFrame patientFrame;
//    private final SesionContexto sesionContexto;

    public PatientController(PatientService patientService, PatientFrame patientFrame) {
        this.patientService = patientService;
        this.patientFrame = patientFrame;
        this.patientFrame.addSelectedButtonListener(e -> loadPatient());
        this.patientFrame.addSaveButtonListener(e -> {
            saveOrUpdatePatient();
        });
    }

    public void getAllPatients() {
        GenericSwingWorker<List<Patient>> worker = new GenericSwingWorker<>(
                () -> {
                    List<Patient> patients = patientService.getAllPatients();
                    return patients;
                },
                pacientes -> patientFrame.updateTable(pacientes),
                e -> patientFrame.showError(e.getMessage())
        );
        worker.execute();
    }
    
    private Optional<Patient> convertFieldsToEntity() {
        List<String> fieldsPatient = patientFrame.getFieldsPatient();
        if (fieldsPatient.stream().anyMatch(String::isEmpty)) {
            return Optional.empty();
        }

        try {
            LocalDate dateBirth = LocalDate.parse(fieldsPatient.get(9));
            Patient patient = new Patient.Builder()
                    .dni(fieldsPatient.get(0))
                    .firstName(fieldsPatient.get(1))
                    .lastName(fieldsPatient.get(2))
                    .address(fieldsPatient.get(3))
                    .email(fieldsPatient.get(4))
                    .gender(fieldsPatient.get(5))
                    .medicalRecordNumber(Integer.parseInt(fieldsPatient.get(6)))
                    .city(fieldsPatient.get(7))
                    .state(fieldsPatient.get(8))
                    .dateOfBirth(dateBirth)
                    .phoneNumber(fieldsPatient.get(10))
                    .occupation(fieldsPatient.get(11))
                    .build();
            return Optional.of(patient);
        } catch (DateTimeParseException e) {
            patientFrame.showError("La fecha de nacimiento es inválida o no se a ingresado el campo..");
            return Optional.empty();
        }
    }
    
    private int selectionPatientTable() {
        int patientId = patientFrame.getSelectedPatientId();
        return patientId;
    }

    private void loadPatient() {
        int patientId = selectionPatientTable();
        if (patientId == -1) {
            patientFrame.showError("Seleccione un paciente!!");
            return;
        }
        Patient patient = patientService.getPatientById(patientId);
        if (patient == null) {
            patientFrame.showError("Paciente no encontrado!!");
            return;
        }
        patientFrame.showPatientDetails(patient);
    }

    private void savePatient() {
        try {
            Patient patient = convertFieldsToEntity().orElseThrow(()
                    -> new IllegalArgumentException("Datos Inválidos, todos los campos son obligatorios"));
            patientService.savePatient(patient);
            patientFrame.showConfirmationSuccessful("Paciente guardado correctamente");
        } catch (ValidationException | IllegalArgumentException | DatabaseException e) {
            patientFrame.showError("Error al guardar el paciente: " + e.getMessage());
        }
    }

    private void updatePatient() {
        int patientId = selectionPatientTable();
        if (patientId == -1) {
            patientFrame.showError("Seleccione un paciente!!");
            return;
        }
        try {
            Patient patient = convertFieldsToEntity().orElseThrow(()
                    -> new IllegalArgumentException("Datos Inválidos, todos los campos son obligatorios"));
            patient.setPatientId(patientId);
            patientService.updatePatient(patient);
            patientFrame.showConfirmationSuccessful("Paciente actualizado correctamente");
            patientFrame.updateRow(patient);
        } catch (DatabaseException | IllegalArgumentException e) {
            patientFrame.showError("Error al actualizar: " + e.getMessage());
        }
    }
    
    private void saveOrUpdatePatient() {
        int selectionId = selectionPatientTable();
        if (selectionId == -1) {
            savePatient();
        } else {
            updatePatient();
        }
    }

//    public void buscarPacientes(String campo) {
//        try {
//            List<Patient> pacientes = pacienteService.obtenerPacientesPorCampo(campo);
//            vistaPaciente.actualizarTabla(pacientes);
//        } catch (Exception e) {
//            MensajeInformativo.mostrarError("Error en la busqueda: " + e.getMessage());
//        }
//    }
//    public void eliminarPaciente(int idPaciente) {
//        pacienteService.eliminar(idPaciente);
//        vistaPaciente.actualizarTabla(pacienteService.obtenerPacientes());
//    }
}
