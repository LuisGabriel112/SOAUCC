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

import com.example.sistema_arrendamiento.models.ReseñasModel;
import com.example.sistema_arrendamiento.services.ReseñasService;

@RestController
@RequestMapping("/api/reseñas")
public class ReseñasController {

    @Autowired
    private ReseñasService reseñasService;

    // Obtener todas las reseñas
    @GetMapping
    public List<ReseñasModel> getAllReseñas() {
        return reseñasService.getAllReseñas();
    }

    // Obtener una reseña por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReseñasModel> getReseñaById(@PathVariable Long id) {
        Optional<ReseñasModel> reseña = reseñasService.getReseñaById(id);
        return reseña.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo mensaje
    @PostMapping
    public ReseñasModel createReseña(@RequestBody ReseñasModel reseña) {
        return reseñasService.saveReseña(reseña);
    }

    // Actualizar una reseña existente
    @PutMapping("/{id}")
    public ResponseEntity<ReseñasModel> updateReseña(@PathVariable Long id, @RequestBody ReseñasModel reseñaDetails) {
        Optional<ReseñasModel> reseñaOptional = reseñasService.getReseñaById(id);
        if (reseñaOptional.isPresent()) {
            ReseñasModel reseñaToUpdate = reseñaOptional.get();
            reseñaToUpdate.setReservacion(reseñaDetails.getReservacion());
            reseñaToUpdate.setCalificacionLimpieza(reseñaDetails.getCalificacionLimpieza());
            reseñaToUpdate.setCalificacionUbicacion(reseñaDetails.getCalificacionUbicacion());
            reseñaToUpdate.setCalificacionComunicacion(reseñaDetails.getCalificacionComunicacion());
            reseñaToUpdate.setCalificacionGeneral(reseñaDetails.getCalificacionGeneral());
            reseñaToUpdate.setComentario(reseñaDetails.getComentario());
            reseñaToUpdate.setFechaReseña(reseñaDetails.getFechaReseña());
            reseñaToUpdate.setRespuestaPropietario(reseñaDetails.getRespuestaPropietario());
            reseñaToUpdate.setFechaRespuesta(reseñaDetails.getFechaRespuesta());
            // Actualizar otros campos según sea necesario

            ReseñasModel updatedReseña = reseñasService.saveReseña(reseñaToUpdate);
            return ResponseEntity.ok(updatedReseña);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReseña(@PathVariable Long id) {
        Optional<ReseñasModel> reseña = reseñasService.getReseñaById(id);
        if (reseña.isPresent()) {
            reseñasService.deleteReseña(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}