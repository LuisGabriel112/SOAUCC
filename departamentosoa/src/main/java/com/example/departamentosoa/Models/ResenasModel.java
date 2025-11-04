package com.example.demo.Models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "reseñas")
public class ResenasModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reseña")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "id_reservacion", referencedColumnName = "id_reservacion", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ReservacionesModel reservacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor", referencedColumnName = "id_cliente", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UsuariosModel autor;

    @Column(name = "calificacion_limpieza")
    private Integer calificacionLimpieza;

    @Column(name = "calificacion_ubicacion")
    private Integer calificacionUbicacion;

    @Column(name = "calificacion_comunicacion")
    private Integer calificacionComunicacion;

    @Column(name = "calificacion_general", nullable = false)
    private Integer calificacionGeneral;

    @Lob 
    @Column(name = "comentario")
    private String comentario;

    @Column(name = "fecha_resena", nullable = false, updatable = false)
    private LocalDateTime fechaResena;

    @Lob 
    @Column(name = "respuesta_propietario")
    private String respuestaPropietario;

    @Column(name = "fecha_respuesta")
    private LocalDateTime fechaRespuesta;

    @PrePersist
    protected void onCreate() {
        this.fechaResena = LocalDateTime.now();
        this.fechaRespuesta = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReservacionesModel getReservacion() {
        return reservacion;
    }

    public void setReservacion(ReservacionesModel reservacion) {
        this.reservacion = reservacion;
    }

    public UsuariosModel getAutor() {
        return autor;
    }

    public void setAutor(UsuariosModel autor) {
        this.autor = autor;
    }

    public Integer getCalificacionLimpieza() {
        return calificacionLimpieza;
    }

    public void setCalificacionLimpieza(Integer calificacionLimpieza) {
        this.calificacionLimpieza = calificacionLimpieza;
    }

    public Integer getCalificacionUbicacion() {
        return calificacionUbicacion;
    }

    public void setCalificacionUbicacion(Integer calificacionUbicacion) {
        this.calificacionUbicacion = calificacionUbicacion;
    }

    public Integer getCalificacionComunicacion() {
        return calificacionComunicacion;
    }

    public void setCalificacionComunicacion(Integer calificacionComunicacion) {
        this.calificacionComunicacion = calificacionComunicacion;
    }

    public Integer getCalificacionGeneral() {
        return calificacionGeneral;
    }

    public void setCalificacionGeneral(Integer calificacionGeneral) {
        this.calificacionGeneral = calificacionGeneral;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFechaResena() {
        return fechaResena;
    }

    public void setFechaResena(LocalDateTime fechaResena) {
        this.fechaResena = fechaResena;
    }

    public String getRespuestaPropietario() {
        return respuestaPropietario;
    }

    public void setRespuestaPropietario(String respuestaPropietario) {
        this.respuestaPropietario = respuestaPropietario;
    }

    public LocalDateTime getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(LocalDateTime fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    //Constructor vacío
    public ResenasModel() {
    }

    //Constructor con parámetros
    public ResenasModel(ReservacionesModel reservacion, Integer calificacionLimpieza, Integer calificacionUbicacion,
            Integer calificacionComunicacion, Integer calificacionGeneral, String comentario,
            String respuestaPropietario, UsuariosModel autor) {
        this.calificacionLimpieza = calificacionLimpieza;
        this.calificacionUbicacion = calificacionUbicacion;
        this.calificacionComunicacion = calificacionComunicacion;
        this.calificacionGeneral = calificacionGeneral;
        this.comentario = comentario;
        this.respuestaPropietario = respuestaPropietario;
        this.reservacion = reservacion;
        this.autor = autor;
    }

}
