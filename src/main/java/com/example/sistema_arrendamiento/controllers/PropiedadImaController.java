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

import com.example.sistema_arrendamiento.models.PropiedadImaModel;
import com.example.sistema_arrendamiento.services.PropiedadImaService;

@RestController
@RequestMapping("/api/propiedadima")
public class PropiedadImaController {

    @Autowired
    private PropiedadImaService propiedadImaService;

    // Obtener todas las imágenes de propiedad
    @GetMapping
    public List<PropiedadImaModel> getAllPropiedadesIma() {
        return propiedadImaService.getAllPropiedadesIma();
    }

    // Obtener una imagen de propiedad por ID
    @GetMapping("/{id}")
    public ResponseEntity<PropiedadImaModel> getPropiedadImaById(@PathVariable Long id) {
        Optional<PropiedadImaModel> propiedadIma = propiedadImaService.getPropiedadImaById(id);
        return propiedadIma.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo mensaje
    @PostMapping
    public PropiedadImaModel createPropiedadIma(@RequestBody PropiedadImaModel propiedadIma) {
        return propiedadImaService.savePropiedadIma(propiedadIma);
    }

    // Actualizar una imagen de propiedad existente
    @PutMapping("/{id}")
    public ResponseEntity<PropiedadImaModel> updatePropiedadIma(@PathVariable Long id, @RequestBody PropiedadImaModel propiedadImaDetails) {
        Optional<PropiedadImaModel> propiedadImaOptional = propiedadImaService.getPropiedadImaById(id);
        if (propiedadImaOptional.isPresent()) {
            PropiedadImaModel propiedadImaToUpdate = propiedadImaOptional.get();
            propiedadImaToUpdate.setPropiedad(propiedadImaDetails.getPropiedad());
            propiedadImaToUpdate.setUrlImagen(propiedadImaDetails.getUrlImagen());
            propiedadImaToUpdate.setOrden(propiedadImaDetails.getOrden());
            propiedadImaToUpdate.setEsPrincipal(propiedadImaDetails.getEsPrincipal());
            propiedadImaToUpdate.setFechaSubida(propiedadImaDetails.getFechaSubida());
            // Actualizar otros campos según sea necesario

            PropiedadImaModel updatedPropiedadIma = propiedadImaService.savePropiedadIma(propiedadImaToUpdate);
            return ResponseEntity.ok(updatedPropiedadIma);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropiedadIma(@PathVariable Long id) {
        Optional<PropiedadImaModel> propiedadIma = propiedadImaService.getPropiedadImaById(id);
        if (propiedadIma.isPresent()) {
            propiedadImaService.deletePropiedadIma(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}