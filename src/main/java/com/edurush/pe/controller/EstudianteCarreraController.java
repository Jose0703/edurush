package com.edurush.pe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edurush.pe.model.EstudianteCarrera;
import com.edurush.pe.service.CarreraService;
import com.edurush.pe.service.EstudianteCarreraService;
import com.edurush.pe.service.EstudianteService;

@Controller
@RequestMapping("/admin/estudiante-carrera")
public class EstudianteCarreraController {

    @Autowired private EstudianteCarreraService ecService;
    @Autowired private EstudianteService estudianteService;
    @Autowired private CarreraService carreraService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", ecService.listarTodos());
        model.addAttribute("titulo", "Asignación de Carreras");
        return "portal-admin/estudiante-carrera/lista";
    }

    @GetMapping("/nuevo")
    public String formulario(Model model) {
        model.addAttribute("estudianteCarrera", new EstudianteCarrera());
        model.addAttribute("estudiantes", estudianteService.listarTodosActivos());
        model.addAttribute("carreras", carreraService.listar());
        model.addAttribute("titulo", "Nueva Asignación");
        return "portal-admin/estudiante-carrera/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute EstudianteCarrera ec) {
        ecService.guardar(ec);
        return "redirect:/admin/estudiante-carrera";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("estudianteCarrera", ecService.buscarPorId(id));
        model.addAttribute("estudiantes", estudianteService.listarTodosActivos());
        model.addAttribute("carreras", carreraService.listar());
        model.addAttribute("titulo", "Actualizar Asignación");
        return "portal-admin/estudiante-carrera/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        ecService.eliminar(id);
        return "redirect:/admin/estudiante-carrera";
    }
}
