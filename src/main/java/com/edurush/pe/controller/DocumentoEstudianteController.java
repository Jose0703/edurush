package com.edurush.pe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edurush.pe.model.DocumentoEstudiante;
import com.edurush.pe.service.DocumentoEstudianteService;
import com.edurush.pe.service.EstudianteService;

@Controller
@RequestMapping("/admin/documentos")
public class DocumentoEstudianteController {

    @Autowired
    private DocumentoEstudianteService docService;
    
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("documentos", docService.listarTodos()); 
        model.addAttribute("titulo", "Gestión de Documentos");
        return "portal-admin/documento-estudiante/lista";
    }

    @GetMapping("/nuevo")
    public String formulario(Model model) {
        model.addAttribute("documento", new DocumentoEstudiante());
        model.addAttribute("estudiantes", estudianteService.listarTodosActivos());
        model.addAttribute("titulo", "Registrar Documento");
        return "portal-admin/documento-estudiante/form";
    }
    
    @GetMapping("/ver/{id}")
    public String verDetalles(@PathVariable Integer id, Model model) {
        model.addAttribute("documento", docService.buscarPorId(id));
        return "portal-admin/documento-estudiante/ver";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute DocumentoEstudiante documento) {
        docService.guardar(documento);
        return "redirect:/admin/documentos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("documento", docService.buscarPorId(id));
        model.addAttribute("estudiantes", estudianteService.listarTodosActivos());
        model.addAttribute("titulo", "Actualizar Estado de Documento");
        return "portal-admin/documento-estudiante/form";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        docService.eliminar(id);
        return "redirect:/admin/documentos";
    }
}