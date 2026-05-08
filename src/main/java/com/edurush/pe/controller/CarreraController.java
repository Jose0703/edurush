package com.edurush.pe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.edurush.pe.model.Carrera;
import com.edurush.pe.service.CarreraService;

@Controller
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("carreras", service.listar());
        
        return "portal-admin/carreras/carreras";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("carrera", new Carrera());
        
        return "portal-admin/carreras/form-carrera";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Carrera carrera) {
    	if (carrera.getEstado() == null || carrera.getEstado().isEmpty()) {
            carrera.setEstado("vigente");
        }

        service.guardar(carrera);

        return "redirect:/carreras";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("carrera", service.buscarPorId(id));
        
        return "portal-admin/carreras/form-carrera";
    }
    
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Integer id, Model model) {

        model.addAttribute("carrera", service.buscarPorId(id));

        return "portal-admin/carreras/detalle-carrera";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return "redirect:/carreras";
    }
}