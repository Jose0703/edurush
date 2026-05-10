package com.edurush.pe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.edurush.pe.model.Pago;
import com.edurush.pe.service.PagoService;
import com.edurush.pe.service.OrdenPagoService;

@Controller
@RequestMapping("/admin/pagos")
public class PagoController {

    @Autowired private PagoService pagoService;
    @Autowired private OrdenPagoService ordenService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pagos", pagoService.listarTodos());
        model.addAttribute("titulo", "Historial de Pagos Recibidos");
        return "portal-admin/pago/lista";
    }

    @GetMapping("/nuevo")
    public String formulario(Model model) {
        model.addAttribute("pago", new Pago());
        model.addAttribute("ordenes", ordenService.listarTodas()); 
        model.addAttribute("titulo", "Registrar Nuevo Pago");
        return "portal-admin/pago/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Pago pago) {
        pagoService.guardar(pago);
        return "redirect:/admin/pagos";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id, Model model) {
        model.addAttribute("p", pagoService.buscarPorId(id));
        model.addAttribute("titulo", "Comprobante de Pago");
        return "portal-admin/pago/ver";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        pagoService.eliminar(id);
        return "redirect:/admin/pagos";
    }
}