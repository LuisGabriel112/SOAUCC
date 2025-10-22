package com.example.sistema_arrendamiento.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table (name = "DISPONIBILIDADES")
public class DisponibilidadModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn (name = "ID_PROPIEDAD")
    private PropiedadesModel Propiedad;

    @Column (name = "FECHA")
    private LocalDateTime fecha;

    @Column (name = "DISPONIBLE")
    private boolean disponible;

    @Column (name = "PRECIO_ESPECIAL")
    private double precioEspecial;

    // Constructor sin argumentos (requerido por JPA)
    public DisponibilidadModel() {
    }

    // Constructor con argumentos para crear nuevas instancias  
    public DisponibilidadModel(PropiedadesModel Propiedad, LocalDateTime fecha, 
                            boolean disponible, double precioEspecial) {
                                this.Propiedad = Propiedad;
                                this.fecha = fecha;
                                this.disponible = disponible;
                                this.precioEspecial = precioEspecial;
                            }

    //GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PropiedadesModel getPropiedad() {
        return Propiedad;
    }

    public void setPropiedad(PropiedadesModel Propiedad) {
        this.Propiedad = Propiedad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public double getPrecioEspecial() {
        return precioEspecial;
    }

    public void setPrecioEspecial(double precioEspecial) {
        this.precioEspecial = precioEspecial;
    }
}