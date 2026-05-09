package com.edurush.pe.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "orden_pago")
@Data
public class OrdenPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrdenPago;

    @ManyToOne
    @JoinColumn(name = "id_matricula", nullable = false)
    private Matricula matricula;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;

    private String descripcion;

    @Column(name = "monto_total", nullable = false)
    private Double montoTotal;

    @Column(name = "fecha_emision")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fechaEmision = LocalDateTime.now();

    @Column(name = "fecha_vencimiento")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fechaVencimiento;

    private String estado = "pendiente";
}