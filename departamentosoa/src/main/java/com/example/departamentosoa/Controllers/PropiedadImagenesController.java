package com.example.demo.Controllers;

    
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

import com.example.demo.Models.PropiedadImagenesModel;
import com.example.demo.Services.PropiedadImagenesService;

@RestController
@RequestMapping("/propiedad-imagenes")
public class PropiedadImagenesController {
    
    @Autowired
    private PropiedadImagenesService gestorImagenes;

    // Crear imagen
    @PostMapping
    public PropiedadImagenesModel crearImagen(@RequestBody PropiedadImagenesModel cuerpoImagen) {
        return gestorImagenes.guardarImagen(cuerpoImagen);
    }

    // Actualizar imagen
    @PutMapping("/{id}")
    public ResponseEntity<PropiedadImagenesModel> actualizarImagen(@RequestBody PropiedadImagenesModel cuerpoImagen, @PathVariable Long id) {
        return gestorImagenes.obtenerImagenPorId(id)
            .map(actual -> {
                actual.setUrlImagen(cuerpoImagen.getUrlImagen());
                actual.setOrden(cuerpoImagen.getOrden());
                actual.setEsPrincipal(cuerpoImagen.getEsPrincipal());
                PropiedadImagenesModel persistida = gestorImagenes.guardarImagen(actual);
                return ResponseEntity.ok(persistida);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar imagen
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarImagen(@PathVariable Long id) {
        return gestorImagenes.obtenerImagenPorId(id)
            .map(ignorada -> {
                gestorImagenes.eliminarImagen(id);
                return ResponseEntity.noContent().build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Listar imágenes
    @GetMapping
    public List<PropiedadImagenesModel> listarImagenes() {
        return gestorImagenes.obtenerTodasLasImagenes();
    }

    // Obtener imagen por id
    @GetMapping("/{id}")
    public ResponseEntity<PropiedadImagenesModel> obtenerImagen(@PathVariable Long id) {
        return gestorImagenes.obtenerImagenPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
