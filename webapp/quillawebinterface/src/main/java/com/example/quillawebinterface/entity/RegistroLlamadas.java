package com.example.quillawebinterface.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "registro_llamadas")
@Getter
@Setter
public class RegistroLlamadas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "origen_ext_id", nullable = false)
    private ExtensionSip origenExt;

    @ManyToOne
    @JoinColumn(name = "destino_ext_id", nullable = false)
    private ExtensionSip destinoExt;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fechaFin;

    @Column(name = "duracion_segundos", nullable = false)
    private Integer duracionSegundos;

    @Column(nullable = false, length = 45)
    private String estado;
}
