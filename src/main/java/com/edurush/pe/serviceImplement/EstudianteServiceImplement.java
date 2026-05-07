package com.edurush.pe.serviceImplement;

import com.edurush.pe.model.Estudiante;
import com.edurush.pe.repository.EstudianteRepository;
import com.edurush.pe.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EstudianteServiceImplement implements EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Override
    public ResponseEntity<Map<String, Object>> listarEstudiante() {
        Map<String, Object> response = new LinkedHashMap<>();
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        if(estudiantes.isEmpty()){
            response.put("mensaje", "No se encontraron estudiantes");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.put("mensaje", "Lista de estudiantes");
        response.put("estudiantes", estudiantes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> agregarEstudiante(Estudiante estudiante) {
        Map<String,Object> response = new HashMap<>();

        if(estudiante.getNombres() == null || estudiante.getNombres().isEmpty()){
            response.put("mensaje", "El codigo del estudiante estar vacio.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            Estudiante nuevoEstudiante = estudianteRepository.save(estudiante);
            response.put("mensaje", "Estudiante agregado exitosamente.");
            response.put("Estudiante", nuevoEstudiante);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("mensaje", "Error al agregar el estudiante: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> obtenerEstudiantePorId(Integer id) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);

        if(estudiante.isPresent()){

            return ResponseEntity.ok(estudiante.get());

        }else{

            Map<String, Object> response = new HashMap<>();

            response.put("mensaje", "Estudiante no encontrado.");

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarEstudiante(Estudiante estudiente, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarEstudiante(Long id) {
        return null;
    }
}