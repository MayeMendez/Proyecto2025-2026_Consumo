package com.uisrael.proyectoconsumo.servicio;

import java.util.List;

import com.uisrael.proyectoconsumo.modelo.ZonaEntregaModelo;

public interface ZonaEntregaServicio {

	List<ZonaEntregaModelo> listar();
	ZonaEntregaModelo buscarPorId(Integer id);
	void guardar(ZonaEntregaModelo zona);
	void actualizar(ZonaEntregaModelo zona);
	void eliminar(Integer id);
}
