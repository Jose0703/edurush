package com.edurush.pe.service.impl;

import com.edurush.pe.model.Rol;
import com.edurush.pe.repository.RolRepository;
import com.edurush.pe.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RolServiceImplement implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public ResponseEntity<Map<String, Object>> listaRoles() {
        Map<String,Object> response = new HashMap<>();

        List<Rol> lista = rolRepository.findByEstado("activo");
        response.put("data", lista);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> agregarROL(Rol rol) {
        Map<String, Object> response = new HashMap<>();

        rol.setEstado("activo");

        Rol saved = rolRepository.save(rol);
        response.put("mensaje", "Rol agregado correctamente");
        response.put("rol", saved);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarRol(Rol rol, Integer id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Rol> rolOpt = rolRepository.findById(id);

        if(rolOpt.isEmpty()){
            response.put("mensaje", "Rol no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Rol existente = rolOpt.get();

        existente.setNombre(rol.getNombre());
        existente.setEstado(rol.getEstado());

        rolRepository.save(existente);

        response.put("mensaje", "Rol actualizado correctamente");
        response.put("rol", existente);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarRol(Integer id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Rol> rolOpt = rolRepository.findById(id);

        if(rolOpt.isEmpty()){
            response.put("mensaje", "Rol no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Rol rol = rolOpt.get();

        rol.setEstado("inactivo");
        rolRepository.save(rol);

        response.put("mensaje", "Rol desactivo correctamente");
        return ResponseEntity.ok(response);
    }
    
    // MÉTODOS PARA EL PORTAL WEB
    
    @Override
    public List<Rol> listarTodos() {
        return rolRepository.findAll();
    }

    @Override
    public Rol buscarPorId(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarWeb(Rol rol) {
        if (rol.getEstado() == null || rol.getEstado().isEmpty()) {
            rol.setEstado("activo");
        }
        rolRepository.save(rol);
    }

    @Override
    public void eliminarLogicoWeb(Integer id) {
        Rol rol = rolRepository.findById(id).orElse(null);
        if (rol != null) {
            rol.setEstado("inactivo");
            rolRepository.save(rol);
        }
    }
}
