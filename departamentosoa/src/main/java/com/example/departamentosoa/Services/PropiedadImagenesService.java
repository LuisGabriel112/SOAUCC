package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.PropiedadImagenesModel;
import com.example.demo.Repositories.IPropiedadImagenesRepository;

@Service
public class PropiedadImagenesService {
    
    @Autowired
    private IPropiedadImagenesRepository fuenteImagenes;

    // Obtener todas las imágenes
    public List<PropiedadImagenesModel> obtenerTodasLasImagenes() {
        return fuenteImagenes.findAll();
    }

    // Buscar imagen por id
    public Optional<PropiedadImagenesModel> obtenerImagenPorId(Long id) {
        return fuenteImagenes.findById(id);
    }

    // Guardar imagen
    public PropiedadImagenesModel guardarImagen(PropiedadImagenesModel imagen) {
        return fuenteImagenes.save(imagen);
    }

    // Eliminar imagen
    public void eliminarImagen(Long id) {
        fuenteImagenes.deleteById(id);
    }

}
