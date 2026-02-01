package com.uisrael.proyectoconsumo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.proyectoconsumo.modelo.ProductoModelo;
import com.uisrael.proyectoconsumo.servicio.ProductoServicio;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/productos")
public class ProductoControlador {

	private final ProductoServicio productoServicio;

	public ProductoControlador(ProductoServicio productoServicio) {
		this.productoServicio = productoServicio;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("productos", productoServicio.listar());
		model.addAttribute("titulo", "Productos");
		model.addAttribute("contenido", "Producto/listarProducto");
		return "layout/base";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("producto", new ProductoModelo());
		model.addAttribute("titulo", "Nuevo Producto");
		model.addAttribute("contenido", "Producto/formularioProducto");
		return "layout/base";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute("producto") ProductoModelo producto, RedirectAttributes redirectAttributes) {

		productoServicio.guardar(producto);

		redirectAttributes.addFlashAttribute("mensaje", "Producto creado correctamente");
		redirectAttributes.addFlashAttribute("tipo", "success");

		return "redirect:/productos";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		ProductoModelo producto = productoServicio.buscarPorId(id);
		model.addAttribute("producto", producto);
		model.addAttribute("titulo", "Editar Producto");
		model.addAttribute("contenido", "Producto/formularioProducto");
		return "layout/base";
	}

	@PostMapping("/actualizar")
	public String actualizar(@ModelAttribute("producto") ProductoModelo producto,
			RedirectAttributes redirectAttributes) {

		productoServicio.actualizar(producto);

		redirectAttributes.addFlashAttribute("mensaje", "Producto actualizado correctamente");
		redirectAttributes.addFlashAttribute("tipo", "warning");

		return "redirect:/productos";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {

		productoServicio.eliminar(id);

		redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado correctamente");
		redirectAttributes.addFlashAttribute("tipo", "danger");

		return "redirect:/productos";
	}
}
