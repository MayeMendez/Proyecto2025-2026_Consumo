package com.uisrael.proyectoconsumo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zonas")
public class ZonaEntregaControlador {

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("titulo", "Zonas de Entrega");
        model.addAttribute("contenido", "ZonaEntrega/listarZonaEntrega");
        return "layout/base";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("titulo", "Nueva Zona");
        model.addAttribute("contenido", "ZonaEntrega/formularioZonaEntrega");
        return "layout/base";
    }
}