package com.example.sistema_arrendamiento.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mensajes")
public class MensajesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_remitente", referencedColumnName = "id_cliente", nullable = false)
    private ClienteModel remitente;

    @ManyToOne
    @JoinColumn(name = "id_destinatario", referencedColumnName = "id_cliente", nullable = false)
    private ClienteModel destinatario;

    @ManyToOne
    @JoinColumn(name="id_reservacion", referencedColumnName = "id_reservacion", nullable = false)
    private ReservacionesModel reservacion;

    @Column(name = "asunto", nullable = false, length = 1000)
    private String asunto;

    @Column(name = "contenido", nullable = false, length = 5000)
    private String contenido;

    @Column(name = "leido", nullable = false)
    private Boolean leido;

    @Column(name = "fecha_envio", nullable = false)
    private Date fechaEnvio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteModel getRemitente() {
        return remitente;
    }

    public void setRemitente(ClienteModel remitente) {
        this.remitente = remitente;
    }

    public ClienteModel getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(ClienteModel destinatario) {
        this.destinatario = destinatario;
    }

    public ReservacionesModel getReservacion() {
        return reservacion;
    }

    public void setReservacion(ReservacionesModel reservacion) {
        this.reservacion = reservacion;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }



}
