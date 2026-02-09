package com.uisrael.proyectoconsumo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.proyectoconsumo.modelo.RepartidorModelo;
import com.uisrael.proyectoconsumo.servicio.RepartidorServicio;

@Controller
@RequestMapping("/repartidores")
public class RepartidorControlador {

	private final RepartidorServicio repartidorServicio;

	public RepartidorControlador(RepartidorServicio repartidorServicio) {
		this.repartidorServicio = repartidorServicio;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("repartidores", repartidorServicio.listar());
		model.addAttribute("titulo", "Repartidores");
		model.addAttribute("contenido", "Repartidor/listarRepartidor");
		return "layout/base";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("repartidor", new RepartidorModelo());
		model.addAttribute("titulo", "Nuevo Repartidor");
		model.addAttribute("contenido", "Repartidor/formularioRepartidor");
		return "layout/base";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute("repartidor") RepartidorModelo repartidor,
			RedirectAttributes redirectAttributes) {

		repartidorServicio.guardar(repartidor);

		redirectAttributes.addFlashAttribute("mensaje", "Repartidor creado correctamente");
		redirectAttributes.addFlashAttribute("tipo", "success");

		return "redirect:/repartidores";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		RepartidorModelo repartidor = repartidorServicio.buscarPorId(id);
		model.addAttribute("repartidor", repartidor);
		model.addAttribute("titulo", "Editar Repartidor");
		model.addAttribute("contenido", "Repartidor/formularioRepartidor");
		return "layout/base";
	}

	@PostMapping("/actualizar")
	public String actualizar(@ModelAttribute("repartidor") RepartidorModelo repartidor,
			RedirectAttributes redirectAttributes) {

		repartidorServicio.actualizar(repartidor);

		redirectAttributes.addFlashAttribute("mensaje", "Repartidor actualizado correctamente");
		redirectAttributes.addFlashAttribute("tipo", "warning");

		return "redirect:/repartidores";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
		try {
			repartidorServicio.eliminar(id);
			ra.addFlashAttribute("mensaje", "Repartidor eliminado correctamente.");
			ra.addFlashAttribute("tipo", "success");
		} catch (Exception e) {
			ra.addFlashAttribute("mensaje", "No se puede eliminar: el repartidor está ligado a pedidos.");
			ra.addFlashAttribute("tipo", "danger");
		}
		return "redirect:/repartidores";
	}

}