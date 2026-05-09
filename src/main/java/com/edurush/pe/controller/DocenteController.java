package com.edurush.pe.controller;

import com.edurush.pe.model.Docente;
import com.edurush.pe.repository.UsuarioRepository;
import com.edurush.pe.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/docentes")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("docentes", docenteService.listarTodosActivos());
        return "portal-admin/docentes/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("docente", new Docente());
        model.addAttribute("usuarios", usuarioRepo.findByEstado("activo"));
        model.addAttribute("titulo", "Nuevo Docente");
        return "portal-admin/docentes/form";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model) {
        Docente docente = docenteService.buscarPorId(id);
        model.addAttribute("docente", docente);
        model.addAttribute("usuarios", usuarioRepo.findByEstado("activo"));
        model.addAttribute("titulo", "Editar Docente");
        return "portal-admin/docentes/form";
    }

    @GetMapping("/ver/{id}")
    public String verDocente(@PathVariable Integer id, Model model) {
        Docente docente = docenteService.buscarPorId(id);
        model.addAttribute("docente", docente);
        return "portal-admin/docentes/ver";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Docente docente) {
        docenteService.guardarWeb(docente);
        return "redirect:/admin/docentes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        docenteService.eliminarLogicoWeb(id);
        return "redirect:/admin/docentes";
    }
}