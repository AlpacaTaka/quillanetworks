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

    @Column(nullable = false, length = 45)
    private String rol;

    @Column(nullable = false)
    private boolean estado;

    @Column(nullable = false)
    private boolean eliminado;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Credencial credencial;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private ExtensionSip extensionSip;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<SalaVideo> salasVideo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<RegistroSalas> registrosSalas;
}
