package com.example.sistema_arrendamiento.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistema_arrendamiento.models.DisponibilidadModel;
import com.example.sistema_arrendamiento.services.DisponibilidadService;

@RestController
@RequestMapping("/api/disponibilidad")
public class DisponibilidadController {

    @Autowired
    private DisponibilidadService disponibilidadService;

    // Obtener todos los registros de disponibilidad
    @GetMapping
    public List<DisponibilidadModel> getAllDisponibilidad() {
        return disponibilidadService.getAllDisponibilidad();
    }

    // Obtener un registro de disponibilidad por ID
    @GetMapping("/{id}")
    public ResponseEntity<DisponibilidadModel> getDisponibilidadById(@PathVariable Long id) {
        Optional<DisponibilidadModel> disponibilidad = disponibilidadService.getDisponibilidadById(id);
        return disponibilidad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo registro de disponibilidad
    @PostMapping
    public DisponibilidadModel createDisponibilidad(@RequestBody DisponibilidadModel disponibilidad) {
        return disponibilidadService.saveDisponibilidad(disponibilidad);
    }

    // Actualizar un registro de disponibilidad existente
    @PutMapping("/{id}")
    public ResponseEntity<DisponibilidadModel> updateDisponibilidad(@PathVariable Long id, @RequestBody DisponibilidadModel disponibilidadDetails) {
        Optional<DisponibilidadModel> disponibilidadOptional = disponibilidadService.getDisponibilidadById(id);
        if (disponibilidadOptional.isPresent()) {
            DisponibilidadModel disponibilidadToUpdate = disponibilidadOptional.get();
            disponibilidadToUpdate.setId(disponibilidadDetails.getId());
            disponibilidadToUpdate.setPropiedad(disponibilidadDetails.getPropiedad());
            disponibilidadToUpdate.setFecha(disponibilidadDetails.getFecha());
            disponibilidadToUpdate.setDisponible(disponibilidadDetails.getDisponible());
            disponibilidadToUpdate.setPrecioEspecial(disponibilidadDetails.getPrecioEspecial());
            // Actualizar otros campos según sea necesario

            DisponibilidadModel updatedDisponibilidad = disponibilidadService.saveDisponibilidad(disponibilidadToUpdate);
            return ResponseEntity.ok(updatedDisponibilidad);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    // Eliminar una disponibilidad
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisponibilidad(@PathVariable Long id) {
        Optional<DisponibilidadModel> disponibilidad = disponibilidadService.getDisponibilidadById(id);
        if (disponibilidad.isPresent()) {
            disponibilidadService.deleteDisponibilidad(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}