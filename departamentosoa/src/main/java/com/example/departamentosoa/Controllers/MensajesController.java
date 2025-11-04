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

import com.example.demo.Models.MensajesModel;
import com.example.demo.Services.MensajesService;

@RestController
@RequestMapping("/mensajes")
public class MensajesController {
    
    @Autowired
    private MensajesService gestorMensajes;

    // Crear mensaje
    @PostMapping
    public MensajesModel crearMensaje(@RequestBody MensajesModel cuerpoMensaje){
        return gestorMensajes.guardarMensaje(cuerpoMensaje);
    }

    // Actualizar mensaje
    @PutMapping("/{id}")
    public ResponseEntity<MensajesModel> actualizarMensaje(@RequestBody MensajesModel cuerpoMensaje, @PathVariable Long id){
        return gestorMensajes.obtenerMensajePorId(id)
            .map(actual -> {
                actual.setRemitente(cuerpoMensaje.getRemitente());
                actual.setDestinatario(cuerpoMensaje.getDestinatario());
                actual.setAsunto(cuerpoMensaje.getAsunto());
                actual.setContenido(cuerpoMensaje.getContenido());
                actual.setLeido(cuerpoMensaje.getLeido());
                MensajesModel persistido = gestorMensajes.guardarMensaje(actual);
                return ResponseEntity.ok(persistido);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar mensaje
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMensaje(@PathVariable Long id){
        return gestorMensajes.obtenerMensajePorId(id)
            .map(ignorado -> {
                gestorMensajes.eliminarMensaje(id);
                return ResponseEntity.noContent().build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Listar todos los mensajes
    @GetMapping()
    public List<MensajesModel> listarMensajes(){
        return gestorMensajes.obtenerTodosLosMensajes();
    }

    // Obtener mensaje por id
    @GetMapping("/{id}")
    public ResponseEntity<MensajesModel> obtenerMensaje(@PathVariable Long id){
        return gestorMensajes.obtenerMensajePorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
