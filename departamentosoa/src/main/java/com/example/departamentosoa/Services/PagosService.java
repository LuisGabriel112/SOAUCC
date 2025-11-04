package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.PagosModel;
import com.example.demo.Repositories.IPagosRepository;

@Service
public class PagosService {
    
    @Autowired
    private IPagosRepository fuentePagos;

    // Obtener todos los pagos
    public List<PagosModel> obtenerTodosLosPagos() {
        return fuentePagos.findAll();
    }

    // Buscar pago por id
    public Optional<PagosModel> obtenerPagoPorId(Long id) {
        return fuentePagos.findById(id);
    }

    // Guardar pago
    public PagosModel guardarPago(PagosModel pago) {
        return fuentePagos.save(pago);
    }

    // Eliminar pago
    public void eliminarPago(Long id) {
        fuentePagos.deleteById(id);
    }

    
}
