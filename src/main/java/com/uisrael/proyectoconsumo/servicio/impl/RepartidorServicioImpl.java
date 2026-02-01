package com.uisrael.proyectoconsumo.servicio.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uisrael.proyectoconsumo.modelo.RepartidorModelo;
import com.uisrael.proyectoconsumo.servicio.RepartidorServicio;

@Service
public class RepartidorServicioImpl implements RepartidorServicio {

	private final RestTemplate restTemplate;

	@Value("${app.api.base-url}")
	private String baseUrl;

	public RepartidorServicioImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<RepartidorModelo> listar() {
		String url = baseUrl + "/api/repartidor";
		RepartidorModelo[] data = restTemplate.getForObject(url, RepartidorModelo[].class);
		return data == null ? List.of() : Arrays.asList(data);
	}

	@Override
	public RepartidorModelo buscarPorId(Integer id) {
		String url = baseUrl + "/api/repartidor/" + id;
		return restTemplate.getForObject(url, RepartidorModelo.class);
	}

	@Override
	public void guardar(RepartidorModelo repartidor) {
		String url = baseUrl + "/api/repartidor";
		restTemplate.postForObject(url, repartidor, RepartidorModelo.class);
	}

	@Override
	public void actualizar(RepartidorModelo repartidor) {
		Integer id = repartidor.getId_repartidor();
		String url = baseUrl + "/api/repartidor/" + id;
		restTemplate.put(url, repartidor);
	}

	@Override
	public void eliminar(Integer id) {
		String url = baseUrl + "/api/repartidor/" + id;
		restTemplate.delete(url);
	}

}
