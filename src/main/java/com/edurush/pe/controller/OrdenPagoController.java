package com.edurush.pe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edurush.pe.model.OrdenPago;
import com.edurush.pe.service.EstudianteService;
import com.edurush.pe.service.MatriculaService;
import com.edurush.pe.service.OrdenPagoService;

@Controller
@RequestMapping("/admin/ordenes-pago")
public class OrdenPagoController {

    @Autowired private OrdenPagoService ordenService;
    @Autowired private EstudianteService estudianteService;
    @Autowired private MatriculaService matriculaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ordenes", ordenService.listarTodas());
        model.addAttribute("titulo", "Tesorería - Órdenes de Pago");
        return "portal-admin/orden-pago/lista";
    }

    @GetMapping("/nuevo")
    public String formulario(Model model) {
        model.addAttribute("orden", new OrdenPago());
        model.addAttribute("estudiantes", estudianteService.listarTodosActivos());
        model.addAttribute("matriculas", matriculaService.listarTodas());
        model.addAttribute("titulo", "Generar Nueva Orden");
        return "portal-admin/orden-pago/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute OrdenPago orden) {
        ordenService.guardar(orden);
        return "redirect:/admin/ordenes-pago";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("orden", ordenService.buscarPorId(id));
        model.addAttribute("estudiantes", estudianteService.listarTodosActivos());
        model.addAttribute("matriculas", matriculaService.listarTodas());
        model.addAttribute("titulo", "Editar Orden de Pago");
        return "portal-admin/orden-pago/form";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id, Model model) {
        model.addAttribute("o", ordenService.buscarPorId(id));
        return "portal-admin/orden-pago/ver";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        ordenService.eliminar(id);
        return "redirect:/admin/ordenes-pago";
    }
}