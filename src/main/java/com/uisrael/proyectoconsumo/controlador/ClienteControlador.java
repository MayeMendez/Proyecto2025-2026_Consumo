package com.uisrael.proyectoconsumo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.proyectoconsumo.modelo.ClienteModelo;
import com.uisrael.proyectoconsumo.servicio.ClienteServicio;

@Controller
@RequestMapping("/clientes")
public class ClienteControlador {

	private final ClienteServicio clienteServicio;

	public ClienteControlador(ClienteServicio clienteServicio) {
		this.clienteServicio = clienteServicio;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("clientes", clienteServicio.listar());
		model.addAttribute("titulo", "Clientes");
		model.addAttribute("contenido", "Cliente/listarCliente");
		return "layout/base";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {

		boolean consumidorFinalExiste = clienteServicio.existePorIdentificacion("9999999999");

		model.addAttribute("cliente", new ClienteModelo(0, "", "", "", "", "", "", ""));
		model.addAttribute("consumidorFinalExiste", consumidorFinalExiste);

		model.addAttribute("titulo", "Nuevo Cliente");
		model.addAttribute("contenido", "Cliente/formularioCliente");
		return "layout/base";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute("cliente") ClienteModelo cliente, RedirectAttributes ra) {
		try {
			clienteServicio.guardar(cliente);
			ra.addFlashAttribute("mensaje", "Cliente creado correctamente");
			ra.addFlashAttribute("tipo", "success");
			return "redirect:/clientes";
		} catch (Exception e) {
			ra.addFlashAttribute("mensaje", "No se pudo guardar: la identificación ya existe");
			ra.addFlashAttribute("tipo", "danger");
			return "redirect:/clientes/nuevo";
		}
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		model.addAttribute("cliente", clienteServicio.buscarPorId(id));
		model.addAttribute("titulo", "Editar Cliente");
		model.addAttribute("contenido", "Cliente/formularioCliente");
		return "layout/base";
	}

	@PostMapping("/actualizar")
	public String actualizar(@ModelAttribute("cliente") ClienteModelo cliente, RedirectAttributes redirectAttributes) {

		clienteServicio.actualizar(cliente);

		redirectAttributes.addFlashAttribute("mensaje", "Cliente actualizado correctamente");
		redirectAttributes.addFlashAttribute("tipo", "success");

		return "redirect:/clientes";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
	    try {
	        clienteServicio.eliminar(id);
	        ra.addFlashAttribute("mensaje", "Cliente eliminado correctamente.");
	        ra.addFlashAttribute("tipo", "success"); 
	    } catch (Exception e) {
	        ra.addFlashAttribute("mensaje",
	            "No se puede eliminar: el cliente está asociado a uno o más pedidos.");
	        ra.addFlashAttribute("tipo", "info"); 
	    }
	    return "redirect:/clientes";
	}

}