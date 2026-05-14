package com.edurush.pe.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class MatriculaPostulanteDTO {
    // Paso 1: Carrera y Modalidad
    private Integer idCarrera;
    private String turno;
    private String modalidad;

    // Paso 2: Datos del Estudiante
    private String nombres;
    private String apellidos;
    private String dni;
    private String celular;
    private String emailPersonal;
    private Date fechaNacimiento;
    // Simulación de documentos (nombres de archivo)
    private String docDniRuta = "dni_simulado.pdf";
    private String docFotoRuta = "foto_simulada.jpg";

    // Paso 3: Pago
    private String metodoPago; // "Tarjeta", "Transferencia", "Presencial"
    private Double montoTotal = 350.00; // Monto base de matrícula

    // Paso 4: Generados por el sistema
    private String correoInstitucional;
    private String passwordTemporal;
}