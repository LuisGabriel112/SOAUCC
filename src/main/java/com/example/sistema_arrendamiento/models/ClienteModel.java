
package com.example.sistema_arrendamiento.models;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "clientes")
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    /*@OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JsonIgnore
    private Set<PropiedadesModel> propiedades = new HashSet<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ReservacionesModel> reservaciones = new HashSet<>();

    @OneToMany(mappedBy = "remitente", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<MensajesModel> mensajesRemitente = new HashSet<>();

    @OneToMany(mappedBy = "destinatario", cascade = CascadeType.ALL)
    private Set<MensajesModel> mensajesDestinatario = new HashSet<>();*/

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cliente", nullable = false)
    private TipoCliente tipo = TipoCliente.INDIVIDUAL;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cliente_mensaje", nullable = false)
    private TipoClienteMensaje tipoClienteMensaje;

    @Column(name = "nombre", nullable = false, length = 35)
    private String nombres;


    @Column(name = "numero_ine", nullable = false, unique = true, length = 20)
    private String ine;

    @Column(name = "apellido_pat", nullable = false, length = 25)
    private String apellidoPat;

    @Column(name = "apellido_mat", nullable = false, length = 25)
    private String apellidoMat;

    @Column(name = "email", unique=true, length = 50)
    private String email;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "ultimo_acceso")
    private LocalDateTime ultimoAcceso;

    // Constructor sin argumentos (requerido por JPA)
    public ClienteModel() {
    }

    // Constructor con argumentos para crear nuevas instancias
    public ClienteModel(TipoCliente tipo, String nombres, String apellidoPat, String apellidoMat,
                         String email, String telefono, Date fechaNacimiento, String ine, String direccion,
                         LocalDateTime fechaRegistro, Status status, String password, LocalDateTime ultimoAcceso, TipoClienteMensaje tipoClienteMensaje) {
        this.tipo = tipo;
        this.nombres = nombres;
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.ine = ine;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.status = status;
        this.password = password;
        this.ultimoAcceso = ultimoAcceso;
        this.tipoClienteMensaje = tipoClienteMensaje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*public Set<PropiedadesModel> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(Set<PropiedadesModel> propiedades) {
        this.propiedades = propiedades;
    }

    public Set<ReservacionesModel> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(Set<ReservacionesModel> reservaciones) {
        this.reservaciones = reservaciones;
    }

    public Set<MensajesModel> getMensajesRemitente() {
        return mensajesRemitente;
    }

    public void setMensajesRemitente(Set<MensajesModel> mensajesRemitente) {
        this.mensajesRemitente = mensajesRemitente;
    }

    public Set<MensajesModel> getMensajesDestinatario() {
        return mensajesDestinatario;
    }

    public void setMensajesDestinatario(Set<MensajesModel> mensajesDestinatario) {
        this.mensajesDestinatario = mensajesDestinatario;
    }*/


    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getIne() {
        return ine;
    }

    public void setIne(String ine) {
        this.ine = ine;
    }

    public String getApellidoPat() {
        return apellidoPat;
    }

    public void setApellidoPat(String apellidoPat) {
        this.apellidoPat = apellidoPat;
    }

    public String getApellidoMat() {
        return apellidoMat;
    }

    public void setApellidoMat(String apellidoMat) {
        this.apellidoMat = apellidoMat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(LocalDateTime ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public TipoClienteMensaje getTipoClienteMensaje() {
        return tipoClienteMensaje;
    }
    public void setTipoClienteMensaje(TipoClienteMensaje tipoClienteMensaje) {
        this.tipoClienteMensaje = tipoClienteMensaje;
    }
    

    

    

 }