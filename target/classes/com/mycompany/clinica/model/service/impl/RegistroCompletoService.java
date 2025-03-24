package com.mycompany.clinica.model.service.impl;

import com.mycompany.clinica.model.entity.Consulta;
import com.mycompany.clinica.model.entity.Enfermedades;
import com.mycompany.clinica.model.entity.Paciente;
import com.mycompany.clinica.model.entity.SignosVitales;
import com.mycompany.clinica.model.service.CrudConsulta;
import com.mycompany.clinica.model.service.CrudEnfermedad;
import com.mycompany.clinica.model.service.CrudPaciente;
import com.mycompany.clinica.model.service.CrudSignosVitales;

public class RegistroCompletoService {
    private CrudPaciente pacienteService;
    private CrudConsulta consultaService;
    private CrudEnfermedad enfermedadService;
    private CrudSignosVitales signosService;

    public RegistroCompletoService(CrudPaciente pacienteService, CrudConsulta consultaService, CrudEnfermedad enfermedadService, CrudSignosVitales signosService) {
        this.pacienteService = pacienteService;
        this.consultaService = consultaService;
        this.enfermedadService = enfermedadService;
        this.signosService = signosService;
    }

    public void guardarRegistroCompleto(Paciente paciente, Consulta consulta, Enfermedades enfermedad, SignosVitales signosVitales) {
        int idPaciente = pacienteService.guardar(paciente);
        int idConsulta = consultaService.guardar(consulta, idPaciente);
        enfermedadService.guardar(enfermedad, idPaciente);
        signosService.guardar(signosVitales, idConsulta);
    }
}
