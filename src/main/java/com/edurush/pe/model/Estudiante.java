package com.edurush.pe.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

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
    private Timestamp fechaRegistro;
    private String estado;
}