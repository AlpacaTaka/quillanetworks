package com.example.quillawebinterface.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "credencial")
@Getter
@Setter
public class Credencial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100, unique = true)
    private String correo;

    @Column(nullable = false, length = 255)
    private String password;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Constructores, getters y setters
    public Credencial() {}

    public Credencial(String correo, String password, Usuario usuario) {
        this.correo = correo;
        this.password = password;
        this.usuario = usuario;
    }

}
