package com.example.sistema_arrendamiento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sistema_arrendamiento.models.PropiedadImaModel;
import com.example.sistema_arrendamiento.repositories.IPropiedadImaRepository;

@Service
public class PropiedadImaService {
    
    @Autowired
    private IPropiedadImaRepository propiedadImaRepository;

    public List<PropiedadImaModel> getAllPropiedadesIma() {
        return propiedadImaRepository.findAll();
    }

    public Optional<PropiedadImaModel> getPropiedadImaById(Long id) {
        return propiedadImaRepository.findById(id);
    }

    public PropiedadImaModel savePropiedadIma(PropiedadImaModel propiedadIma) {
        return propiedadImaRepository.save(propiedadIma);
    }

    public void deletePropiedadIma(Long id) {
        propiedadImaRepository.deleteById(id);
    }

    
}