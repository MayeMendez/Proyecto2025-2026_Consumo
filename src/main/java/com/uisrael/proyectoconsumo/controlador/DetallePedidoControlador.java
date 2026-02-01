package com.uisrael.proyectoconsumo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uisrael.proyectoconsumo.modelo.DetallePedidoModelo;
import com.uisrael.proyectoconsumo.servicio.DetallePedidoServicio;
import com.uisrael.proyectoconsumo.servicio.ProductoServicio;

@Controller
@RequestMapping("/detallePedido")
public class DetallePedidoControlador {

	private final DetallePedidoServicio detallePedidoServicio;
	private final ProductoServicio productoServicio;

	public DetallePedidoControlador(DetallePedidoServicio detallePedidoServicio, ProductoServicio productoServicio) {

		this.detallePedidoServicio = detallePedidoServicio;
		this.productoServicio = productoServicio;
	}

	@GetMapping("/nuevo/{idPedido}")
	public String nuevo(@PathVariable Integer idPedido, Model model) {

		DetallePedidoModelo detalle = new DetallePedidoModelo();
		detalle.setId_pedido(idPedido);

		model.addAttribute("detalle", detalle);
		model.addAttribute("productos", productoServicio.listar());
		model.addAttribute("titulo", "Agregar Detalle al Pedido #" + idPedido);
		model.addAttribute("contenido", "Pedido/formularioDetallePedido");

		return "layout/base";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute("detalle") DetallePedidoModelo detalle, RedirectAttributes ra) {

		try {
			detallePedidoServicio.guardar(detalle);
			ra.addFlashAttribute("mensaje", "Detalle creado correctamente");
			ra.addFlashAttribute("tipo", "success");
		} catch (Exception e) {
			ra.addFlashAttribute("mensaje", "No se pudo crear el detalle");
			ra.addFlashAttribute("tipo", "danger");
		}
		return "redirect:/pedidos";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model, RedirectAttributes ra) {

		DetallePedidoModelo detalle = detallePedidoServicio.buscarPorId(id);

		if (detalle == null) {
			ra.addFlashAttribute("mensaje", "El detalle no existe");
			ra.addFlashAttribute("tipo", "warning");
			return "redirect:/pedidos";
		}

		model.addAttribute("detalle", detalle);
		model.addAttribute("productos", productoServicio.listar());
		model.addAttribute("titulo", "Editar Detalle Pedido #" + id);
		model.addAttribute("contenido", "Pedido/formularioDetallePedido");

		return "layout/base";
	}

	@PostMapping("/actualizar")
	public String actualizar(@ModelAttribute("detalle") DetallePedidoModelo detalle, RedirectAttributes ra) {

		try {
			detallePedidoServicio.actualizar(detalle);
			ra.addFlashAttribute("mensaje", "Detalle actualizado correctamente");
			ra.addFlashAttribute("tipo", "success");
		} catch (Exception e) {
			ra.addFlashAttribute("mensaje", "No se pudo actualizar el detalle");
			ra.addFlashAttribute("tipo", "danger");
		}
		return "redirect:/pedidos";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
		try {
			detallePedidoServicio.eliminar(id);
			ra.addFlashAttribute("mensaje", "Detalle eliminado correctamente");
			ra.addFlashAttribute("tipo", "success");
		} catch (Exception e) {
			ra.addFlashAttribute("mensaje", "No se pudo eliminar el detalle");
			ra.addFlashAttribute("tipo", "danger");
		}
		return "redirect:/pedidos";
	}

}
