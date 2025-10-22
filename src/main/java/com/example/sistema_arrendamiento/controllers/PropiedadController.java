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

import com.example.sistema_arrendamiento.models.PropiedadesModel;
import com.example.sistema_arrendamiento.models.TipoPropiedad;
import com.example.sistema_arrendamiento.services.PropiedadService;



@RestController
@RequestMapping("/api/propiedades")
public class PropiedadController {
    
    @Autowired
    private PropiedadService propiedadService;

    @GetMapping
    public List<PropiedadesModel> getAllPropiedades() {
        return propiedadService.getAllPropiedades();
    }

    //Propiedad por ID
    //... Similar a ClienteController
    @GetMapping("/{id}")
    public ResponseEntity<PropiedadesModel> getPropiedadesById(@PathVariable Long id) {
        Optional<PropiedadesModel> propiedad = propiedadService.getPropiedadById(id);
        return propiedad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<PropiedadesModel>> getPropiedadesByTipo(@PathVariable TipoPropiedad tipo){
        List<PropiedadesModel> propiedades = propiedadService.getPropiedadesByTipo(tipo);
        if (propiedades.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(propiedades);
        }
    }
    // Crear nueva propiedad
    @PostMapping
    public PropiedadesModel createPropiedad(@RequestBody PropiedadesModel propiedad) {
        return propiedadService.savePropiedad(propiedad);
    }

    // Actualizar propiedad existente
    @PutMapping("/{id}")
    public ResponseEntity<PropiedadesModel> updatePropiedad(@PathVariable Long id, @RequestBody PropiedadesModel propiedadDetails) {
        Optional<PropiedadesModel> propiedadOptional = propiedadService.getPropiedadById(id);
        if (propiedadOptional.isPresent()) {
            PropiedadesModel propiedadToUpdate = propiedadOptional.get();
            propiedadToUpdate.setDireccion(propiedadDetails.getDireccion());
            propiedadToUpdate.setTipo(propiedadDetails.getTipo());
            // Actualizar otros campos según sea necesario

            PropiedadesModel updatedPropiedad = propiedadService.savePropiedad(propiedadToUpdate);
            return ResponseEntity.ok(updatedPropiedad);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una propiedad
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropiedad(@PathVariable Long id) {
        Optional<PropiedadesModel> propiedad = propiedadService.getPropiedadById(id);
        if (propiedad.isPresent()) {
            propiedadService.deletePropiedad(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
    
    

    

