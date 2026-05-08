package com.edurush.pe.controller;

import com.edurush.pe.model.Rol;
import com.edurush.pe.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("roles", rolService.listarTodos());
        return "portal-admin/roles/lista";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id, Model model) {
        model.addAttribute("rol", rolService.buscarPorId(id));
        return "portal-admin/roles/ver";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("rol", new Rol());
        model.addAttribute("titulo", "Nuevo Rol de Sistema");
        return "portal-admin/roles/form";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model) {
        Rol r = rolService.buscarPorId(id);
        model.addAttribute("rol", r);
        model.addAttribute("titulo", "Editar Rol");
        return "portal-admin/roles/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Rol rol) {
        rolService.guardarWeb(rol);
        return "redirect:/admin/roles";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        rolService.eliminarLogicoWeb(id);
        return "redirect:/admin/roles";
    }
}