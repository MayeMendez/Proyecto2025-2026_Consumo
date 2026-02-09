package com.uisrael.proyectoconsumo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.proyectoconsumo.servicio.RutaEntregaServicio;

@Controller
@RequestMapping("/rutas")
public class RutaEntregaControlador {

	private final RutaEntregaServicio rutaEntregaServicio;

	public RutaEntregaControlador(RutaEntregaServicio rutaEntregaServicio) {
		this.rutaEntregaServicio = rutaEntregaServicio;
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, @RequestParam(required = false) Integer zonaId,
			RedirectAttributes ra) {
		try {
			rutaEntregaServicio.eliminar(id);
			ra.addFlashAttribute("mensaje", "Ruta eliminada correctamente.");
			ra.addFlashAttribute("tipo", "success");

		} catch (HttpClientErrorException.Conflict e) {
			ra.addFlashAttribute("mensaje", "No se puede eliminar: la ruta está asociada a pedidos.");
			ra.addFlashAttribute("tipo", "danger");

		} catch (HttpClientErrorException e) {
			ra.addFlashAttribute("mensaje", "Error del API: " + e.getStatusCode());
			ra.addFlashAttribute("tipo", "danger");

		} catch (Exception e) {
			ra.addFlashAttribute("mensaje", "No se pudo eliminar la ruta.");
			ra.addFlashAttribute("tipo", "danger");
		}

		if (zonaId != null)
			return "redirect:/zonas/editar-con-rutas/" + zonaId;
		return "redirect:/zonas";
	}
}
