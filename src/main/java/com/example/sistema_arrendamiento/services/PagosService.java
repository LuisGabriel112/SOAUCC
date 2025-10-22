package com.example.sistema_arrendamiento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sistema_arrendamiento.models.PagosModel;
import com.example.sistema_arrendamiento.repositories.IPagosRepository;

@Service
public class PagosService {
    
    @Autowired
    private IPagosRepository pagosRepository;

    public List<PagosModel> getAllPagos() {
        return pagosRepository.findAll();
    }

    public Optional<PagosModel> getPagosById(Long id) {
        return pagosRepository.findById(id);
    }

    public PagosModel savePagos(PagosModel pagos) {
        return pagosRepository.save(pagos);
    }

    public void deletePagos(Long id) {
        pagosRepository.deleteById(id);
    }

    
}