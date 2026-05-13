package com.edurush.pe.controller;

import com.edurush.pe.dto.MatriculaPostulanteDTO;
import com.edurush.pe.service.CarreraService;
import com.edurush.pe.service.PublicMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/matricula")
@SessionAttributes("matriculaDto") // Mantiene los datos vivos entre pasos
public class PublicMatriculaController {

    @Autowired private CarreraService carreraService;
    @Autowired private PublicMatriculaService publicMatriculaService;

    // Inicializa el DTO en la sesión la primera vez que entran al flujo
    @ModelAttribute("matriculaDto")
    public MatriculaPostulanteDTO inicializarDto() {
        return new MatriculaPostulanteDTO();
    }

    // PASO 1: Selección de Carrera, Turno y Modalidad
    @GetMapping("/paso1")
    public String verPaso1(Model model) {
        model.addAttribute("carreras", carreraService.listar());
        model.addAttribute("titulo", "Paso 1: Elección Académica");
        return "portal-principal/matricula/paso1";
    }

    @PostMapping("/paso1")
    public String procesarPaso1(@ModelAttribute("matriculaDto") MatriculaPostulanteDTO dto) {
        // Los datos se guardan automáticamente en la sesión gracias a @SessionAttributes
        return "redirect:/matricula/paso2";
    }

    // PASO 2: Datos Personales y Documentos
    @GetMapping("/paso2")
    public String verPaso2(Model model) {
        model.addAttribute("titulo", "Paso 2: Información del Postulante");
        return "portal-principal/matricula/paso2";
    }

    @PostMapping("/paso2")
    public String procesarPaso2(@ModelAttribute("matriculaDto") MatriculaPostulanteDTO dto, Model model) {
        // Validación de DNI (RF09)
        if (publicMatriculaService.existeDni(dto.getDni())) {
            model.addAttribute("error", "El DNI ingresado ya se encuentra registrado en nuestro sistema.");
            return "public/matricula/paso2";
        }
        return "redirect:/matricula/paso3";
    }

    // PASO 3: Proceso de Pago
    @GetMapping("/paso3")
    public String verPaso3(Model model) {
        model.addAttribute("titulo", "Paso 3: Registro de Pago");
        return "portal-principal/matricula/paso3";
    }

    @PostMapping("/paso3")
    public String procesarPaso3(@ModelAttribute("matriculaDto") MatriculaPostulanteDTO dto) {
        // Al darle finalizar en el paso 3, llamamos al service para guardar en SQL Server
        publicMatriculaService.registrarMatriculaCompleta(dto);
        return "redirect:/matricula/paso4";
    }

    // PASO 4: Confirmación y Credenciales
    @GetMapping("/paso4")
    public String verPaso4(Model model, @ModelAttribute("matriculaDto") MatriculaPostulanteDTO dto, SessionStatus status) {
        model.addAttribute("res", dto);
        model.addAttribute("titulo", "¡Bienvenido a EduRush!");
        
        // Limpiamos la sesión para que no se queden los datos ahí después de terminar
        status.setComplete(); 
        return "portal-principal/matricula/paso4";
    }
}