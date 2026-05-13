package com.edurush.pe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edurush.pe.model.Seccion;
import com.edurush.pe.service.CursoService;
import com.edurush.pe.service.PeriodoService;
import com.edurush.pe.service.SeccionService;

@Controller
@RequestMapping("/admin/secciones")
public class SeccionController {

    @Autowired private SeccionService seccionService;
    @Autowired private CursoService cursoService;
    @Autowired private PeriodoService periodoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("secciones", seccionService.listarVigentes());
        model.addAttribute("titulo", "Gestión de Secciones");
        return "portal-admin/seccion/lista";
    }

    @GetMapping("/nuevo")
    public String formulario(Model model) {
        model.addAttribute("seccion", new Seccion());
        model.addAttribute("cursos", cursoService.listar());
        model.addAttribute("periodos", periodoService.listarAbiertos());
        model.addAttribute("titulo", "Nueva Sección Académica");
        return "portal-admin/seccion/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Seccion seccion) {
        seccionService.guardar(seccion);
        return "redirect:/admin/secciones";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id, Model model) {
        model.addAttribute("s", seccionService.buscarPorId(id));
        return "portal-admin/seccion/ver";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("seccion", seccionService.buscarPorId(id));
        model.addAttribute("cursos", cursoService.listar());
        model.addAttribute("periodos", periodoService.listarAbiertos());
        model.addAttribute("titulo", "Editar Sección");
        return "portal-admin/seccion/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        seccionService.eliminarLogico(id);
        return "redirect:/admin/secciones";
    }
}