package com.example.quillawebinterface.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "sala_video")
@Getter
@Setter
public class SalaVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_sala", nullable = false, length = 250)
    private String nombreSala;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL)
    private List<RegistroSalas> registroSalas;
}
