package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.ResenasModel;
import com.example.demo.Repositories.IResenasRepository;

@Service
public class ResenasService {

    @Autowired
    private IResenasRepository fuenteResenas;

    // Guardar o actualizar una reseña
    public ResenasModel guardarResena(ResenasModel entidadResena) {
        return fuenteResenas.save(entidadResena);
    }

    // Obtener todas las reseñas
    public List<ResenasModel> obtenerTodasLasResenas() {
        return fuenteResenas.findAll();
    }

    // Buscar una reseña por id
    public Optional<ResenasModel> obtenerResenaPorId(Long identificador) {
        return fuenteResenas.findById(identificador);
    }

    // Eliminar una reseña por id
    public void eliminarResena(Long identificador) {
        fuenteResenas.deleteById(identificador);
    }
}
