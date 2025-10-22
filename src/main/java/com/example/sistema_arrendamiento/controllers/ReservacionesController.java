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

import com.example.sistema_arrendamiento.models.ReservacionesModel;
import com.example.sistema_arrendamiento.services.ReservacionesService;

@RestController
@RequestMapping("/api/reservaciones")
public class ReservacionesController {

    @Autowired
    private ReservacionesService reservacionesService;

    // Obtener todas las reservaciones
    @GetMapping
    public List<ReservacionesModel> getAllReservaciones() {
        return reservacionesService.getAllReservaciones();
    }

    // Obtener una reservación por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservacionesModel> getReservacionById(@PathVariable Long id) {
        Optional<ReservacionesModel> reservacion = reservacionesService.getReservacionById(id);
        return reservacion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo mensaje
    @PostMapping
    public ReservacionesModel createReservacion(@RequestBody ReservacionesModel reservacion) {
        return reservacionesService.saveReservacion(reservacion);
    }

    // Actualizar una reservación existente
    @PutMapping("/{id}")
    public ResponseEntity<ReservacionesModel> updateReservacion(@PathVariable Long id, @RequestBody ReservacionesModel reservacionDetails) {
        Optional<ReservacionesModel> reservacionOptional = reservacionesService.getReservacionById(id);
        if (reservacionOptional.isPresent()) {
            ReservacionesModel reservacionToUpdate = reservacionOptional.get();
            reservacionToUpdate.setPago(reservacionDetails.getPago());
            reservacionToUpdate.setResena(reservacionDetails.getResena());
            reservacionToUpdate.setPropiedad(reservacionDetails.getPropiedad());
            reservacionToUpdate.setCliente(reservacionDetails.getCliente());
            reservacionToUpdate.setFechaEntrada(reservacionDetails.getFechaEntrada());
            reservacionToUpdate.setFechaSalida(reservacionDetails.getFechaSalida());
            reservacionToUpdate.setNumeroHuespedes(reservacionDetails.getNumeroHuespedes());
            reservacionToUpdate.setPrecioTotal(reservacionDetails.getPrecioTotal());
            reservacionToUpdate.setEstado(reservacionDetails.getEstado());
            reservacionToUpdate.setFechaReservacion(reservacionDetails.getFechaReservacion());
            reservacionToUpdate.setNotas(reservacionDetails.getNotas());
            reservacionToUpdate.setCodigoReserva(reservacionDetails.getCodigoReserva());
            reservacionToUpdate.setFechaCheckin(reservacionDetails.getFechaCheckin());
            reservacionToUpdate.setFechaCheckout(reservacionDetails.getFechaCheckout());
            
            // Actualizar otros campos según sea necesario

            ReservacionesModel updatedReservacion = reservacionesService.saveReservacion(reservacionToUpdate);
            return ResponseEntity.ok(updatedReservacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservacion(@PathVariable Long id) {
        Optional<ReservacionesModel> reservacion = reservacionesService.getReservacionById(id);
        if (reservacion.isPresent()) {
            reservacionesService.deleteReservacion(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}