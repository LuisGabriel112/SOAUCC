package com.example.demo.Controllers;

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

import com.example.demo.Models.PropiedadesModel;
import com.example.demo.Models.PropiedadesModel.TipoPropiedad;
import com.example.demo.Models.UsuariosModel;
import com.example.demo.Services.PropiedadesService;
import com.example.demo.Services.UsuariosService;

@RestController
@RequestMapping("/propiedades")
public class PropiedadesController {
    
    @Autowired
    private PropiedadesService gestorPropiedades;

    @Autowired
    private UsuariosService gestorUsuarios;

    // Crear propiedad
    @PostMapping
    public PropiedadesModel crearPropiedad(@RequestBody PropiedadesModel cuerpoPropiedad){
        return gestorPropiedades.guardarPropiedad(cuerpoPropiedad);
    }

    // Actualizar propiedad
    @PutMapping("/{id}")
    public ResponseEntity<PropiedadesModel> actualizarPropiedad(@RequestBody PropiedadesModel cuerpoPropiedad, @PathVariable Long id){
        return gestorPropiedades.obtenerPropiedadPorId(id)
            .map(actual -> {
                actual.setDireccion(cuerpoPropiedad.getDireccion());
                actual.setCiudad(cuerpoPropiedad.getCiudad());
                actual.setEstado(cuerpoPropiedad.getEstado());
                actual.setCodigoPostal(cuerpoPropiedad.getCodigoPostal());
                actual.setPrecioNoche(cuerpoPropiedad.getPrecioNoche());
                actual.setDescripcion(cuerpoPropiedad.getDescripcion());
                actual.setNumeroHabitaciones(cuerpoPropiedad.getNumeroHabitaciones());
                actual.setNumeroBaños(cuerpoPropiedad.getNumeroBaños());
                actual.setMetrosCuadrados(cuerpoPropiedad.getMetrosCuadrados());
                actual.setComodidades(cuerpoPropiedad.getComodidades());
                actual.setNormasCasa(cuerpoPropiedad.getNormasCasa());
                actual.setCapacidadPersonas(cuerpoPropiedad.getCapacidadPersonas());
                actual.setPais(cuerpoPropiedad.getPais());
                actual.setEstadoProvincia(cuerpoPropiedad.getEstadoProvincia());
                actual.setLatitud(cuerpoPropiedad.getLatitud());
                actual.setLongitud(cuerpoPropiedad.getLongitud());
                actual.setTipo(cuerpoPropiedad.getTipo());
                actual.setTitulo(cuerpoPropiedad.getTitulo());
                PropiedadesModel persistida = gestorPropiedades.guardarPropiedad(actual);
                return ResponseEntity.ok(persistida);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar propiedad
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPropiedad(@PathVariable Long id){
        return gestorPropiedades.obtenerPropiedadPorId(id)
            .map(ignorada -> {
                gestorPropiedades.eliminarPropiedad(id);
                return ResponseEntity.noContent().build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 1. Endpoint para /api/propiedades/tipo/{tipo}
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<PropiedadesModel>> getPropiedadesByTipo(@PathVariable String tipo) {
        try {
            TipoPropiedad tipoEnum = TipoPropiedad.valueOf(tipo.toUpperCase());
            List<PropiedadesModel> propiedades = gestorPropiedades.getPropiedadesByTipo(tipoEnum);
            
            if (propiedades.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(propiedades);
        } catch (IllegalArgumentException e) {
            // Manejo si el valor del path no es un Enum válido
            return ResponseEntity.badRequest().build();
        }
    }

    // 2. Endpoint para /api/propiedades/propietario/{propietarioId}
    @GetMapping("/propietario/{propietarioId}")
    public ResponseEntity<List<PropiedadesModel>> getPropiedadesByPropietario(@PathVariable Long propietarioId) {
        
        Optional<UsuariosModel> propietarioOptional = gestorUsuarios.getUsuarioById(propietarioId);

        if (propietarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // Propietario no encontrado
        }
        
        UsuariosModel propietario = propietarioOptional.get();
        List<PropiedadesModel> propiedades = gestorPropiedades.getPropiedadesByPropietario(propietario);

        if (propiedades.isEmpty()) {
            // Retorna OK, pero lista vacía, o NotFound si prefieres
            return ResponseEntity.ok(propiedades); 
        }

        return ResponseEntity.ok(propiedades);
    }

    // Listar todas las propiedades
    @GetMapping()
    public List<PropiedadesModel> listarPropiedades(){
        return gestorPropiedades.getAllPropiedades();
    }

    // Obtener propiedad por id
    @GetMapping("/{id}")
    public ResponseEntity<PropiedadesModel> obtenerPropiedad(@PathVariable Long id){
        return gestorPropiedades.getPropiedadById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
