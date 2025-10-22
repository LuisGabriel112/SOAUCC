package com.example.sistema_arrendamiento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sistema_arrendamiento.models.ClienteModel;
import com.example.sistema_arrendamiento.models.TipoCliente;
import com.example.sistema_arrendamiento.repositories.IClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private IClienteRepository clienteRepository;

    public List<ClienteModel> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteModel>  getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public ClienteModel saveCliente(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public List<ClienteModel> getClienteByTipo(TipoCliente tipo) {
        return clienteRepository.findAllByTipo(tipo);
    }

    
}