package com.uisrael.proyectoconsumo.modelo;

public class ProductoModelo {

	private int id_producto;
	private String nombre_producto;
	private String categoria;
	private double precio_unitario;
	private int stock_actual;
	private String unidad_medida;
	private boolean activo;
	
	
	public ProductoModelo(int id_producto, String nombre_producto, String categoria, double precio_unitario,
			int stock_actual, String unidad_medida, boolean activo) {
		super();
		this.id_producto = id_producto;
		this.nombre_producto = nombre_producto;
		this.categoria = categoria;
		this.precio_unitario = precio_unitario;
		this.stock_actual = stock_actual;
		this.unidad_medida = unidad_medida;
		this.activo = activo;
	}
	
	
	
	public ProductoModelo() {
	
	}



	public int getId_producto() {
		return id_producto;
	}
	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
	public String getNombre_producto() {
		return nombre_producto;
	}
	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public double getPrecio_unitario() {
		return precio_unitario;
	}
	public void setPrecio_unitario(double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}
	public int getStock_actual() {
		return stock_actual;
	}
	public void setStock_actual(int stock_actual) {
		this.stock_actual = stock_actual;
	}
	public String getUnidad_medida() {
		return unidad_medida;
	}
	public void setUnidad_medida(String unidad_medida) {
		this.unidad_medida = unidad_medida;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
	
}
