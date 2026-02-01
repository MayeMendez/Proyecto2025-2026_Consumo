package com.uisrael.proyectoconsumo.servicio;

import java.util.List;

import com.uisrael.proyectoconsumo.modelo.DetallePedidoModelo;

public interface DetallePedidoServicio {

	List<DetallePedidoModelo> listar(); 
	DetallePedidoModelo buscarPorId(Integer id); 
	List<DetallePedidoModelo> listarPorPedido(Integer idPedido);
	void guardar(DetallePedidoModelo detalle);
	void actualizar(DetallePedidoModelo detalle);
	void eliminar(Integer id);
}
