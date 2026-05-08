package com.edurush.pe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.edurush.pe.model.Curso;
import com.edurush.pe.service.CursoService;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    @GetMapping
    public String listar(Model model) {

        model.addAttribute("cursos", service.listar());

        return "portal-admin/cursos/cursos";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("curso", new Curso());

        return "portal-admin/cursos/form-curso";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Curso curso) {

        if (curso.getEstado() == null || curso.getEstado().isEmpty()) {
            curso.setEstado("activo");
        }

        service.guardar(curso);

        return "redirect:/cursos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {

        model.addAttribute("curso", service.buscarPorId(id));

        return "portal-admin/cursos/form-curso";
    }


    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Integer id, Model model) {

        model.addAttribute("curso", service.buscarPorId(id));

        return "portal-admin/cursos/detalle-curso";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {

        service.eliminar(id);

        return "redirect:/cursos";
    }
}