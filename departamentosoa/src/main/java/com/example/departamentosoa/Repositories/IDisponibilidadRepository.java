package com.example.demo.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.DisponibilidadModel;
import com.example.demo.Models.PropiedadesModel;

@Repository
public interface IDisponibilidadRepository extends JpaRepository<DisponibilidadModel, Long> {

    @Query("SELECT d FROM DisponibilidadModel d WHERE " +
           "d.propiedad = :propiedad AND " +
           "d.fecha BETWEEN :fechaEntrada AND :fechaSalida AND " +
           "d.disponible = TRUE")
    List<DisponibilidadModel> findAvailableDatesForProperty(
            @Param("propiedad") PropiedadesModel propiedad,
            @Param("fechaEntrada") LocalDate fechaEntrada,
            @Param("fechaSalida") LocalDate fechaSalida
    );
    
}
