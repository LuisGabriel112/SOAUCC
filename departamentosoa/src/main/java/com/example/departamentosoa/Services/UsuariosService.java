package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.UsuariosModel;
import com.example.demo.Repositories.IUsuariosRepository;

@Service
public class UsuariosService {
    @Autowired
    private IUsuariosRepository usuariosRepository;

    public List<UsuariosModel> getallUsuarios(){
        return usuariosRepository.findAll();
    }

    public Optional<UsuariosModel> getUsuarioById(Long id){
        return usuariosRepository.findById(id);
    }

    public UsuariosModel saveUsuario(UsuariosModel usuario){
        return usuariosRepository.save(usuario);
    }

    public void deleteUsuario(Long id){
        usuariosRepository.deleteById(id);
    }

    // Aliases en español para controladores refactorizados
    public Optional<UsuariosModel> obtenerUsuarioPorId(Long id){
        return getUsuarioById(id);
    }

    public UsuariosModel guardarUsuario(UsuariosModel usuario){
        return saveUsuario(usuario);
    }

    public void eliminarUsuario(Long id){
        deleteUsuario(id);
    }
}
