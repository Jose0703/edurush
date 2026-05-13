package com.edurush.pe.controller;

import com.edurush.pe.model.Estudiante;
import com.edurush.pe.model.Usuario;
import com.edurush.pe.service.EstudianteService;
import com.edurush.pe.service.UsuarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("estudiantes", estudianteService.listarTodosActivos());
        return "portal-admin/estudiantes/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        model.addAttribute("usuarios", usuarioService.listarUsuariosEstudiantesSinAsignar());
        model.addAttribute("titulo", "Nuevo Estudiante");
        return "portal-admin/estudiantes/form";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model) {
        Estudiante e = estudianteService.buscarPorId(id);
        model.addAttribute("estudiante", e);
        
        List<Usuario> usuariosDisponibles = usuarioService.listarUsuariosEstudiantesSinAsignar();
        usuariosDisponibles.add(e.getUsuario()); 
        
        model.addAttribute("usuarios", usuariosDisponibles);
        model.addAttribute("titulo", "Editar Estudiante");
        return "portal-admin/estudiantes/form";
    }
    
    @GetMapping("/ver/{id}")
    public String verEstudiante(@PathVariable Integer id, Model model) {
        model.addAttribute("estudiante", estudianteService.buscarPorId(id));
        return "portal-admin/estudiantes/ver";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Estudiante estudiante) {
        estudianteService.guardarWeb(estudiante);
        return "redirect:/admin/estudiantes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        estudianteService.eliminarLogicoWeb(id);
        return "redirect:/admin/estudiantes";
    }
}