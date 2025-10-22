package com.example.sistema_arrendamiento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sistema_arrendamiento.models.PropiedadImaModel;

@Repository
public interface IPropiedadImaRepository extends JpaRepository<PropiedadImaModel, Long> {

    
}
