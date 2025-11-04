package com.example.demo.controllers;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.DisponibilidadModel;
import com.example.demo.Services.DisponibilidadService;

@RestController
@RequestMapping("/disponibilidad")
public class DisponibilidadController {

    @Autowired
    private DisponibilidadService gestorDisponibilidad;

    // Crear disponibilidad
    @PostMapping
    public DisponibilidadModel crearDisponibilidad(@RequestBody DisponibilidadModel cuerpoDisponibilidad){
        return gestorDisponibilidad.guardarDisponibilidad(cuerpoDisponibilidad);
    }

    // Actualizar disponibilidad
    @PutMapping("/{id}")
    public ResponseEntity<DisponibilidadModel> actualizarDisponibilidad(@RequestBody DisponibilidadModel cuerpoDisponibilidad, @PathVariable Long id){
        return gestorDisponibilidad.obtenerDisponibilidadPorId(id)
            .map(actual -> {
                actual.setFecha(cuerpoDisponibilidad.getFecha());
                actual.setDisponible(cuerpoDisponibilidad.getDisponible());
                actual.setPrecioEspecial(cuerpoDisponibilidad.getPrecioEspecial());
                DisponibilidadModel persistida = gestorDisponibilidad.guardarDisponibilidad(actual);
                return ResponseEntity.ok(persistida);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar disponibilidad
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDisponibilidad(@PathVariable Long id){
        return gestorDisponibilidad.obtenerDisponibilidadPorId(id)
            .map(ignorada -> {
                gestorDisponibilidad.eliminarDisponibilidad(id);
                return ResponseEntity.noContent().build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Comprobar disponibilidad 
    @GetMapping("/check")
    public ResponseEntity<List<DisponibilidadModel>> comprobarDisponibilidad(
        @RequestParam Long propiedadId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaEntrada,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSalida) {
        List<DisponibilidadModel> disponibles = gestorDisponibilidad.comprobarDisponibilidad(
                propiedadId,
                fechaEntrada,
                fechaSalida
        );
        return ResponseEntity.ok(disponibles);
    }

    // Listar todas las disponibilidades
    @GetMapping()
    public List<DisponibilidadModel> listarDisponibilidades(){
        return gestorDisponibilidad.obtenerTodasLasDisponibilidades();
    }

    // Obtener disponibilidad por id
    @GetMapping("/{id}")
    public ResponseEntity<DisponibilidadModel> obtenerDisponibilidad(@PathVariable Long id){
        return gestorDisponibilidad.obtenerDisponibilidadPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
