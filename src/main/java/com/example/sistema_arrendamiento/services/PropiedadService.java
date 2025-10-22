package com.example.sistema_arrendamiento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sistema_arrendamiento.models.PropiedadesModel;
import com.example.sistema_arrendamiento.models.TipoPropiedad;
import com.example.sistema_arrendamiento.repositories.IPropiedadRepository;


@Service
public class PropiedadService {
    
    @Autowired
    private IPropiedadRepository propiedadRepository;

    public List<PropiedadesModel> getAllPropiedades() {
        return propiedadRepository.findAll();
    }

    public Optional<PropiedadesModel> getPropiedadById(Long id) {
        return propiedadRepository.findById(id);
    }

    public PropiedadesModel savePropiedad(PropiedadesModel propiedad) {
        return propiedadRepository.save(propiedad);
    }

    public void deletePropiedad(Long id) {
        propiedadRepository.deleteById(id);
    }
    public List<PropiedadesModel> getPropiedadesByTipo(TipoPropiedad tipo) {
        return propiedadRepository.findByTipo(tipo); 
    }

    
}