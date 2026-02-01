package com.uisrael.proyectoconsumo.servicio;

import java.util.List;

import com.uisrael.proyectoconsumo.modelo.RepartidorModelo;

public interface RepartidorServicio {

	List<RepartidorModelo> listar();
	RepartidorModelo buscarPorId(Integer id);
	void guardar(RepartidorModelo repartidor);
	void actualizar(RepartidorModelo repartidor);
	void eliminar(Integer id);

}
