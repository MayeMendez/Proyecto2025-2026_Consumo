package com.uisrael.proyectoconsumo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/productos")
public class ProductoControlador {

  @GetMapping
  public String listar(Model model) {
    model.addAttribute("titulo", "Productos");
    model.addAttribute("contenido", "Producto/listarProducto");
    return "layout/base";
  }

  @GetMapping("/nuevo")
  public String nuevo(Model model) {
    model.addAttribute("titulo", "Nuevo Producto");
    model.addAttribute("contenido", "Producto/formularioProducto");
    return "layout/base";
  }
}
