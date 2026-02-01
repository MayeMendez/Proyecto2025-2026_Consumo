package com.uisrael.proyectoconsumo.servicio;

import java.util.List;

import com.uisrael.proyectoconsumo.modelo.PedidoModelo;

public interface PedidoServicio {

	List<PedidoModelo> listar();
	PedidoModelo buscarPorId(Integer id);
	void guardar(PedidoModelo pedido);
	void actualizar(PedidoModelo pedido);
	void eliminar(Integer id);

}
