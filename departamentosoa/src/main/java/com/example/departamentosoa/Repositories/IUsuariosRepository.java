package com.example.demo.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.UsuariosModel;

@Repository
public interface IUsuariosRepository extends JpaRepository<UsuariosModel, Long>{
    Optional<UsuariosModel> findByEmail(String email);
}
