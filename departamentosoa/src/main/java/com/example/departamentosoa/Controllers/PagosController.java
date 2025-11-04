package com.example.demo.Controllers;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.PagosModel;
import com.example.demo.Services.PagosService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/pagos")
public class PagosController {
    
    @Autowired
    private PagosService gestorPagos;

    // Crear pago
    @PostMapping
    public PagosModel crearPago(@RequestBody PagosModel cuerpoPago){
        return gestorPagos.guardarPago(cuerpoPago);
    }

    // Actualizar pago
    @PutMapping("/{id}")
    public ResponseEntity<PagosModel> actualizarPago(@RequestBody PagosModel cuerpoPago, @PathVariable Long id){
        return gestorPagos.obtenerPagoPorId(id)
            .map(actual -> {
                actual.setMonto(cuerpoPago.getMonto());
                actual.setMetodoPago(cuerpoPago.getMetodoPago());
                actual.setEstado(cuerpoPago.getEstado());
                actual.setReferenciaPago(cuerpoPago.getReferenciaPago());
                actual.setDatosPago(cuerpoPago.getDatosPago());
                PagosModel persistido = gestorPagos.guardarPago(actual);
                return ResponseEntity.ok(persistido);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar pago
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPago(@PathVariable Long id){
        return gestorPagos.obtenerPagoPorId(id)
            .map(ignorado -> {
                gestorPagos.eliminarPago(id);
                return ResponseEntity.noContent().build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Listar pagos
    @GetMapping()
    public List<PagosModel> listarPagos(){
        return gestorPagos.obtenerTodosLosPagos();
    }

    // Obtener pago por id
    @GetMapping("/{id}")
    public ResponseEntity<PagosModel> obtenerPago(@PathVariable Long id){
        return gestorPagos.obtenerPagoPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
