package com.edurush.pe.service;

import com.edurush.pe.model.Estudiante;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface EstudianteService {

    ResponseEntity<Map<String, Object>> listarEstudiante();
    ResponseEntity<Map<String, Object>> buscarPorNombreApellido(String texto);
    ResponseEntity<Map<String, Object>> agregarEstudiante(Estudiante estudiante);
    ResponseEntity<Map<String, Object>> editarEstudiante(Estudiante estudiante, Integer id);
    ResponseEntity<Map<String, Object>> eliminarEstudiante(Integer id);

    List<Estudiante> listarTodosActivos();
    Estudiante buscarPorId(Integer id);
    void guardarWeb(Estudiante estudiante);
    void eliminarLogicoWeb(Integer id);
}
