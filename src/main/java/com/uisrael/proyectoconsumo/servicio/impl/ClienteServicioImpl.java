package com.uisrael.proyectoconsumo.servicio.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uisrael.proyectoconsumo.modelo.ClienteModelo;
import com.uisrael.proyectoconsumo.servicio.ClienteServicio;

@Service
public class ClienteServicioImpl implements ClienteServicio {

	private final RestTemplate restTemplate;
	
	@Value("${app.api.base-url:http://localhost:8080}")
	private String apiBaseUrl;


	@Value("${app.api.base-url}")
	private String baseUrl;

	public ClienteServicioImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<ClienteModelo> listar() {
		String url = baseUrl + "/api/cliente";
		ClienteModelo[] data = restTemplate.getForObject(url, ClienteModelo[].class);
		return data == null ? List.of() : Arrays.asList(data);
	}

	@Override
	public ClienteModelo buscarPorId(Integer id) {
		String url = baseUrl + "/api/cliente/" + id;
		return restTemplate.getForObject(url, ClienteModelo.class);
	}

	@Override
	public ClienteModelo guardar(ClienteModelo cliente) {
		String url = baseUrl + "/api/cliente";
		return restTemplate.postForObject(url, cliente, ClienteModelo.class);
	}

	@Override
	public void actualizar(ClienteModelo cliente) {
		Integer id = cliente.getId_cliente();
		String url = baseUrl + "/api/cliente/" + id;
		restTemplate.put(url, cliente);
	}

	@Override
	public void eliminar(Integer id) {
		String url = baseUrl + "/api/cliente/" + id;
		restTemplate.delete(url);
	}

	@Override
	public boolean existePorIdentificacion(String identificacion) {
	    String url = apiBaseUrl + "/api/cliente/existe/" + identificacion;
	    Boolean res = restTemplate.getForObject(url, Boolean.class);
	    return res != null && res;
	}


}
