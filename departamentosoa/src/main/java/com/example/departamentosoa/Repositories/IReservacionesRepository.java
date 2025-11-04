package com.example.demo.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.PropiedadesModel;
import com.example.demo.Models.ReservacionesModel;
import com.example.demo.Models.ReservacionesModel.EstadoReserva;
import com.example.demo.Models.UsuariosModel;

@Repository
public interface IReservacionesRepository extends JpaRepository<ReservacionesModel, Long> {
    
    //Metodo para encontrar reservacion por user
    List<ReservacionesModel> findByCliente(UsuariosModel cliente);

    //Metodo para encontrar reservacion por propiedad
    List<ReservacionesModel> findByPropiedad(PropiedadesModel propiedad);

    /* 
    //Metodo para encontrar reservacion por fecha
    List<ReservacionesModel> findByFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(
            LocalDate fecha, LocalDate fechaBusqueda
    );
    */



    //Metodo para encontrar reservacion por estado
    List<ReservacionesModel> findByEstado(EstadoReserva estado);

    //Crear un método para obtener las reservaciones por usuario y estado
    List<ReservacionesModel> findByClienteAndEstado(UsuariosModel cliente, EstadoReserva estado);

    //Crear un método para obtener las reservaciones por propiedad y estado
    List<ReservacionesModel> findByPropiedadAndEstado(PropiedadesModel propiedad, EstadoReserva estado);

    //Crear un método para obtener las reservaciones por usuario y fecha
    List<ReservacionesModel> findByClienteAndFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(
            UsuariosModel cliente, 
            LocalDate fechaBusquedaInicio, 
            LocalDate fechaBusquedaFin
    );
    
    //Metodo para obtener las reservaciones por propiedad y fecha
    List<ReservacionesModel> findByPropiedadAndFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(
            PropiedadesModel propiedad, 
            LocalDate fechaBusquedaInicio, 
            LocalDate fechaBusquedaFin
    );

    //Metodo para cancelar todas las reservaciones por fecha
    List<ReservacionesModel> findByFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(
            LocalDate fechaInicio, 
            LocalDate fechaFin
        );
            

    

}
