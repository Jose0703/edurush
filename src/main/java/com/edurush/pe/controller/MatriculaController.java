package com.edurush.pe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.edurush.pe.model.Matricula;
import com.edurush.pe.service.MatriculaService;
import com.edurush.pe.service.EstudianteCarreraService;
import com.edurush.pe.service.PeriodoService;

@Controller
@RequestMapping("/admin/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private EstudianteCarreraService ecService;

    @Autowired
    private PeriodoService periodoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("matriculas", matriculaService.listarVigentes());
        model.addAttribute("titulo", "Gestión de Matrículas");
        return "portal-admin/matricula/lista";
    }

    @GetMapping("/nuevo")
    public String formulario(Model model) {
        model.addAttribute("matricula", new Matricula());
        model.addAttribute("estudianteCarreras", ecService.listarTodos());
        model.addAttribute("periodos", periodoService.listarAbiertos());
        model.addAttribute("titulo", "Nueva Matrícula Académica");
        return "portal-admin/matricula/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Matricula matricula) {
        matriculaService.guardar(matricula);
        return "redirect:/admin/matriculas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Matricula m = matriculaService.buscarPorId(id);
        model.addAttribute("matricula", m);
        model.addAttribute("estudianteCarreras", ecService.listarTodos());
        model.addAttribute("periodos", periodoService.listarAbiertos());
        model.addAttribute("titulo", "Editar Matrícula");
        return "portal-admin/matricula/form";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id, Model model) {
        model.addAttribute("m", matriculaService.buscarPorId(id));
        model.addAttribute("titulo", "Detalle de Matrícula");
        return "portal-admin/matricula/ver";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        matriculaService.eliminarLogico(id);
        return "redirect:/admin/matriculas";
    }
}