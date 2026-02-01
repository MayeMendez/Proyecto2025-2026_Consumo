package com.uisrael.proyectoconsumo.servicio;

import java.util.List;

import com.uisrael.proyectoconsumo.modelo.ProductoModelo;

public interface ProductoServicio {

	List<ProductoModelo> listar();
	ProductoModelo buscarPorId(Integer id);
	void guardar(ProductoModelo producto);
	void actualizar(ProductoModelo producto);
	void eliminar(Integer id);

}
