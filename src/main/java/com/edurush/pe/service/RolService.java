package com.edurush.pe.service;

import com.edurush.pe.model.Rol;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface RolService {

    ResponseEntity<Map<String, Object>> listaRoles();

    ResponseEntity<Map<String, Object>> agregarROL(Rol rol);
    ResponseEntity<Map<String, Object>> editarRol(Rol rol, Integer id);
    ResponseEntity<Map<String, Object>> eliminarRol(Integer id);



}
