package com.uisrael.proyectoconsumo.servicio.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uisrael.proyectoconsumo.modelo.PedidoModelo;
import com.uisrael.proyectoconsumo.servicio.PedidoServicio;

@Service
public class PedidosServicioImpl implements PedidoServicio {

	private final RestTemplate restTemplate;

	@Value("${app.api.base-url}")
	private String baseUrl;

	public PedidosServicioImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

	@Override
	public List<PedidoModelo> listar() {
		String url = baseUrl + "/api/pedido";
		PedidoModelo[] data = restTemplate.getForObject(url, PedidoModelo[].class);
		return data == null ? List.of() : Arrays.asList(data);
	}

	@Override
	public PedidoModelo buscarPorId(Integer id) {
		String url = baseUrl + "/api/pedido/" + id;
		return restTemplate.getForObject(url, PedidoModelo.class);
	}

	@Override
	public void guardar(PedidoModelo pedido) {
		String url = baseUrl + "/api/pedido";
		restTemplate.postForObject(url, pedido, PedidoModelo.class);
	}

	@Override
	public void actualizar(PedidoModelo pedido) {
		Integer id = pedido.getId_pedido();
		String url = baseUrl + "/api/pedido/" + id;
		restTemplate.put(url, pedido);
	}

	@Override
	public void eliminar(Integer id) {
		String url = baseUrl + "/api/pedido/" + id;
		restTemplate.delete(url);
	}

}