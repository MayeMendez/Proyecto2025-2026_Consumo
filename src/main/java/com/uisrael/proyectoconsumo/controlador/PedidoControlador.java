package com.uisrael.proyectoconsumo.controlador;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.proyectoconsumo.modelo.DetallePedidoModelo;
import com.uisrael.proyectoconsumo.modelo.PedidoModelo;
import com.uisrael.proyectoconsumo.servicio.ClienteServicio;
import com.uisrael.proyectoconsumo.servicio.DetallePedidoServicio;
import com.uisrael.proyectoconsumo.servicio.PedidoServicio;
import com.uisrael.proyectoconsumo.servicio.RepartidorServicio;
import com.uisrael.proyectoconsumo.servicio.RutaEntregaServicio;

@Controller
@RequestMapping("/pedidos")
public class PedidoControlador {

	private final PedidoServicio pedidoServicio;
	private final ClienteServicio clienteServicio;
	private final RutaEntregaServicio rutaEntregaServicio;
	private final RepartidorServicio repartidorServicio;
	private final DetallePedidoServicio detallePedidoServicio;

	public PedidoControlador(PedidoServicio pedidoServicio, ClienteServicio clienteServicio,
			RutaEntregaServicio rutaEntregaServicio, RepartidorServicio repartidorServicio,
			DetallePedidoServicio detallePedidoServicio) {
		this.pedidoServicio = pedidoServicio;
		this.clienteServicio = clienteServicio;
		this.rutaEntregaServicio = rutaEntregaServicio;
		this.repartidorServicio = repartidorServicio;
		this.detallePedidoServicio = detallePedidoServicio;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("pedidos", pedidoServicio.listar());
		model.addAttribute("titulo", "Pedidos");
		model.addAttribute("contenido", "Pedido/listarPedido");
		return "layout/base";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("pedido", new PedidoModelo());
		model.addAttribute("clientes", clienteServicio.listar());
		model.addAttribute("rutas", rutaEntregaServicio.listar());
		model.addAttribute("repartidores", repartidorServicio.listar());
		model.addAttribute("titulo", "Nuevo Pedido");
		model.addAttribute("contenido", "Pedido/formularioPedido");
		return "layout/base";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute("pedido") PedidoModelo pedido, RedirectAttributes ra) {
		try {
			pedidoServicio.guardar(pedido);
			ra.addFlashAttribute("mensaje", "Pedido creado correctamente");
			ra.addFlashAttribute("tipo", "success");
		} catch (Exception e) {
			ra.addFlashAttribute("mensaje", "No se pudo crear el pedido: " + e.getMessage());
			ra.addFlashAttribute("tipo", "danger");
		}
		return "redirect:/pedidos";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model, RedirectAttributes ra) {
		try {
			PedidoModelo pedido = pedidoServicio.buscarPorId(id);
			if (pedido == null) {
				ra.addFlashAttribute("mensaje", "No existe el pedido con ID: " + id);
				ra.addFlashAttribute("tipo", "warning");
				return "redirect:/pedidos";
			}

			model.addAttribute("pedido", pedido);
			model.addAttribute("clientes", clienteServicio.listar());
			model.addAttribute("rutas", rutaEntregaServicio.listar());
			model.addAttribute("repartidores", repartidorServicio.listar());
			model.addAttribute("titulo", "Editar Pedido");
			model.addAttribute("contenido", "Pedido/formularioPedido");
			return "layout/base";

		} catch (Exception e) {
			ra.addFlashAttribute("mensaje", "Error al cargar pedido: " + e.getMessage());
			ra.addFlashAttribute("tipo", "danger");
			return "redirect:/pedidos";
		}
	}

	@PostMapping("/actualizar")
	public String actualizar(@ModelAttribute("pedido") PedidoModelo pedido, RedirectAttributes ra) {
		try {
			pedidoServicio.actualizar(pedido);
			ra.addFlashAttribute("mensaje", "Pedido actualizado correctamente");
			ra.addFlashAttribute("tipo", "warning");
		} catch (Exception e) {
			ra.addFlashAttribute("mensaje", "No se pudo actualizar el pedido: " + e.getMessage());
			ra.addFlashAttribute("tipo", "danger");
		}
		return "redirect:/pedidos";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
		try {
			pedidoServicio.eliminar(id);
			ra.addFlashAttribute("mensaje", "Pedido eliminado correctamente");
			ra.addFlashAttribute("tipo", "success");
		} catch (Exception e) {
			ra.addFlashAttribute("mensaje", "No se puede eliminar: el pedido tiene detalle asociado");
			ra.addFlashAttribute("tipo", "danger");
		}
		return "redirect:/pedidos";
	}


	@GetMapping("/detallePedido/{idDetalle}")
	public String detallePorIdDetalle(@PathVariable Integer idDetalle, Model model, RedirectAttributes ra) {
		try {
			DetallePedidoModelo detalle = detallePedidoServicio.buscarPorId(idDetalle);

			if (detalle == null) {
				ra.addFlashAttribute("mensaje", "No existe detalle con ID: " + idDetalle);
				ra.addFlashAttribute("tipo", "warning");
				return "redirect:/pedidos";
			}

			model.addAttribute("titulo", "Detalle Pedido (ID Detalle) #" + idDetalle);
			model.addAttribute("detalles", List.of(detalle));
			model.addAttribute("contenido", "Pedido/detallePedido");
			return "layout/base";

		} catch (Exception e) {
			ra.addFlashAttribute("mensaje", "Error al consultar detalle: " + e.getMessage());
			ra.addFlashAttribute("tipo", "danger");
			return "redirect:/pedidos";
		}
	}

	
	@GetMapping("/{id}/detalle")
	public String detalleNoDisponible(@PathVariable Integer id, RedirectAttributes ra) {
		ra.addFlashAttribute("mensaje", "No disponible: la API solo soporta /api/detallePedido/{id_detalle_pedido}.");
		ra.addFlashAttribute("tipo", "warning");
		return "redirect:/pedidos";
	}
}
