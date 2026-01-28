package com.uisrael.proyectoconsumo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/repartidores")
public class RepartidorControlador {

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("titulo", "Repartidores");
        model.addAttribute("contenido", "Repartidor/listarRepartidor");
        return "layout/base";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("titulo", "Nuevo Repartidor");
        model.addAttribute("contenido", "Repartidor/formularioRepartidor");
        return "layout/base";
    }
}