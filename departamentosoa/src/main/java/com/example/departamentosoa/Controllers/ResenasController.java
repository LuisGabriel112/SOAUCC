package com.example.demo.Controllers;

import java.util.List;

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

import com.example.demo.Models.ResenasModel;
import com.example.demo.Services.ResenasService;

@RestController
@RequestMapping("/resenas")
public class ResenasController {

    @Autowired
    private ResenasService gestorResenas;

    // Crear una reseña nueva
    @PostMapping
    public ResenasModel crearResena(@RequestBody ResenasModel cuerpoResena) {
        return gestorResenas.guardarResena(cuerpoResena);
    }

    // Actualizar una reseña existente
    @PutMapping("/{id}")
    public ResponseEntity<ResenasModel> actualizarResena(@RequestBody ResenasModel cuerpoResena, @PathVariable Long id) {
        return gestorResenas.obtenerResenaPorId(id)
            .map(resenaActual -> {
                resenaActual.setCalificacionLimpieza(cuerpoResena.getCalificacionLimpieza());
                resenaActual.setCalificacionUbicacion(cuerpoResena.getCalificacionUbicacion());
                resenaActual.setCalificacionComunicacion(cuerpoResena.getCalificacionComunicacion());
                resenaActual.setCalificacionGeneral(cuerpoResena.getCalificacionGeneral());
                resenaActual.setComentario(cuerpoResena.getComentario());
                resenaActual.setRespuestaPropietario(cuerpoResena.getRespuestaPropietario());
                ResenasModel resenaPersistida = gestorResenas.guardarResena(resenaActual);
                return ResponseEntity.ok(resenaPersistida);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar una reseña por id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarResena(@PathVariable Long id) {
        return gestorResenas.obtenerResenaPorId(id)
            .map(ignorada -> {
                gestorResenas.eliminarResena(id);
                return ResponseEntity.noContent().build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Listar todas las reseñas
    @GetMapping
    public List<ResenasModel> listarResenas() {
        return gestorResenas.obtenerTodasLasResenas();
    }

    // Obtener una reseña por id
    @GetMapping("/{id}")
    public ResponseEntity<ResenasModel> obtenerResena(@PathVariable Long id) {
        return gestorResenas.obtenerResenaPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
