package com.mycompany.clinica.presentation.controller;

import com.mycompany.clinica.presentation.view.utils.GenericSwingWorker;
import com.mycompany.clinica.domain.entity.Patient;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import com.mycompany.clinica.domain.service.PatientService;
import com.mycompany.clinica.infrastructure.execption.DatabaseException;
import com.mycompany.clinica.infrastructure.execption.ValidationException;
import com.mycompany.clinica.presentation.view.viewfx.PatientViewFX;

public class PatientController {
    private final PatientService patientService;
    private final PatientViewFX  patientViewFX ;
//    private final SesionContexto sesionContexto;

    public PatientController(PatientService patientService, PatientViewFX patientViewFX) {
        this.patientService = patientService;
        this.patientViewFX = patientViewFX;
        this.patientViewFX.addSaveButtonListener(e -> {
            saveOrUpdatePatient();
        });
        this.patientViewFX.addClearButtonListener(e -> patientViewFX.clearForm());
    }

    public void getAllPatients() {
        GenericSwingWorker<List<Patient>> worker = new GenericSwingWorker<>(
                () -> {
                    List<Patient> patients = patientService.getAllPatients();
                    return patients;
                },
                pacientes -> patientViewFX.updateTable(pacientes),
                e -> patientViewFX.showError(e.getMessage())
        );
        worker.execute();
    }
    
    private Optional<Patient> convertFieldsToEntity() {
        List<String> fieldsPatient = patientViewFX.getFieldsPatient();
        if (fieldsPatient.stream().anyMatch(String::isEmpty)) {
            return Optional.empty();
        }

        try {
            LocalDate dateBirth = LocalDate.parse(fieldsPatient.get(9));
            Patient patient = new Patient.BuilderPatient()
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
            patientViewFX.showError("La fecha de nacimiento es inválida o no se a ingresado el campo..");
            return Optional.empty();
        }
    }
    
    private Optional<Patient> selectionPatientTable() {
        return Optional.ofNullable(patientViewFX.getSelectedPatient());
    }

    private void loadPatient() {
        Optional<Patient> selectedOpt = selectionPatientTable();
        if (selectedOpt.isEmpty()) {
            patientViewFX.showError("Seleccione un paciente!!");
            return;
        }
        Patient patient = patientService.getPatientById(selectedOpt.get().getPatientId());
        if (patient == null) {
            patientViewFX.showError("Paciente no encontrado!!");
            return;
        }
        patientViewFX.showPatientDetails(patient);
    }

    private void savePatient() {
        try {
            Patient patient = convertFieldsToEntity().orElseThrow(()
                    -> new IllegalArgumentException("Datos Inválidos, todos los campos son obligatorios"));
            patientService.savePatient(patient);
            patientViewFX.showConfirmationSuccessful("Paciente guardado correctamente");
            patientViewFX.addRow(patient);
        } catch (ValidationException | IllegalArgumentException | DatabaseException e) {
            patientViewFX.showError("Error al guardar el paciente: " + e.getMessage());
        }
    }

    private void updatePatient(Patient selected) {
        try {
            Patient patient = convertFieldsToEntity()
                    .orElseThrow(() -> new IllegalArgumentException("Datos inválidos, todos los campos son obligatorios"));
            patient.setPatientId(selected.getPatientId());
            patientService.updatePatient(patient);
            patientViewFX.showConfirmationSuccessful("Paciente actualizado correctamente");
            patientViewFX.addRow(patient);
        } catch (DatabaseException | IllegalArgumentException e) {
            patientViewFX.showError("Error al actualizar: " + e.getMessage());
        }
    }
    
    private void saveOrUpdatePatient() {
        Optional<Patient> selectedOpt = selectionPatientTable();
        if (selectedOpt.isPresent()) {
            updatePatient(selectedOpt.get());
        } else {
            savePatient();
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
