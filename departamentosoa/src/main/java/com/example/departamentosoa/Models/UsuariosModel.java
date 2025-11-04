package com.example.demo.Models;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class UsuariosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cliente", nullable = false,length = 20)
    private TipoCliente tipoCliente;

    @Column(name = "nombres", nullable = false, length = 35)
    private String nombres;

    @Column(name = "apellido_pat", nullable = false, length = 35)
    private String apellidoPat;

    @Column(name = "apellido_mat", nullable = false, length = 35)
    private String apellidoMat;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "numero_ine", unique = true, length = 20)
    private String ine;

    @Column(name = "direccion", length = 100)
    private String direccion;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @Enumerated(EnumType.STRING)
    @Column(name = "estatus", nullable = false)
    private Status estatus;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "ultimo_acceso", nullable = false)
    private LocalDateTime ultimoAcceso;

    private enum TipoCliente {
        PROPIETARIO,
        INQUILINO,
        HUESPED
    }  
    
    private enum Status{
        ACTIVO,
        INACTIVO,
        SUSPENDIDO
    }

    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public String getIne() {
        return ine;
    }

    public void setIne(String ine) {
        this.ine = ine;
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

    public Status getEstatus() {
        return estatus;
    }

    public void setEstatus(Status estatus) {
        this.estatus = estatus;
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

    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
        ultimoAcceso = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "remitente", cascade = CascadeType.ALL)
    private List<MensajesModel> mensajesEnviados; // El 'mappedBy' apunta al campo 'remitente' en Mensaje

    @OneToMany(mappedBy = "destinatario", cascade = CascadeType.ALL)
    private List<MensajesModel> mensajesRecibidos; // El 'mappedBy' apunta al campo 'destinatario' en Mensaje

    //Constructors

    public UsuariosModel() {
    }

    // Constructor with all fields except id, fechaRegistro, and ultimoAcceso
    public UsuariosModel(TipoCliente tipoCliente, String nombres, String apellidoPat, String apellidoMat,
            String email, String telefono, Date fechaNacimiento, String ine, String direccion, Status estatus,
            String password) {
        this.tipoCliente = tipoCliente;
        this.nombres = nombres;
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.ine = ine;
        this.direccion = direccion;
        this.estatus = estatus;
        this.password = password;
    }
}
