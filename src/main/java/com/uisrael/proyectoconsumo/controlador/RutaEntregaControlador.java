package com.uisrael.proyectoconsumo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.proyectoconsumo.modelo.RutaEntregaModelo;
import com.uisrael.proyectoconsumo.servicio.RutaEntregaServicio;
import com.uisrael.proyectoconsumo.servicio.ZonaEntregaServicio;

@Controller
@RequestMapping("/rutas")
public class RutaEntregaControlador {

	private final RutaEntregaServicio rutaEntregaServicio;
	private final ZonaEntregaServicio zonaEntregaServicio;

	public RutaEntregaControlador(RutaEntregaServicio rutaEntregaServicio, ZonaEntregaServicio zonaEntregaServicio) {
		this.rutaEntregaServicio = rutaEntregaServicio;
		this.zonaEntregaServicio = zonaEntregaServicio;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("rutas", rutaEntregaServicio.listar());
		model.addAttribute("titulo", "Rutas de Entrega");
		model.addAttribute("contenido", "RutaEntrega/listarRutaEntrega");
		return "layout/base";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("ruta", new RutaEntregaModelo());
		model.addAttribute("zonas", zonaEntregaServicio.listar());
		model.addAttribute("titulo", "Nueva Ruta");
		model.addAttribute("contenido", "RutaEntrega/formularioRutaEntrega");
		return "layout/base";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute("ruta") RutaEntregaModelo ruta, RedirectAttributes redirectAttributes) {
		try {
			rutaEntregaServicio.guardar(ruta);
			redirectAttributes.addFlashAttribute("mensaje", "Ruta creada correctamente");
			redirectAttributes.addFlashAttribute("tipo", "success");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensaje", "No se pudo crear la ruta");
			redirectAttributes.addFlashAttribute("tipo", "danger");
		}
		return "redirect:/rutas";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		RutaEntregaModelo ruta = rutaEntregaServicio.buscarPorId(id);
		model.addAttribute("ruta", ruta);
		model.addAttribute("zonas", zonaEntregaServicio.listar());
		model.addAttribute("titulo", "Editar Ruta");
		model.addAttribute("contenido", "RutaEntrega/formularioRutaEntrega");
		return "layout/base";
	}

	@PostMapping("/actualizar")
	public String actualizar(@ModelAttribute("ruta") RutaEntregaModelo ruta, RedirectAttributes redirectAttributes) {
		try {
			rutaEntregaServicio.actualizar(ruta);
			redirectAttributes.addFlashAttribute("mensaje", "Ruta actualizada correctamente");
			redirectAttributes.addFlashAttribute("tipo", "warning");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensaje", "No se pudo actualizar la ruta");
			redirectAttributes.addFlashAttribute("tipo", "danger");
		}
		return "redirect:/rutas";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		try {
			rutaEntregaServicio.eliminar(id);
			redirectAttributes.addFlashAttribute("mensaje", "Ruta eliminada correctamente");
			redirectAttributes.addFlashAttribute("tipo", "success");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensaje",
					"No se puede eliminar la ruta porque está asociada a un pedido");
			redirectAttributes.addFlashAttribute("tipo", "danger");
		}
		return "redirect:/rutas";
	}
}