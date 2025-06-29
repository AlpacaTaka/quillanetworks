package com.example.quillawebinterface.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "extension_sip")
@Getter
@Setter
public class ExtensionSip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero_ext", nullable = false, length = 10, unique = true)
    private String numeroExt;

    @Column(nullable = false, length = 100)
    private String clave;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "origenExt")
    private List<RegistroLlamadas> llamadasSalientes;

    @OneToMany(mappedBy = "destinoExt")
    private List<RegistroLlamadas> llamadasEntrantes;
}
