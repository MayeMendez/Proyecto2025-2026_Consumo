package com.uisrael.proyectoconsumo.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.proyectoconsumo.modelo.RutaEntregaModelo;
import com.uisrael.proyectoconsumo.modelo.ZonaConRutasForm;
import com.uisrael.proyectoconsumo.modelo.ZonaEntregaModelo;
import com.uisrael.proyectoconsumo.servicio.RutaEntregaServicio;
import com.uisrael.proyectoconsumo.servicio.ZonaEntregaServicio;

@Controller
@RequestMapping("/zonas")
public class ZonaEntregaControlador {

	private final ZonaEntregaServicio zonaEntregaServicio;
	private final RutaEntregaServicio rutaEntregaServicio;
	private final RestTemplate restTemplate;

	public ZonaEntregaControlador(ZonaEntregaServicio zonaEntregaServicio, RutaEntregaServicio rutaEntregaServicio,
			RestTemplate restTemplate) {
		this.zonaEntregaServicio = zonaEntregaServicio;
		this.rutaEntregaServicio = rutaEntregaServicio;
		this.restTemplate = restTemplate;
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
		ZonaConRutasForm form = new ZonaConRutasForm();
		form.getRutas().add(new RutaEntregaModelo());

		model.addAttribute("form", form);
		model.addAttribute("titulo", "Nueva Zona + Rutas");
		model.addAttribute("contenido", "ZonaEntrega/zonaConRutas");
		return "layout/base";
	}

	@PostMapping("/guardar-con-rutas")
	public String guardarConRutas(@ModelAttribute("form") ZonaConRutasForm form, RedirectAttributes ra) {
		ZonaEntregaModelo zona = form.getZona();
		zonaEntregaServicio.guardar(zona);

		Integer zonaId = zona.getId_zona_entrega();

		int guardadas = 0;
		if (form.getRutas() != null) {
			for (RutaEntregaModelo r : form.getRutas()) {
				if (r == null)
					continue;
				if (r.getNombre_ruta() == null || r.getNombre_ruta().trim().isEmpty())
					continue;

				r.setId_zona_entrega(zonaId);
				rutaEntregaServicio.guardar(r);
				guardadas++;
			}
		}

		ra.addFlashAttribute("ok", "Zona guardada correctamente y " + guardadas + " ruta(s) registradas.");
		return "redirect:/zonas";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
		try {
			boolean tieneRutas = rutaEntregaServicio.listar().stream()
					.anyMatch(r -> r.getId_zona_entrega() != null && r.getId_zona_entrega().equals(id));

			if (tieneRutas) {
				ra.addFlashAttribute("mensaje", "No se puede eliminar: la zona tiene rutas asociadas.");
				ra.addFlashAttribute("tipo", "danger");
				return "redirect:/zonas";
			}

			zonaEntregaServicio.eliminar(id);

			ra.addFlashAttribute("mensaje", "Zona eliminada correctamente.");
			ra.addFlashAttribute("tipo", "success");
			return "redirect:/zonas";

		} catch (org.springframework.web.client.HttpStatusCodeException e) {
			ra.addFlashAttribute("mensaje", "Error del API: " + e.getStatusCode());
			ra.addFlashAttribute("tipo", "danger");
			return "redirect:/zonas";
		} catch (Exception e) {
			ra.addFlashAttribute("mensaje", "No se pudo eliminar la zona.");
			ra.addFlashAttribute("tipo", "danger");
			return "redirect:/zonas";
		}
	}

	@GetMapping("/editar-con-rutas/{id}")
	public String editarConRutas(@PathVariable Integer id, Model model) {
		ZonaEntregaModelo zona = zonaEntregaServicio.buscarPorId(id);

		ZonaConRutasForm form = new ZonaConRutasForm();
		form.setZona(zona);
		form.setRutas(rutaEntregaServicio.listar().stream()
				.filter(r -> r.getId_zona_entrega() != null && r.getId_zona_entrega().equals(id)).toList());

		model.addAttribute("form", form);
		model.addAttribute("titulo", "Editar Zona + Rutas");
		model.addAttribute("contenido", "ZonaEntrega/zonaConRutasEditar");
		return "layout/base";
	}

	@PostMapping("/actualizar-con-rutas")
	public String actualizarConRutas(@ModelAttribute("form") ZonaConRutasForm form, RedirectAttributes ra) {

		ZonaEntregaModelo zona = form.getZona();
		zonaEntregaServicio.actualizar(zona);

		Integer zonaId = zona.getId_zona_entrega();

		int creadas = 0, actualizadas = 0;

		if (form.getRutas() != null) {
			for (RutaEntregaModelo r : form.getRutas()) {
				if (r == null)
					continue;
				if (r.getNombre_ruta() == null || r.getNombre_ruta().trim().isEmpty())
					continue;

				r.setId_zona_entrega(zonaId);

				if (r.getId_ruta_entrega() == null || r.getId_ruta_entrega() == 0) {
					rutaEntregaServicio.guardar(r);
					creadas++;
				} else {
					rutaEntregaServicio.actualizar(r);
					actualizadas++;
				}
			}
		}

		ra.addFlashAttribute("ok",
				"Zona actualizada. Rutas: " + actualizadas + " actualizadas, " + creadas + " creadas.");
		return "redirect:/zonas";
	}
}
