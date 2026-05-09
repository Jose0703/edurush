package com.edurush.pe.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "documento_estudiante")
@Data
public class DocumentoEstudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDocumento;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;

    private String tipo;
    
    @Column(name = "ruta_archivo")
    private String rutaArchivo;

    private String estado = "pendiente";
}