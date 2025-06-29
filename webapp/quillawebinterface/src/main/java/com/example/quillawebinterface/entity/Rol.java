package com.example.quillawebinterface.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String nombre;

    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;

    // Constructores, getters y setters
    public Rol() {}

    public Rol(String nombre) {
        this.nombre = nombre;
    }

}