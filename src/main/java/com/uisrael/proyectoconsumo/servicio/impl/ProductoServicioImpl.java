package com.uisrael.proyectoconsumo.servicio.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.uisrael.proyectoconsumo.modelo.ProductoModelo;
import com.uisrael.proyectoconsumo.servicio.ProductoServicio;

@Service
public class ProductoServicioImpl implements ProductoServicio {

	private final RestTemplate restTemplate;

	@Value("${app.api.base-url}")
	private String baseUrl;

	public ProductoServicioImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<ProductoModelo> listar() {
		String url = baseUrl + "/api/producto";
		ProductoModelo[] data = restTemplate.getForObject(url, ProductoModelo[].class);
		return data == null ? List.of() : Arrays.asList(data);
	}

	@Override
	public ProductoModelo buscarPorId(Integer id) {
		String url = baseUrl + "/api/producto/" + id;
		return restTemplate.getForObject(url, ProductoModelo.class);
	}

	@Override
	public void guardar(ProductoModelo producto) {

	    
	    if (producto.getPrecio_unitario() <= 0) {
	        throw new RuntimeException("El precio debe ser mayor a 0");
	    }

	    if (producto.getStock_actual() <= 0) {
	        throw new RuntimeException("El stock debe ser mayor a 0");
	    }


	    String url = baseUrl + "/api/producto";
	    restTemplate.postForObject(url, producto, ProductoModelo.class);
	}


	@Override
	public void actualizar(ProductoModelo producto) {
		Integer id = producto.getId_producto();
		String url = baseUrl + "/api/producto/" + id;
		restTemplate.put(url, producto);
	}

	public void eliminar(Integer id) {
		try {
			restTemplate.delete(baseUrl + "/api/producto/" + id);
		} catch (HttpClientErrorException.Conflict e) {
			throw new RuntimeException("No se puede eliminar el producto porque está asociado a pedidos.");
		}
	}

}
