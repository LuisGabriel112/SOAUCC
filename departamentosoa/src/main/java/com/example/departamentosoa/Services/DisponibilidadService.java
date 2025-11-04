package com.example.demo.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.DisponibilidadModel;
import com.example.demo.Models.PropiedadesModel;
import com.example.demo.Repositories.IDisponibilidadRepository;

@Service
public class DisponibilidadService {
    
    @Autowired
    private IDisponibilidadRepository fuenteDisponibilidad;

    @Autowired
    private PropiedadesService propiedadesService;

    // Obtener todas las disponibilidades
    public List<DisponibilidadModel> obtenerTodasLasDisponibilidades() {
        return fuenteDisponibilidad.findAll();
    }

    // Buscar disponibilidad por id
    public Optional<DisponibilidadModel> obtenerDisponibilidadPorId(Long id) {
        return fuenteDisponibilidad.findById(id);
    }

    // Guardar disponibilidad
    public DisponibilidadModel guardarDisponibilidad(DisponibilidadModel disponibilidad) {
        return fuenteDisponibilidad.save(disponibilidad);
    }

    // Eliminar disponibilidad
    public void eliminarDisponibilidad(Long id) {
        fuenteDisponibilidad.deleteById(id);
    }

    // Comprobar disponibilidad
    public List<DisponibilidadModel> comprobarDisponibilidad(Long propiedadId, LocalDate fechaEntrada, LocalDate fechaSalida) {
        PropiedadesModel propiedad = propiedadesService.getPropiedadById(propiedadId)
            .orElseThrow(() -> new RuntimeException("Propiedad no encontrada con ID: " + propiedadId));
        return fuenteDisponibilidad.findAvailableDatesForProperty(
            propiedad,
            fechaEntrada,
            fechaSalida
        );
    }
    
}
