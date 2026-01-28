package com.uisrael.proyectoconsumo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rutas")
public class RutaEntregaControlador {

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("titulo", "Rutas de Entrega");
        model.addAttribute("contenido", "RutaEntrega/listarRutaEntrega");
        return "layout/base";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("titulo", "Nueva Ruta");
        model.addAttribute("contenido", "RutaEntrega/formularioRutaEntrega");
        return "layout/base";
    }
}