package com.edurush.pe.service;

import com.edurush.pe.dto.MatriculaPostulanteDTO;

public interface PublicMatriculaService {
    // Este método hará toda la lógica pesada: Crear Usuario, Estudiante, Pago y Matrícula
    MatriculaPostulanteDTO registrarMatriculaCompleta(MatriculaPostulanteDTO dto);
    
    // Validación de DNI para el paso 2 (RF09)
    boolean existeDni(String dni);
}