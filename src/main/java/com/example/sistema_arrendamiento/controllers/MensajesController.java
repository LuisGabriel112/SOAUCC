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

import com.example.sistema_arrendamiento.models.MensajesModel;
import com.example.sistema_arrendamiento.services.MensajesService;

@RestController
@RequestMapping("/api/mensajes")
public class MensajesController {

    @Autowired
    private MensajesService mensajesService;

    // Obtener todos los mensajes
    @GetMapping
    public List<MensajesModel> getAllMensajes() {
        return mensajesService.getAllMensajes();
    }

    // Obtener un mensaje por ID
    @GetMapping("/{id}")
    public ResponseEntity<MensajesModel> getMensajeById(@PathVariable Long id) {
        Optional<MensajesModel> mensaje = mensajesService.getMensajeById(id);
        return mensaje.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo mensaje
    @PostMapping
    public MensajesModel createMensaje(@RequestBody MensajesModel mensaje) {
        return mensajesService.saveMensaje(mensaje);
    }

    // Actualizar un mensaje existente
    @PutMapping("/{id}")
    public ResponseEntity<MensajesModel> updateMensaje(@PathVariable Long id, @RequestBody MensajesModel mensajeDetails) {
        Optional<MensajesModel> mensajeOptional = mensajesService.getMensajeById(id);
        if (mensajeOptional.isPresent()) {
            MensajesModel mensajeToUpdate = mensajeOptional.get();
            mensajeToUpdate.setContenido(mensajeDetails.getContenido());
            mensajeToUpdate.setRemitente(mensajeDetails.getRemitente());
            mensajeToUpdate.setDestinatario(mensajeDetails.getDestinatario());
            mensajeToUpdate.setReservacion(mensajeDetails.getReservacion());
            mensajeToUpdate.setAsunto(mensajeDetails.getAsunto());
            mensajeToUpdate.setLeido(mensajeDetails.getLeido());
            mensajeToUpdate.setFechaEnvio(mensajeDetails.getFechaEnvio());
            // Actualizar otros campos según sea necesario

            MensajesModel updatedMensaje = mensajesService.saveMensaje(mensajeToUpdate);
            return ResponseEntity.ok(updatedMensaje);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMensaje(@PathVariable Long id) {
        Optional<MensajesModel> mensaje = mensajesService.getMensajeById(id);
        if (mensaje.isPresent()) {
            mensajesService.deleteMensaje(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}