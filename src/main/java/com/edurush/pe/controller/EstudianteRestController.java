package com.edurush.pe.controller;

import com.edurush.pe.model.Estudiante;
import com.edurush.pe.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class EstudianteRestController {
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarEstudiantes() {
        return estudianteService.listarEstudiante();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Map<String, Object>> buscarPorNombreApellido(@RequestParam String texto) {
        return estudianteService.buscarPorNombreApellido(texto);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregarEstudiante( @RequestBody Estudiante estudiante){
        return estudianteService.agregarEstudiante(estudiante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editarEstudiante(@RequestBody Estudiante estudiante, @PathVariable Integer id){
        return estudianteService.editarEstudiante(estudiante, id);
    }

    @PutMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Object>> eliminarEstudiante(@PathVariable Integer id){
        return estudianteService.eliminarEstudiante(id);
    }
}
