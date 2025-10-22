package com.example.sistema_arrendamiento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sistema_arrendamiento.models.MensajesModel;

@Repository
public interface IMensajesRepository extends JpaRepository<MensajesModel, Long> {

    
}
