package com.edurush.pe.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "estado")
    private String estado = "activo";
}
