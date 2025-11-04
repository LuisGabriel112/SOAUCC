package com.example.demo.Controllers;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.UsuariosModel;
import com.example.demo.Services.UsuariosService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    @Autowired
    private UsuariosService gestorUsuarios;

    //Get all usuarios
    @GetMapping()
    public List<UsuariosModel> getallUsuarios(){
        return gestorUsuarios.getallUsuarios();
    }

    //Get usuario by id
    @GetMapping("/{id}")
    public ResponseEntity<UsuariosModel> getUsuarioById(@PathVariable Long id){
        Optional<UsuariosModel> usuario = gestorUsuarios.getUsuarioById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Create usuario
    @PostMapping
    public UsuariosModel createUsuario(@RequestBody UsuariosModel usuario){
        return gestorUsuarios.saveUsuario(usuario);
    }

    //Update usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsuariosModel> updateUsuario(@RequestBody UsuariosModel cuerpoUsuario, @PathVariable Long id){
        return gestorUsuarios.getUsuarioById(id)
            .map(actual -> {
                actual.setNombres(cuerpoUsuario.getNombres());
                actual.setApellidoPat(cuerpoUsuario.getApellidoPat());
                actual.setApellidoMat(cuerpoUsuario.getApellidoMat());
                actual.setEmail(cuerpoUsuario.getEmail());
                actual.setTelefono(cuerpoUsuario.getTelefono());
                actual.setFechaNacimiento(cuerpoUsuario.getFechaNacimiento());
                actual.setIne(cuerpoUsuario.getIne());
                actual.setDireccion(cuerpoUsuario.getDireccion());
                actual.setTipoCliente(cuerpoUsuario.getTipoCliente());
                actual.setEstatus(cuerpoUsuario.getEstatus());
                actual.setPassword(cuerpoUsuario.getPassword());
                UsuariosModel persistido = gestorUsuarios.saveUsuario(actual);
                return ResponseEntity.ok(persistido);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Delete usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id){
        return gestorUsuarios.getUsuarioById(id)
            .map(u -> {
                gestorUsuarios.deleteUsuario(id);
                return ResponseEntity.noContent().build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    
}
