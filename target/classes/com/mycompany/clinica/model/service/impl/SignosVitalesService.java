package com.mycompany.clinica.model.service.impl;

import com.mycompany.clinica.model.data.BaseDatos;
import com.mycompany.clinica.model.entity.SignosVitales;
import com.mycompany.clinica.model.service.CrudSignosVitales;

public class SignosVitalesService implements CrudSignosVitales {
    private final BaseDatos db = BaseDatos.getInstanceDB();
    
    @Override
    public String validarCampos(SignosVitales signosVitales) {
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
    public void guardar(SignosVitales signosVitales, int idConsulta) {
        if(validarCampos(signosVitales) == null) {
            db.insertSignosVitales(signosVitales, idConsulta);
            return;
        }
        throw new IllegalArgumentException("Los datos de los signos vitales no son válidos");
    }

    @Override
    public SignosVitales obtenerSignoVitalePorId(int idConsulta) {
       return db.obtenerSignosVitales(idConsulta);
    }
}