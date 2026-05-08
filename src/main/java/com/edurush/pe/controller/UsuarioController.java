package com.edurush.pe.controller;

import com.edurush.pe.model.Usuario;
import com.edurush.pe.repository.RolRepository;
import com.edurush.pe.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolRepository rolRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodosActivos());
        return "portal-admin/usuarios/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolRepo.findAll());
        model.addAttribute("titulo", "Nuevo Usuario");
        return "portal-admin/usuarios/form";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model) {
        Usuario u = usuarioService.buscarPorId(id);
        model.addAttribute("usuario", u);
        model.addAttribute("roles", rolRepo.findAll());
        model.addAttribute("titulo", "Editar Usuario");
        return "portal-admin/usuarios/form";
    }

    @GetMapping("/ver/{id}")
    public String verUsuario(@PathVariable Integer id, Model model) {
        model.addAttribute("usuario", usuarioService.buscarPorId(id));
        return "portal-admin/usuarios/ver";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario) {
        usuarioService.guardar(usuario);
        return "redirect:/admin/usuarios";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        usuarioService.eliminar(id);
        return "redirect:/admin/usuarios";
    }
}