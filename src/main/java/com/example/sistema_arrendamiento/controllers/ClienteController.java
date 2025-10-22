package com.example.sistema_arrendamiento.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistema_arrendamiento.models.ClienteModel;
import com.example.sistema_arrendamiento.models.TipoCliente;
import com.example.sistema_arrendamiento.services.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Obtener todos los clientes
    @GetMapping
    public List<ClienteModel> getAllClientes() {
        return clienteService.getAllClientes();
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> getClienteById(@PathVariable Long id) {
        Optional <ClienteModel> cliente = clienteService.getClienteById(id);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());        
    }
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ClienteModel>> getClienteByTipo(@PathVariable TipoCliente tipo){
        List<ClienteModel> clientes = clienteService.getClienteByTipo(tipo);
        if (clientes.isEmpty()) {
        return ResponseEntity.notFound().build();
    } else {
        return ResponseEntity.ok(clientes);
    }
    }
    // Crear un nuevo cliente
    @PostMapping
    public ClienteModel createCliente(@RequestBody ClienteModel cliente) {
        return clienteService.saveCliente(cliente);
    }

    // Actualizar un cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> updateCliente(@PathVariable Long id, @RequestBody ClienteModel clienteDetails) {
        Optional<ClienteModel> clienteOptional = clienteService.getClienteById(id);
        if (clienteOptional.isPresent()) {
            ClienteModel clienteToUpdate = clienteOptional.get();
            clienteToUpdate.setNombres(clienteDetails.getNombres());
            /*clienteToUpdate.setPropiedades(clienteDetails.getPropiedades());
            clienteToUpdate.setReservaciones(clienteDetails.getReservaciones());
            clienteToUpdate.setMensajesRemitente(clienteDetails.getMensajesRemitente());
            clienteToUpdate.setMensajesDestinatario(clienteDetails.getMensajesDestinatario()); */
            clienteToUpdate.setTipo(clienteDetails.getTipo());
            clienteToUpdate.setIne(clienteDetails.getIne());
            clienteToUpdate.setApellidoPat(clienteDetails.getApellidoPat());
            clienteToUpdate.setApellidoMat(clienteDetails.getApellidoMat());
            clienteToUpdate.setEmail(clienteDetails.getEmail());
            clienteToUpdate.setTelefono(clienteDetails.getTelefono());
            clienteToUpdate.setDireccion(clienteDetails.getDireccion());
            clienteToUpdate.setFechaNacimiento(clienteDetails.getFechaNacimiento());
            clienteToUpdate.setFechaRegistro(clienteDetails.getFechaRegistro());
            clienteToUpdate.setStatus(clienteDetails.getStatus());
            clienteToUpdate.setPassword(clienteDetails.getPassword());
            clienteToUpdate.setUltimoAcceso(clienteDetails.getUltimoAcceso());
            // Actualizar otros campos según sea necesario

            ClienteModel updatedCliente = clienteService.saveCliente(clienteToUpdate);
            return ResponseEntity.ok(updatedCliente);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        Optional<ClienteModel> cliente = clienteService.getClienteById(id);
        if (cliente.isPresent()) {
            clienteService.deleteCliente(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}