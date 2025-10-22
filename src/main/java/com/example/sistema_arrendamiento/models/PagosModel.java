package com.example.sistema_arrendamiento.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagos")
public class PagosModel {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_reservacion", nullable = false) // FK: id_reservacion en la tabla reseñas
    private ReservacionesModel reservacion;

    @Column(name="monto", nullable = false)
    private Double monto;

    @Enumerated(jakarta.persistence.EnumType.STRING)
    @Column(name="metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @Enumerated(jakarta.persistence.EnumType.STRING)
    @Column(name="estado", nullable = false)
    private EstadoPago estado;

    @Column(name="fecha_pago", nullable = false)
    private Date fechaPago;

    @Column(name="referencia_pago", nullable = false, unique = true)
    private String referenciaPago;

    @Column(name="datos_pago")
    private String datosPago;

    @Column(name="fecha_creacion", nullable = false)
    private Date fechaCreacion;

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

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public EstadoPago getEstado() {
        return estado;
    }

    public void setEstado(EstadoPago estado) {
        this.estado = estado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getReferenciaPago() {
        return referenciaPago;
    }

    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    public String getDatosPago() {
        return datosPago;
    }

    public void setDatosPago(String datosPago) {
        this.datosPago = datosPago;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


    
}
