package com.uisrael.proyectoconsumo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteControlador {

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("titulo", "Clientes");
        model.addAttribute("contenido", "Cliente/listarCliente");
        return "layout/base";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("titulo", "Nuevo Cliente");
        model.addAttribute("contenido", "Cliente/formularioCliente");
        return "layout/base";
    }
}