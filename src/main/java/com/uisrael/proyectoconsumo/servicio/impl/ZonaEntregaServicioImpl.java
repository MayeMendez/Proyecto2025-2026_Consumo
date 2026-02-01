package com.uisrael.proyectoconsumo.servicio.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uisrael.proyectoconsumo.modelo.ZonaEntregaModelo;
import com.uisrael.proyectoconsumo.servicio.ZonaEntregaServicio;

@Service
public class ZonaEntregaServicioImpl implements ZonaEntregaServicio {

	private final RestTemplate restTemplate;

	@Value("${app.api.base-url}")
	private String baseUrl;

	public ZonaEntregaServicioImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<ZonaEntregaModelo> listar() {
		String url = baseUrl + "/api/zonaEntrega";
		ZonaEntregaModelo[] data = restTemplate.getForObject(url, ZonaEntregaModelo[].class);
		return data == null ? List.of() : Arrays.asList(data);
	}

	@Override
	public ZonaEntregaModelo buscarPorId(Integer id) {
		String url = baseUrl + "/api/zonaEntrega/" + id;
		return restTemplate.getForObject(url, ZonaEntregaModelo.class);
	}

	@Override
	public void guardar(ZonaEntregaModelo zona) {
		String url = baseUrl + "/api/zonaEntrega";
		restTemplate.postForObject(url, zona, ZonaEntregaModelo.class);
	}

	@Override
	public void actualizar(ZonaEntregaModelo zona) {
		Integer id = zona.getId_zona_entrega();
		String url = baseUrl + "/api/zonaEntrega/" + id;
		restTemplate.put(url, zona);
	}

	@Override
	public void eliminar(Integer id) {
		String url = baseUrl + "/api/zonaEntrega/" + id;
		restTemplate.delete(url);
	}

}
