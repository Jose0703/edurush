package com.edurush.pe.controller;

import com.edurush.pe.model.Rol;
import com.edurush.pe.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listar() {
        return rolService.listaRoles();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregar(@RequestBody Rol rol) {
        return rolService.agregarROL(rol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editar(@RequestBody Rol rol, @PathVariable Integer id) {
        return rolService.editarRol(rol, id);
    }

    @PutMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Integer id) {
        return rolService.eliminarRol(id);
    }
}
