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
@Table(name = "Propiedad_Imagen")
public class PropiedadImaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_propiedad", nullable = false) // Columna FK en esta tabla
    private PropiedadesModel propiedad;

    @Column(name = "url_imagen", nullable = false, length = 255)
    private String urlImagen;

    @Column(name="orden")
    private Integer orden;

    @Column(name="es_principal")
    private Boolean esPrincipal;

    @Column(name="fecha_subida")
    private Date fechaSubida;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PropiedadesModel getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadesModel propiedad) {
        this.propiedad = propiedad;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Boolean getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(Boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    

}
