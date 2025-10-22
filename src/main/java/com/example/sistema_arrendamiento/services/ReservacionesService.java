package com.example.sistema_arrendamiento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sistema_arrendamiento.models.ReservacionesModel;
import com.example.sistema_arrendamiento.repositories.IReservacionesRepository;

@Service
public class ReservacionesService {
    
    @Autowired
    private IReservacionesRepository reservacionesRepository;

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

}
