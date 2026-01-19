package com.mycompany.clinica.aplication.usecase;

import com.mycompany.clinica.infrastructure.execption.NegocioException;
import com.mycompany.clinica.domain.entity.VitalSigns;
import com.mycompany.clinica.domain.repo.VitalSignsRepo;
import java.util.List;
import com.mycompany.clinica.domain.service.VitalSignsService;

public class VitalSignsServiceImpl implements VitalSignsService {
    private final VitalSignsRepo vitalSignsRepo;
    
    public VitalSignsServiceImpl(VitalSignsRepo vitalSignsRepo) {
        this.vitalSignsRepo = vitalSignsRepo;
    }
    
    @Override
    public String validarCampos(VitalSigns vitalSigns) {
        StringBuilder mensajeError = new StringBuilder();
        if (vitalSigns.getWeight() <= 0) {
            mensajeError.append("El peso debe ser un valor positivo.\n");
        }
        if (vitalSigns.getHeight() <= 0) {
            mensajeError.append("La talla debe ser un valor positivo.\n");
        }
        return mensajeError.length() > 0 ? mensajeError.toString() : null;
    }
    
    @Override
    public int guardar(VitalSigns vitalSigns, int consultationId) {
        String mensaje = validarCampos(vitalSigns);
        if(mensaje != null) {
            throw new NegocioException(mensaje);
        }
        return vitalSignsRepo.insertVitalSigns(vitalSigns, consultationId);
    }

    @Override
    public List<VitalSigns> obtenerSignosVitales(int consultationId) {
        return vitalSignsRepo.getAllVitalSigns(consultationId);
    }
}