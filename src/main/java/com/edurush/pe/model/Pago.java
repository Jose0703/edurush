package com.edurush.pe.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "pago")
@Data
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;

    @OneToOne
    @JoinColumn(name = "id_orden_pago", nullable = false)
    private OrdenPago ordenPago;

    @Column(name = "monto_pagado", nullable = false)
    private Double montoPagado;

    @Column(name = "fecha_pago")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fechaPago = LocalDateTime.now();

    private String estado = "confirmado";
}