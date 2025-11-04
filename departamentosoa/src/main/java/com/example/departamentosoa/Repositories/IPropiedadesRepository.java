package com.example.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.PropiedadesModel;
import com.example.demo.Models.PropiedadesModel.TipoPropiedad;
import com.example.demo.Models.UsuariosModel;

@Repository
public interface IPropiedadesRepository extends JpaRepository<PropiedadesModel, Long>{

    List<PropiedadesModel> findByTipo(TipoPropiedad tipo);

    List<PropiedadesModel> findByPropietario(UsuariosModel propietario);
}
