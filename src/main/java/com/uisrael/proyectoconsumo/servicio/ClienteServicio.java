package com.uisrael.proyectoconsumo.servicio;

import java.util.List;

import com.uisrael.proyectoconsumo.modelo.ClienteModelo;

public interface ClienteServicio {

	List<ClienteModelo> listar();
    ClienteModelo buscarPorId(Integer id);
    ClienteModelo guardar(ClienteModelo cliente);
    void actualizar(ClienteModelo cliente);
    void eliminar(Integer id);
	
}
