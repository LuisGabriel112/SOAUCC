package com.example.sistema_arrendamiento.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name="Propiedades")
public class PropiedadesModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_propiedad")
    private Long id;

    /*@OneToMany(mappedBy = "propiedad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PropiedadImaModel> imagenes = new HashSet<>();*/

    @ManyToOne
    @JoinColumn(name = "id_propietario", referencedColumnName = "id_cliente", nullable = true)
    @JsonIgnore
    private ClienteModel propietario ;

    /*@OneToMany(mappedBy = "propiedad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReservacionesModel> reservaciones = new HashSet<>();

    @OneToMany(mappedBy = "propiedad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DisponibilidadModel> disponibilidades = new HashSet<>();*/

    @Enumerated(EnumType.STRING)
    @Column(name="Tipo")
    private TipoPropiedad tipo;

    @Column(name="Título")
    private String titulo;

    @Column(name="Descripción", length = 500)
    private String descripcion;

    @Column(name="Dirección", length = 100)
    private String direccion;

    @Column(name="Ciudad")
    private String ciudad;

    @Column(name="Código_Postal", length = 10)
    private String codigoPostal;

    @Column(name="País")
    private String pais;

    @Column(name="latitud")
    private Double latitud;

    @Column(name="longitud")
    private Double longitud;

    @Column(name="precio_noche")
    private Double precioNoche;

    @Column(name="capacidad_personas")
    private Integer capacidadPersonas;

    @Column(name="numero_habitaciones")
    private Integer numeroHabitaciones;

    @Column(name="numero_banos")
    private Integer numeroBanos;

    @Column(name="metros_cuadrados")
    private Integer metrosCuadrados;

    @Column(name="comodidades", length = 300)
    private String comodidades;

    @Column(name="normas_casa", length = 300)
    private String normasCasa;

    @Enumerated(EnumType.STRING)
    @Column(name="estado")
    private EstadoPropiedad estado;

    @Column(name="fecha_creacion")
    private Date fechaCreacion;

    @Column(name="fecha_actualizacion")
    private Date fechaActualizacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*public Set<PropiedadImaModel> getImagenes() {
        return imagenes;
    }

    public void setImagenes(Set<PropiedadImaModel> imagenes) {
        this.imagenes = imagenes;
    }*/

    public ClienteModel getPropietario() {
        return propietario;
    }

    public void setPropietario(ClienteModel propietario) {
        this.propietario = propietario;
    }

    /*public Set<ReservacionesModel> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(Set<ReservacionesModel> reservaciones) {
        this.reservaciones = reservaciones;
    }

    public Set<DisponibilidadModel> getDisponibilidades() {
        return disponibilidades;
    }

    public void setDisponibilidades(Set<DisponibilidadModel> disponibilidades) {
        this.disponibilidades = disponibilidades;
    }*/

    public TipoPropiedad getTipo() {
        return tipo;
    }

    public void setTipo(TipoPropiedad tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(Double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public Integer getCapacidadPersonas() {
        return capacidadPersonas;
    }

    public void setCapacidadPersonas(Integer capacidadPersonas) {
        this.capacidadPersonas = capacidadPersonas;
    }

    public Integer getNumeroHabitaciones() {
        return numeroHabitaciones;
    }

    public void setNumeroHabitaciones(Integer numeroHabitaciones) {
        this.numeroHabitaciones = numeroHabitaciones;
    }

    public Integer getNumeroBanos() {
        return numeroBanos;
    }

    public void setNumeroBanos(Integer numeroBanos) {
        this.numeroBanos = numeroBanos;
    }

    public Integer getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(Integer metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public String getComodidades() {
        return comodidades;
    }

    public void setComodidades(String comodidades) {
        this.comodidades = comodidades;
    }

    public String getNormasCasa() {
        return normasCasa;
    }

    public void setNormasCasa(String normasCasa) {
        this.normasCasa = normasCasa;
    }

    public EstadoPropiedad getEstado() {
        return estado;
    }

    public void setEstado(EstadoPropiedad estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    





}
