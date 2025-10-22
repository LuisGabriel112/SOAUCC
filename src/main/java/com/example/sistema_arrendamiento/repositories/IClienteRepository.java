package com.example.sistema_arrendamiento.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sistema_arrendamiento.models.ClienteModel;
import com.example.sistema_arrendamiento.models.TipoCliente;



@Repository
public interface IClienteRepository extends JpaRepository<ClienteModel, Long> {
    List<ClienteModel> findAllByTipo(TipoCliente tipo);

    
}
