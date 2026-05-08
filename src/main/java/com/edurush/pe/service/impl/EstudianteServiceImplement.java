package com.edurush.pe.service.impl;

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
        List<Estudiante> estudiantes = estudianteRepository.findByEstado("activo");
        if(estudiantes.isEmpty()){
            response.put("mensaje", "No se encontraron estudiantes");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.put("mensaje", "Lista de estudiantes");
        response.put("estudiantes", estudiantes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> buscarPorNombreApellido(String texto) {
        List<Estudiante> lista = estudianteRepository.buscarPorNombreApellido(texto);

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Resultados encontrados");
        response.put("cantidad", lista.size());
        response.put("data", lista);

        return ResponseEntity.ok(response);
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
    public ResponseEntity<Map<String, Object>> editarEstudiante(Estudiante estudiante, Integer id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Estudiante> estudianteExistente = estudianteRepository.findById(id);

        if (estudianteExistente.isPresent()) {
            Estudiante estudianteActual = estudianteExistente.get();

            if (estudiante.getNombres() == null || estudiante.getNombres().isEmpty()) {
                response.put("mensaje", "El nombre del estudiante no puede estar vacío.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);			}

            estudianteActual.setNombres(estudiante.getNombres());
            estudianteActual.setApellidos(estudiante.getApellidos());
            estudianteActual.setDni(estudiante.getDni());
            estudianteActual.setCelular(estudiante.getCelular());
            estudianteActual.setEmail(estudiante.getEmail());
            estudianteActual.setFechaNacimiento(estudiante.getFechaNacimiento());
            estudianteActual.setFechaRegistro(estudiante.getFechaRegistro());
            estudianteActual.setEstado(estudiante.getEstado());

            estudianteRepository.save(estudianteActual);

            response.put("estudiante", estudianteActual);
            response.put("mensaje", "Datos del estudiante modificados.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("mensaje", "Sin registros con ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    @Override
    public ResponseEntity<Map<String, Object>> eliminarEstudiante(Integer id) {
        Map<String, Object> response = new HashMap<>();

        Optional<Estudiante> estudianteOpt = estudianteRepository.findById(id);

        if (estudianteOpt.isEmpty()) {
            response.put("mensaje", "Estudiante no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        estudianteRepository.deleteById(id);

        response.put("mensaje", "Estudiante eliminado permanentemente");

        return ResponseEntity.ok(response);
    }
    
    // MÉTODOS PARA EL PORTAL WEB

    @Override
    public List<Estudiante> listarTodosActivos() {
        return estudianteRepository.findByEstado("activo");
    }

    @Override
    public Estudiante buscarPorId(Integer id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarWeb(Estudiante estudiante) {
        if (estudiante.getEstado() == null) {
            estudiante.setEstado("activo");
        }
        estudianteRepository.save(estudiante);
    }

    @Override
    public void eliminarLogicoWeb(Integer id) {
        Estudiante e = estudianteRepository.findById(id).orElse(null);
        if (e != null) {
            e.setEstado("inactivo");
            estudianteRepository.save(e);
        }
    }

}