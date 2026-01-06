package com.mycompany.clinica.aplication.service.impl;

import com.mycompany.clinica.infrastructure.execption.NegocioException;
import com.mycompany.clinica.infrastructure.repository.BaseDatos;
import com.mycompany.clinica.domain.entity.VitalSigns;
import com.mycompany.clinica.aplication.service.CrudSignosVitales;
import java.util.List;

public class SignosVitalesService implements CrudSignosVitales {
    private final BaseDatos db = BaseDatos.getInstanceDB();
    
    @Override
    public String validarCampos(VitalSigns signosVitales) {
        StringBuilder mensajeError = new StringBuilder();
        if (signosVitales.getPeso() <= 0) {
            mensajeError.append("El peso debe ser un valor positivo.\n");
        }
        if (signosVitales.getTalla() <= 0) {
            mensajeError.append("La talla debe ser un valor positivo.\n");
        }
        return mensajeError.length() > 0 ? mensajeError.toString() : null;
    }
    
    @Override
    public int guardar(VitalSigns signosVitales, int idConsulta) {
        String mensaje = validarCampos(signosVitales);
        if(mensaje != null) {
            throw new NegocioException(mensaje);
        }
        return db.insertSignosVitales(signosVitales, idConsulta);
    }

    @Override
    public List<VitalSigns> obtenerSignosVitales(int idConsulta) {
        return db.obtenerSignosVitales(idConsulta);
    }
}