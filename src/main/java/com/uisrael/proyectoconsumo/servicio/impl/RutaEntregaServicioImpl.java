package com.uisrael.proyectoconsumo.servicio.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.uisrael.proyectoconsumo.modelo.RutaEntregaModelo;
import com.uisrael.proyectoconsumo.servicio.RutaEntregaServicio;

@Service
public class RutaEntregaServicioImpl implements RutaEntregaServicio {

    private final RestTemplate restTemplate;

    @Value("${app.api.base-url}")
    private String baseUrl;

    public RutaEntregaServicioImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<RutaEntregaModelo> listar() {
        String url = baseUrl + "/api/rutaEntrega";
        RutaEntregaModelo[] data = restTemplate.getForObject(url, RutaEntregaModelo[].class);
        return data == null ? List.of() : Arrays.asList(data);
    }

    @Override
    public RutaEntregaModelo buscarPorId(Integer id) {
        String url = baseUrl + "/api/rutaEntrega/" + id;
        return restTemplate.getForObject(url, RutaEntregaModelo.class);
    }

    @Override
    public void guardar(RutaEntregaModelo ruta) {
        String url = baseUrl + "/api/rutaEntrega";
        restTemplate.postForObject(url, ruta, RutaEntregaModelo.class);
    }

    @Override
    public void actualizar(RutaEntregaModelo ruta) {
        Integer id = ruta.getId_ruta_entrega();
        String url = baseUrl + "/api/rutaEntrega/" + id;
        restTemplate.put(url, ruta);
    }

    @Override
    public void eliminar(Integer id) {
        String url = baseUrl + "/api/rutaEntrega/" + id;

        try {
            ResponseEntity<Void> resp = restTemplate.exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
            System.out.println("DELETE RUTA -> " + url + " status=" + resp.getStatusCode());
        } catch (HttpStatusCodeException e) {
            System.out.println("DELETE RUTA ERROR -> " + url + " status=" + e.getStatusCode()
                    + " body=" + e.getResponseBodyAsString());
            throw e;
        }
    }

    @Override
    public List<RutaEntregaModelo> listarPorZona(Integer idZona) {
        String url = baseUrl + "/api/rutaEntrega/zona/" + idZona;
        RutaEntregaModelo[] data = restTemplate.getForObject(url, RutaEntregaModelo[].class);
        return data == null ? List.of() : Arrays.asList(data);
    }
}
