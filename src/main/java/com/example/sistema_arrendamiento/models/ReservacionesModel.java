package com.example.sistema_arrendamiento.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;




@Entity
@Table(name = "reservaciones")
public class ReservacionesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservacion")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pago", nullable = true) // FK: id_pago
    private PagosModel pago;

    @ManyToOne
    @JoinColumn(name = "id_resena", nullable = true) // FK: id_resena en la tabla reseñas
    private ReseñasModel resena;

    @ManyToOne
    @JoinColumn(name = "id_propiedad", nullable = false) // FK: id_propiedad en la tabla reservaciones
    private PropiedadesModel propiedad;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false) // FK: id_cliente
    private ClienteModel cliente;

    @Column(name="fecha_entrada")
    private Date fechaEntrada;

    @Column(name="fecha_salida")
    private Date fechaSalida;

    @Column(name="numero_huespedes")
    private Integer numeroHuespedes;

    @Column(name="precio_total")
    private Double precioTotal;

    @Enumerated(EnumType.STRING)
    @Column(name="estado", nullable = false)
    private EstadoReservacion estado = EstadoReservacion.PENDIENTE;

    @Column(name="fecha_reservacion")
    private Date fechaReservacion;

    @Column(name="notas", length = 500)
    private String notas;

    @Column(name="codigo_reserva")
    private String codigoReserva;

    @Column(name="fecha_checkin")
    private Date fechaCheckin;

    @Column(name="fecha_checkout")
    private Date fechaCheckout;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PagosModel getPago() {
        return pago;
    }

    public void setPago(PagosModel pago) {
        this.pago = pago;
    }

    public ReseñasModel getResena() {
        return resena;
    }

    public void setResena(ReseñasModel resena) {
        this.resena = resena;
    }

    public PropiedadesModel getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadesModel propiedad) {
        this.propiedad = propiedad;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Integer getNumeroHuespedes() {
        return numeroHuespedes;
    }

    public void setNumeroHuespedes(Integer numeroHuespedes) {
        this.numeroHuespedes = numeroHuespedes;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public EstadoReservacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoReservacion estado) {
        this.estado = estado;
    }

    public Date getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(Date fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public Date getFechaCheckin() {
        return fechaCheckin;
    }

    public void setFechaCheckin(Date fechaCheckin) {
        this.fechaCheckin = fechaCheckin;
    }

    public Date getFechaCheckout() {
        return fechaCheckout;
    }

    public void setFechaCheckout(Date fechaCheckout) {
        this.fechaCheckout = fechaCheckout;
    }

    

    
}
