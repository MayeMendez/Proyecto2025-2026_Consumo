package com.uisrael.proyectoconsumo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.proyectoconsumo.modelo.ZonaEntregaModelo;
import com.uisrael.proyectoconsumo.servicio.ZonaEntregaServicio;

@Controller
@RequestMapping("/zonas")
public class ZonaEntregaControlador {

	private final ZonaEntregaServicio zonaEntregaServicio;

	public ZonaEntregaControlador(ZonaEntregaServicio zonaEntregaServicio) {
		this.zonaEntregaServicio = zonaEntregaServicio;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("zonas", zonaEntregaServicio.listar());
		model.addAttribute("titulo", "Zonas de Entrega");
		model.addAttribute("contenido", "ZonaEntrega/listarZonaEntrega");
		return "layout/base";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("zona", new ZonaEntregaModelo());
		model.addAttribute("titulo", "Nueva Zona");
		model.addAttribute("contenido", "ZonaEntrega/formularioZonaEntrega");
		return "layout/base";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute("zona") ZonaEntregaModelo zona, RedirectAttributes ra) {
		zonaEntregaServicio.guardar(zona);
		ra.addFlashAttribute("ok", "Zona guardada correctamente.");
		return "redirect:/zonas";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		model.addAttribute("zona", zonaEntregaServicio.buscarPorId(id));
		model.addAttribute("titulo", "Editar Zona");
		model.addAttribute("contenido", "ZonaEntrega/formularioZonaEntrega");
		return "layout/base";
	}

	@PostMapping("/actualizar")
	public String actualizar(@ModelAttribute("zona") ZonaEntregaModelo zona, RedirectAttributes ra) {
		zonaEntregaServicio.actualizar(zona);
		ra.addFlashAttribute("ok", "Zona actualizada correctamente.");
		return "redirect:/zonas";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
		try {
			zonaEntregaServicio.eliminar(id);
			ra.addFlashAttribute("ok", "Zona eliminada correctamente.");
		} catch (Exception e) {
			ra.addFlashAttribute("error", "No se puede eliminar: la zona está asociada a rutas/pedidos.");
		}
		return "redirect:/zonas";
	}
}