package com.uisrael.proyectoconsumo.servicio.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.uisrael.proyectoconsumo.modelo.DetallePedidoModelo;
import com.uisrael.proyectoconsumo.modelo.ProductoModelo;
import com.uisrael.proyectoconsumo.servicio.DetallePedidoServicio;
import com.uisrael.proyectoconsumo.servicio.ProductoServicio;

@Service
public class DetallePedidoServicioImpl implements DetallePedidoServicio {

	private final RestTemplate restTemplate;
	private final ProductoServicio productoServicio; 

	@Value("${app.api.base-url}")
	private String baseUrl;

	public DetallePedidoServicioImpl(RestTemplate restTemplate, ProductoServicio productoServicio) {
		this.restTemplate = restTemplate;
		this.productoServicio = productoServicio;
	}

	@Override
	public List<DetallePedidoModelo> listar() {
		String url = baseUrl + "/api/detallePedido";
		DetallePedidoModelo[] data = restTemplate.getForObject(url, DetallePedidoModelo[].class);
		return data == null ? List.of() : Arrays.asList(data);
	}

	@Override
	public DetallePedidoModelo buscarPorId(Integer id) {
		String url = baseUrl + "/api/detallePedido/" + id;
		try {
			return restTemplate.getForObject(url, DetallePedidoModelo.class);
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND)
				return null;
			throw e;
		}
	}

	@Override
	public List<DetallePedidoModelo> listarPorPedido(Integer idDetallePedido) {
		DetallePedidoModelo detalle = buscarPorId(idDetallePedido);
		return detalle == null ? List.of() : List.of(detalle);
	}

	@Override
	public void guardar(DetallePedidoModelo detalle) {
		if (detalle.getId_producto() == null || detalle.getCantidad() == null || detalle.getCantidad() <= 0) {
			throw new IllegalArgumentException("Debe seleccionar producto y cantidad válida.");
		}

		ProductoModelo producto = productoServicio.buscarPorId(detalle.getId_producto());
		if (producto == null) {
			throw new IllegalArgumentException("Producto no existe.");
		}

		double precio = producto.getPrecio_unitario();
		double subtotal = precio * detalle.getCantidad();

		detalle.setPrecio_unitario(precio);
		detalle.setSubtotal(subtotal);

		String url = baseUrl + "/api/detallePedido";
		restTemplate.postForObject(url, detalle, DetallePedidoModelo.class);
	}

	@Override
	public void actualizar(DetallePedidoModelo detalle) {
		Integer id = detalle.getId_detalle_pedido();
		if (id == null)
			throw new IllegalArgumentException("ID detalle es requerido para actualizar.");

		if (detalle.getId_producto() == null || detalle.getCantidad() == null || detalle.getCantidad() <= 0) {
			throw new IllegalArgumentException("Debe seleccionar producto y cantidad válida.");
		}

		ProductoModelo producto = productoServicio.buscarPorId(detalle.getId_producto());
		if (producto == null)
			throw new IllegalArgumentException("Producto no existe.");

		double precio = producto.getPrecio_unitario();
		double subtotal = precio * detalle.getCantidad();

		detalle.setPrecio_unitario(precio);
		detalle.setSubtotal(subtotal);

		String url = baseUrl + "/api/detallePedido/" + id;
		restTemplate.put(url, detalle);
	}

	@Override
	public void eliminar(Integer id) {
		String url = baseUrl + "/api/detallePedido/" + id;
		restTemplate.delete(url);
	}
}
