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
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarEstudiantes() {
        return estudianteService.listarEstudiante();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregarEstudiante( @RequestBody Estudiante estudiante){
        return estudianteService.agregarEstudiante(estudiante);
    }

}
