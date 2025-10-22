package com.example.sistema_arrendamiento.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reseñas")
public class ReseñasModel {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_reservacion", nullable = false) // FK: id_reservacion en la tabla reseñas
    private ReservacionesModel reservacion;

    @Column(name="calificacion_limpieza")
    private Integer calificacionLimpieza;

    @Column(name="calificacion_ubicacion")
    private Integer calificacionUbicacion;

    @Column(name="calificacion_comunicacion")
    private Integer calificacionComunicacion;

    @Column(name="calificacion_general")
    private Integer calificacionGeneral;

    @Column(name="comentario", length = 1000)
    private String comentario;

    @Column(name="fecha_reseña")
    private Date fechaReseña;

    @Column(name="respuesta_propietario", length = 1000)
    private String respuestaPropietario;

    @Column(name="fecha_respuesta")
    private Date fechaRespuesta;

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

    public Date getFechaReseña() {
        return fechaReseña;
    }

    public void setFechaReseña(Date fechaReseña) {
        this.fechaReseña = fechaReseña;
    }

    public String getRespuestaPropietario() {
        return respuestaPropietario;
    }

    public void setRespuestaPropietario(String respuestaPropietario) {
        this.respuestaPropietario = respuestaPropietario;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }




    
}
