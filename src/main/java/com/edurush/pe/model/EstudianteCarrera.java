package com.edurush.pe.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "estudiante_carrera")
@Data
public class EstudianteCarrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstudianteCarrera;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_carrera", nullable = false)
    private Carrera carrera;

    @Column(name = "fecha_ingreso")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fechaIngreso = LocalDateTime.now();

    private String estado = "pre_matriculado"; 
    // Estados: 'pre_matriculado','activo','suspendido','finalizado','abandonado','transferido'
}