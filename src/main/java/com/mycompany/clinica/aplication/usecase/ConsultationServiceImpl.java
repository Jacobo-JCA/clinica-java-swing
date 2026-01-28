package com.mycompany.clinica.aplication.usecase;
import com.mycompany.clinica.aplication.util.ValidationUtil;
import com.mycompany.clinica.infrastructure.execption.NegocioException;
import com.mycompany.clinica.domain.entity.Consultation;
import com.mycompany.clinica.domain.repo.ConsultationRepo;
import java.time.LocalDate;
import java.util.List;
import com.mycompany.clinica.domain.service.ConsultationService;

public class ConsultationServiceImpl implements ConsultationService {
    private final ConsultationRepo consultationRepo;
    
    public ConsultationServiceImpl(ConsultationRepo consultationRepo) {
        this.consultationRepo = consultationRepo;
    }
    
    @Override
    public String validateFields(Consultation consultation) {
        StringBuilder mensajeError = new StringBuilder();
        if (ValidationUtil.isNullOrBlank(consultation.getReasonConsultation())) {
            mensajeError.append("El motivo de consulta no puede estar vacío.");
        }       
        if (consultation.getConsultationDate().isAfter(LocalDate.now())) {
            mensajeError.append("La fecha de consulta no puede ser en el futuro.");
        }        
        if (ValidationUtil.isNullOrBlank(consultation.getDiagnosis())) {
            mensajeError.append("El diagnóstico no puede estar vacía.");
        }
        if (ValidationUtil.isNullOrBlank(consultation.getPrescription())) {
            mensajeError.append("La receta no puede estar vacia.");
        }
        if (ValidationUtil.isNullOrBlank(consultation.getInstructions())) {
            mensajeError.append("Las indicaciones no pueden estar vacias.");
        } 
        return mensajeError.isEmpty() ? " " : mensajeError.toString();
    }
    
    @Override
    public int save(Consultation consultation, int medicalAppointmentId) {
        String mensaje = validateFields(consultation);
        if (mensaje.isBlank()) {
            throw new NegocioException(mensaje);
        }
        return consultationRepo.insertConsultation(consultation, medicalAppointmentId);
    }
    
    @Override
    public List<Consultation> getConsultationByPatient(int medicalAppointmentId) {
        return consultationRepo.getConsultationsByPatient(medicalAppointmentId);
    }
}
