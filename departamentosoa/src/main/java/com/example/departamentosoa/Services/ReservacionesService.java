package com.example.demo.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.PropiedadesModel;
import com.example.demo.Models.ReservacionesModel;
import com.example.demo.Models.ReservacionesModel.EstadoReserva;
import com.example.demo.Models.UsuariosModel;
import com.example.demo.Repositories.IReservacionesRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservacionesService {
    
    @Autowired
    private IReservacionesRepository reservacionesRepository;

    @Autowired
    private UsuariosService clientesService;

    @Autowired
    private PropiedadesService propiedadesService;


    public List<ReservacionesModel> getAllReservaciones() {
        return reservacionesRepository.findAll();
    }

    public Optional<ReservacionesModel> getReservacionById(Long id) {
        return reservacionesRepository.findById(id);
    }

    public ReservacionesModel saveReservacion(ReservacionesModel reservacion) {
        return reservacionesRepository.save(reservacion);
    }

    public void deleteReservacion(Long id) {
        reservacionesRepository.deleteById(id);
    }

    // Método para reservar un apartamento
    public ReservacionesModel reservarApartamento(ReservacionesModel reserva) {
        
        
        if (reserva.getEstado() == null) {
            reserva.setEstado(EstadoReserva.PENDIENTE);
        }
        
        if (reserva.getFechaReservacion() == null) {
            reserva.setFechaReservacion(LocalDateTime.now());
        }
        
       
        return reservacionesRepository.save(reserva);
    }

    //Metodo para cancelar una reserva
    public Optional<ReservacionesModel> cancelarReserva(Long reservaId) {
        
        Optional<ReservacionesModel> reservaOptional = reservacionesRepository.findById(reservaId);
        
        if (reservaOptional.isPresent()) {
            ReservacionesModel reservaToCancel = reservaOptional.get();
            
            // 1. Verificar el estado actual (lógica de negocio)
            if (reservaToCancel.getEstado() == EstadoReserva.CANCELADA || 
                reservaToCancel.getEstado() == EstadoReserva.COMPLETADA) {
                // Aquí podrías lanzar una excepción si no se permite cancelar
                // una reserva ya cancelada o completada.
            }
            
            // 2. Actualizar el estado a CANCELADA
            reservaToCancel.setEstado(EstadoReserva.CANCELADA);
            
            // 3. Guardar la entidad actualizada
            return Optional.of(reservacionesRepository.save(reservaToCancel));
        }
        
        return Optional.empty(); // Reserva no encontrada
    }

    //Metodo para obtener reservas por usuario
    public List<ReservacionesModel> getReservasByUsuarioId(Long clienteId) {
        
        // 1. Buscar la entidad del cliente (Usuario)
        Optional<UsuariosModel> clienteOptional = clientesService.getUsuarioById(clienteId);

        if (clienteOptional.isPresent()) {
            // 2. Si existe, usar el repositorio para filtrar por el objeto cliente
            UsuariosModel cliente = clienteOptional.get();
            return reservacionesRepository.findByCliente(cliente);
        }
        
        // 3. Si el cliente no existe, devuelve una lista vacía
        return Collections.emptyList();
    }

    //Metodo para obtener reservas por fecha
    public List<ReservacionesModel> getReservasByFecha(LocalDate fechaBusqueda) {
        
        return reservacionesRepository.findByFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(
                fechaBusqueda, 
                fechaBusqueda // Usamos la misma fecha para ambos parámetros
        );
    }

    //Metodo para obtener reservas por estado
    public List<ReservacionesModel> getReservasByEstado(EstadoReserva estado) {
        return reservacionesRepository.findByEstado(estado);
    }

    //Método para obtener reservaciones por usuario y estado
    public List<ReservacionesModel> getReservasByUsuarioAndEstado(Long clienteId, EstadoReserva estado) {
        
        // 1. Buscar la entidad del cliente (Usuario)
        Optional<UsuariosModel> clienteOptional = clientesService.getUsuarioById(clienteId);

        if (clienteOptional.isPresent()) {
            // 2. Si existe, usar el repositorio para filtrar por cliente y estado
            UsuariosModel cliente = clienteOptional.get();
            return reservacionesRepository.findByClienteAndEstado(cliente, estado);
        }
        
        // 3. Si el cliente no existe, devuelve una lista vacía
        return Collections.emptyList();
    }

    //Método para obtener reservaciones por propiedad y estado
    public List<ReservacionesModel> getReservasByPropiedadAndEstado(Long propiedadId, EstadoReserva estado) {
        
        // 1. Buscar la entidad de la propiedad
        Optional<PropiedadesModel> propiedadOptional = propiedadesService.getPropiedadById(propiedadId);

        if (propiedadOptional.isPresent()) {
            // 2. Si existe, usar el repositorio para filtrar por propiedad y estado
            PropiedadesModel propiedad = propiedadOptional.get();
            return reservacionesRepository.findByPropiedadAndEstado(propiedad, estado);
        }
        
        // 3. Si la propiedad no existe, devuelve una lista vacía
        return Collections.emptyList();
    }

    //Método para obtener todas las reservaciones por propiedad
    public List<ReservacionesModel> getReservasByPropiedadId(Long propiedadId) {
        Optional<PropiedadesModel> propiedadOptional = propiedadesService.getPropiedadById(propiedadId);

        if (propiedadOptional.isPresent()) {
            PropiedadesModel propiedad = propiedadOptional.get();
            return reservacionesRepository.findByPropiedad(propiedad);
        }

        return Collections.emptyList();
    }

    //Método para obtener reservaciones por usuario y fecha
    public List<ReservacionesModel> getReservasByUsuarioAndFecha(Long clienteId, LocalDate fechaBusqueda) {
        
        // 1. Buscar la entidad del cliente (Usuario)
        Optional<UsuariosModel> clienteOptional = clientesService.getUsuarioById(clienteId);

        if (clienteOptional.isPresent()) {
            UsuariosModel cliente = clienteOptional.get();
            
            // 2. Filtrar por cliente y por traslape con la fecha de búsqueda
            return reservacionesRepository.findByClienteAndFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(
                cliente, 
                fechaBusqueda, 
                fechaBusqueda
            );
        }
        
        // 3. Si el cliente no existe, devuelve una lista vacía
        return Collections.emptyList();
    }

    //Método para obtener reservaciones por propiedad y fecha
    public List<ReservacionesModel> getReservasByPropiedadAndFecha(Long propiedadId, LocalDate fechaBusqueda) {
        
        // 1. Buscar la entidad de la propiedad
        Optional<PropiedadesModel> propiedadOptional = propiedadesService.getPropiedadById(propiedadId);

        if (propiedadOptional.isPresent()) {
            PropiedadesModel propiedad = propiedadOptional.get();
            
            // 2. Filtrar por propiedad y por traslape con la fecha de búsqueda
            return reservacionesRepository.findByPropiedadAndFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(
                propiedad, 
                fechaBusqueda, 
                fechaBusqueda
            );
        }
        
        // 3. Si la propiedad no existe, devuelve una lista vacía
        return Collections.emptyList();
    }

    //Método para cancelar todas las reservas por usuario
    public List<ReservacionesModel> cancelarTodasLasReservasDeUsuario(Long clienteId) {
        
        // 1. Verificar si el usuario existe
        Optional<UsuariosModel> clienteOptional = clientesService.getUsuarioById(clienteId);

        if (clienteOptional.isEmpty()) {
            // Si el usuario no existe, se lanza una excepción o se devuelve null/lista vacía.
            throw new RuntimeException("Usuario no encontrado con ID: " + clienteId);
        }
        
        UsuariosModel cliente = clienteOptional.get();
        
        // 2. Obtener todas las reservas activas del cliente (usando el método findByCliente)
        List<ReservacionesModel> reservasDelCliente = reservacionesRepository.findByCliente(cliente);

        // 3. Iterar y actualizar el estado
        for (ReservacionesModel reserva : reservasDelCliente) {
            // Se puede añadir lógica aquí para no cancelar reservas ya completadas, etc.
            if (reserva.getEstado() != EstadoReserva.COMPLETADA) { 
                reserva.setEstado(EstadoReserva.CANCELADA);
            }
        }
        
        // 4. Guardar las reservas actualizadas masivamente
        return reservacionesRepository.saveAll(reservasDelCliente);
    }

    //Metodo para cancelar todas las reservas por propiedad
    public List<ReservacionesModel> cancelarTodasLasReservasDePropiedad(Long propiedadId) {
        
        // 1. Verificar si la propiedad existe
        Optional<PropiedadesModel> propiedadOptional = propiedadesService.getPropiedadById(propiedadId);

        if (propiedadOptional.isEmpty()) {
            // Si la propiedad no existe, se lanza una excepción
            throw new RuntimeException("Propiedad no encontrada con ID: " + propiedadId);
        }
        
        PropiedadesModel propiedad = propiedadOptional.get();
        
        // 2. Obtener todas las reservas del apartamento (usando findByPropiedad)
        List<ReservacionesModel> reservasDelApartamento = reservacionesRepository.findByPropiedad(propiedad);

        // 3. Iterar y actualizar el estado
        for (ReservacionesModel reserva : reservasDelApartamento) {
            // Solo cancelamos si no están ya completadas o canceladas
            if (reserva.getEstado() != EstadoReserva.COMPLETADA && reserva.getEstado() != EstadoReserva.CANCELADA) { 
                reserva.setEstado(EstadoReserva.CANCELADA);
            }
        }
        
        // 4. Guardar las reservas actualizadas masivamente
        return reservacionesRepository.saveAll(reservasDelApartamento);
    }

    //Metodo para cancelar todas las reservas por fecha
    @Transactional // Asegura que todas las actualizaciones se realicen como una sola transacción
    public List<ReservacionesModel> cancelarReservasPorFecha(LocalDate fechaBusqueda) {
        
        // 1. Obtener todas las reservas que coinciden o se traslapan con la fecha
        List<ReservacionesModel> reservasActivasEnFecha = 
            reservacionesRepository.findByFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(
                fechaBusqueda, 
                fechaBusqueda
            );

        // 2. Iterar y actualizar el estado
        for (ReservacionesModel reserva : reservasActivasEnFecha) {
            // Solo cancelar si no están ya canceladas o completadas
            if (reserva.getEstado() != EstadoReserva.COMPLETADA && reserva.getEstado() != EstadoReserva.CANCELADA) {
                reserva.setEstado(EstadoReserva.CANCELADA);
            }
        }
        
        // 3. Guardar todas las reservas actualizadas masivamente
        return reservacionesRepository.saveAll(reservasActivasEnFecha);
    }
}
