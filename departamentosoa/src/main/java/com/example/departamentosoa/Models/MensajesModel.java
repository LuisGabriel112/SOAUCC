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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "mensajes")
public class MensajesModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "id_remitente", // Columna FK en la tabla 'mensajes'
        referencedColumnName = "id_cliente", // PK en la tabla 'usuarios'
        nullable = false
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "propiedades", "reservasEnviadas", "reservasRecibidas"})
    private UsuariosModel remitente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "id_destinatario", // Columna FK en la tabla 'mensajes'
        referencedColumnName = "id_cliente", // PK en la tabla 'usuarios'
        nullable = false
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "propiedades", "reservasEnviadas", "reservasRecibidas"})
    private UsuariosModel destinatario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "id_reservacion", // Columna FK en la tabla 'mensajes'
        referencedColumnName = "id_reservacion", // PK en la tabla 'reservaciones'
        nullable = false
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ReservacionesModel reservacion;

    @Column(name = "asunto", length = 255)
    private String asunto;

    @Lob 
    @Column(name = "contenido")
    private String contenido;

    @Column(name = "leido", nullable = false)
    private Boolean leido = false; // Valor por defecto

    @Column(name = "fecha_envio", nullable = false, updatable = false)
    private LocalDateTime fechaEnvio;

    @PrePersist
    protected void onCreate() {
        fechaEnvio = LocalDateTime.now();
    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuariosModel getRemitente() {
        return remitente;
    }

    public void setRemitente(UsuariosModel remitente) {
        this.remitente = remitente;
    }

    public UsuariosModel getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(UsuariosModel destinatario) {
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

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    //Constructor
    public MensajesModel() {
    }

    //Constructor with parameters
    public MensajesModel(Long id, UsuariosModel remitente, UsuariosModel destinatario, ReservacionesModel reservacion,
            String asunto, String contenido, Boolean leido, LocalDateTime fechaEnvio) {
        this.asunto = asunto;
        this.contenido = contenido;
        this.leido = leido;
    }   

}
