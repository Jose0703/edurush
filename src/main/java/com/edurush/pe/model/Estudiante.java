package com.edurush.pe.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "estudiante")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Integer idEstudiante;
    @Column(name = "id_usuario")
    private int idUsuario;
    private String nombres;
    private String apellidos;
    private String dni;
    private String celular;
    private String email;
    private Date fechaNacimiento;
    @Column(insertable = false, updatable = false)
    private LocalDateTime fechaRegistro;
    private String estado;
}