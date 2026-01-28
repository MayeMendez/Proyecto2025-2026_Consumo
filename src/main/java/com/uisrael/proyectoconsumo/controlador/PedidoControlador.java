package com.uisrael.proyectoconsumo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedidos")
public class PedidoControlador {

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("titulo", "Pedidos");
        model.addAttribute("contenido", "Pedido/listarPedido");
        return "layout/base";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("titulo", "Nuevo Pedido");
        model.addAttribute("contenido", "Pedido/formularioPedido");
        return "layout/base";
    }

    @GetMapping("/{id}/detalle")
    public String detalle(@PathVariable Integer id, Model model) {
        model.addAttribute("titulo", "Detalle Pedido #" + id);
        model.addAttribute("contenido", "Pedido/detallePedido");
        return "layout/base";
    }
}