package com.example.demo.Models;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "disponibilidad")
public class DisponibilidadModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disponibilidad")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_propiedad", referencedColumnName = "id_propiedad", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PropiedadesModel propiedad;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha; 

    @Column(name = "disponible", nullable = false)
    private Boolean disponible; 

    @Column(name = "precio_especial", precision = 10, scale = 2)
    private BigDecimal precioEspecial;

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PropiedadesModel getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadesModel propiedad) {
        this.propiedad = propiedad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public BigDecimal getPrecioEspecial() {
        return precioEspecial;
    }

    public void setPrecioEspecial(BigDecimal precioEspecial) {
        this.precioEspecial = precioEspecial;
    }
    
    //Constructors
    public DisponibilidadModel() {
    }


    public DisponibilidadModel(Long id, PropiedadesModel propiedad, LocalDate fecha, Boolean disponible,
            BigDecimal precioEspecial) {
        this.propiedad = propiedad;        
        this.fecha = fecha;
        this.disponible = disponible;
        this.precioEspecial = precioEspecial;
    }

}
