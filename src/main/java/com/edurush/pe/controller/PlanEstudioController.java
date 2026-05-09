package com.edurush.pe.controller;

import com.edurush.pe.model.PlanEstudio;
import com.edurush.pe.repository.CarreraRepository;
import com.edurush.pe.service.PlanEstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/planes-estudio")
public class PlanEstudioController {

    @Autowired
    private PlanEstudioService planEstudioService;

    @Autowired
    private CarreraRepository carreraRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("planes", planEstudioService.listarTodosActivos());
        return "portal-admin/planes-estudio/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("planEstudio", new PlanEstudio());
        model.addAttribute("carreras", carreraRepository.findByEstado("vigente"));
        model.addAttribute("titulo", "Nuevo Plan de Estudio");
        return "portal-admin/planes-estudio/form";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model) {
        PlanEstudio planEstudio = planEstudioService.buscarPorId(id);
        model.addAttribute("planEstudio", planEstudio);
        model.addAttribute("carreras", carreraRepository.findByEstado("vigente"));
        model.addAttribute("titulo", "Editar Plan de Estudio");
        return "portal-admin/planes-estudio/form";
    }

    @GetMapping("/ver/{id}")
    public String verPlanEstudio(@PathVariable Integer id, Model model) {
        model.addAttribute("planEstudio", planEstudioService.buscarPorId(id));
        return "portal-admin/planes-estudio/ver";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute PlanEstudio planEstudio) {
        planEstudioService.guardarWeb(planEstudio);
        return "redirect:/admin/planes-estudio";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        planEstudioService.eliminarLogicoWeb(id);
        return "redirect:/admin/planes-estudio";
    }
}