package com.edurush.pe.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ciclo")
public class Ciclo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciclo")
    private Integer idCiclo;

    @ManyToOne
    @JoinColumn(name = "id_plan_estudio", nullable = false)
    private PlanEstudio planEstudio;

    @Column(name = "numero_ciclo", nullable = false)
    private Integer numeroCiclo;

    @Column(length = 20)
    private String estado = "activo";
}