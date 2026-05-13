package com.edurush.pe.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "seccion")
@Data
public class Seccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSeccion;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_periodo", nullable = false)
    private Periodo periodo;

    @Column(nullable = false, length = 10)
    private String codigo;

    @Column(name = "cupo_total", nullable = false)
    private Integer cupoTotal;
    
    @Column(length = 20)
    private String estado = "activo";
    // activo, inactivo, lleno
}