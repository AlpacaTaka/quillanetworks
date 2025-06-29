package com.example.quillawebinterface.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private Boolean estado;

    @Column(nullable = false)
    private Boolean eliminado;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Credencial credencial;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private ExtensionSip extensionSip;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<SalaVideo> salasVideo;

    // Constructores, getters y setters
    public Usuario() {}

    public Usuario(String nombre, Rol rol) {
        this.nombre = nombre;
        this.rol = rol;
        this.estado = true;
        this.eliminado = false;
        this.fechaCreacion = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Credencial getCredencial() {
        return credencial;
    }

    public void setCredencial(Credencial credencial) {
        this.credencial = credencial;
    }

    public ExtensionSip getExtensionSip() {
        return extensionSip;
    }

    public void setExtensionSip(ExtensionSip extensionSip) {
        this.extensionSip = extensionSip;
    }

    public List<SalaVideo> getSalasVideo() {
        return salasVideo;
    }

    public void setSalasVideo(List<SalaVideo> salasVideo) {
        this.salasVideo = salasVideo;
    }
}
