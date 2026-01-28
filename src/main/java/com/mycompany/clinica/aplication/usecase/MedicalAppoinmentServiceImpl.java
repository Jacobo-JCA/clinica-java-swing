package com.mycompany.clinica.aplication.usecase;

import com.mycompany.clinica.domain.entity.MedicalAppointment;
import com.mycompany.clinica.domain.repo.MedicalAppointmentRepo;
import com.mycompany.clinica.domain.service.MedicalAppointmentService;
import java.util.List;

public class MedicalAppoinmentServiceImpl implements MedicalAppointmentService {
    private final MedicalAppointmentRepo MedicalAppointmentRepo;

    public MedicalAppoinmentServiceImpl(MedicalAppointmentRepo MedicalAppointmentRepo) {
        this.MedicalAppointmentRepo = MedicalAppointmentRepo;
    }
            
    @Override
    public int saveAppointment(MedicalAppointment medicalAppointment, int patientId) {
        return MedicalAppointmentRepo.saveAppointment(medicalAppointment, patientId);
    }

    @Override
    public MedicalAppointment getAppointment(int patientId) {
        return MedicalAppointmentRepo.getAppointment(patientId);
    }

    @Override
    public List<MedicalAppointment> getAppointmentsByPatient(int patientId) {
        return MedicalAppointmentRepo.getAppointmentsByPatient(patientId);
    }

    @Override
    public void updateAppointment(MedicalAppointment appointment) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteAppointment(int appointmentId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
