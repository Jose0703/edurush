package com.edurush.pe.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "aula")
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aula")
    private Integer idAula;

    @Column(name = "codigo", length = 20)
    private String codigo;

    @Column(name = "capacidad")
    private Integer capacidad;

    @Column(name = "estado", length = 20)
    private String estado = "disponible";
}