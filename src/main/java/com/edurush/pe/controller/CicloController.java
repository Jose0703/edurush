package com.edurush.pe.controller;

import com.edurush.pe.model.Ciclo;
import com.edurush.pe.repository.PlanEstudioRepository;
import com.edurush.pe.service.CicloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/ciclos")
public class CicloController {

    @Autowired
    private CicloService cicloService;

    @Autowired
    private PlanEstudioRepository planEstudioRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ciclos", cicloService.listarTodosActivos());
        return "portal-admin/ciclos/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("ciclo", new Ciclo());
        model.addAttribute("planes", planEstudioRepository.findByEstado("vigente"));
        model.addAttribute("titulo", "Nuevo Ciclo");
        return "portal-admin/ciclos/form";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model) {
        Ciclo ciclo = cicloService.buscarPorId(id);
        model.addAttribute("ciclo", ciclo);
        model.addAttribute("planes", planEstudioRepository.findByEstado("vigente"));
        model.addAttribute("titulo", "Editar Ciclo");
        return "portal-admin/ciclos/form";
    }

    @GetMapping("/ver/{id}")
    public String verCiclo(@PathVariable Integer id, Model model) {
        model.addAttribute("ciclo", cicloService.buscarPorId(id));
        return "portal-admin/ciclos/ver";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Ciclo ciclo) {
        cicloService.guardarWeb(ciclo);
        return "redirect:/admin/ciclos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        cicloService.eliminarLogicoWeb(id);
        return "redirect:/admin/ciclos";
    }
}