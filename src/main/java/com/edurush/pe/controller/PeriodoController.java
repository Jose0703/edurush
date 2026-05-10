package com.edurush.pe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.edurush.pe.model.Periodo;
import com.edurush.pe.service.PeriodoService;

@Controller
@RequestMapping("/admin/periodos")
public class PeriodoController {

    @Autowired
    private PeriodoService periodoService;

    //Realizado por Mechato
    
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("periodos", periodoService.listarVigentes());
        model.addAttribute("titulo", "Gestión de Periodos Académicos");
        return "portal-admin/periodo/lista";
    }

    @GetMapping("/nuevo")
    public String formulario(Model model) {
        model.addAttribute("periodo", new Periodo());
        model.addAttribute("titulo", "Registrar Nuevo Periodo");
        return "portal-admin/periodo/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Periodo periodo) {
        periodoService.guardar(periodo);
        return "redirect:/admin/periodos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Periodo p = periodoService.buscarPorId(id);
        model.addAttribute("periodo", p);
        model.addAttribute("titulo", "Editar Periodo Académico");
        return "portal-admin/periodo/form";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id, Model model) {
        Periodo p = periodoService.buscarPorId(id);
        model.addAttribute("p", p);
        model.addAttribute("titulo", "Detalle del Periodo");
        return "portal-admin/periodo/ver";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        periodoService.eliminarLogico(id);
        return "redirect:/admin/periodos";
    }
}