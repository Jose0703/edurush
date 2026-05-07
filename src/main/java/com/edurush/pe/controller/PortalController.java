package com.edurush.pe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortalController {

    @GetMapping("/")
    public String homePrincipal() {
        return "portal-principal/home";
    }

    @GetMapping("/admin/home")
    public String homeAdmin() {
        return "portal-admin/home";
    }

    @GetMapping("/docente/home")
    public String homeDocente() {
        return "portal-docente/home";
    }

    @GetMapping("/estudiante/home")
    public String homeEstudiante() {
        return "portal-estudiante/home";
    }
}