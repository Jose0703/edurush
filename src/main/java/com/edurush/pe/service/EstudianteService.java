package com.edurush.pe.service;

import com.edurush.pe.model.Estudiante;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

public interface EstudianteService {

    ResponseEntity<Map<String, Object>> listarEstudiante();
    ResponseEntity<Map<String, Object>> agregarEstudiante(Estudiante estudiante);

    ResponseEntity<?> obtenerEstudiantePorId(@PathVariable Integer id);
    ResponseEntity<Map<String, Object>> editarEstudiante(Estudiante estudiente, Long id);
    ResponseEntity<Map<String, Object>> eliminarEstudiante(Long id);
}
