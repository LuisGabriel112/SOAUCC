package com.example.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.PropiedadesModel;
import com.example.demo.Models.ResenasModel;
import com.example.demo.Models.UsuariosModel;

@Repository
public interface IResenasRepository extends JpaRepository<ResenasModel, Long> {
    List<ResenasModel> findByAutor(UsuariosModel autor);
    List<ResenasModel> findByReservacionCliente(UsuariosModel cliente);
    List<ResenasModel> findByReservacionPropiedad(PropiedadesModel propiedad);
}
