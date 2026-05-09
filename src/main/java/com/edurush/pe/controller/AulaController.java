package com.edurush.pe.controller;

import com.edurush.pe.model.Aula;
import com.edurush.pe.service.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/aulas")
public class AulaController {

    @Autowired
    private AulaService aulaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("aulas", aulaService.listarTodasOperativas());
        return "portal-admin/aulas/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("aula", new Aula());
        model.addAttribute("titulo", "Nueva Aula");
        return "portal-admin/aulas/form";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model) {
        Aula aula = aulaService.buscarPorId(id);
        model.addAttribute("aula", aula);
        model.addAttribute("titulo", "Editar Aula");
        return "portal-admin/aulas/form";
    }

    @GetMapping("/ver/{id}")
    public String verAula(@PathVariable Integer id, Model model) {
        model.addAttribute("aula", aulaService.buscarPorId(id));
        return "portal-admin/aulas/ver";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Aula aula) {
        aulaService.guardarWeb(aula);
        return "redirect:/admin/aulas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        aulaService.eliminarLogicoWeb(id);
        return "redirect:/admin/aulas";
    }
}