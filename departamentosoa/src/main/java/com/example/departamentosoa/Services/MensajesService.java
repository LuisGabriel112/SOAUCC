package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.MensajesModel;
import com.example.demo.Repositories.IMensajesRepository;

@Service
public class MensajesService {
    
    @Autowired
    private IMensajesRepository fuenteMensajes;

    // Obtener todos los mensajes
    public List<MensajesModel> obtenerTodosLosMensajes() {
        return fuenteMensajes.findAll();
    }

    // Buscar mensaje por id
    public Optional<MensajesModel> obtenerMensajePorId(Long id) {
        return fuenteMensajes.findById(id);
    }

    // Guardar mensaje
    public MensajesModel guardarMensaje(MensajesModel mensaje) {
        return fuenteMensajes.save(mensaje);
    }

    // Eliminar mensaje
    public void eliminarMensaje(Long id) {
        fuenteMensajes.deleteById(id);
    }

}
