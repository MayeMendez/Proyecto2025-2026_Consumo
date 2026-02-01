package com.uisrael.proyectoconsumo.modelo;

public class DetallePedidoModelo {

	private Integer id_detalle_pedido;
	private Integer id_pedido;
	private Integer id_producto;
	private Integer cantidad;
	private double precio_unitario;
	private double subtotal;
	
	
	public DetallePedidoModelo(Integer id_detalle_pedido, Integer id_pedido, Integer id_producto, Integer cantidad,
			double precio_unitario, double subtotal) {
		super();
		this.id_detalle_pedido = id_detalle_pedido;
		this.id_pedido = id_pedido;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
		this.precio_unitario = precio_unitario;
		this.subtotal = subtotal;
	}
	
	
	
	public DetallePedidoModelo() {
		
	}



	public Integer getId_detalle_pedido() {
		return id_detalle_pedido;
	}
	public void setId_detalle_pedido(Integer id_detalle_pedido) {
		this.id_detalle_pedido = id_detalle_pedido;
	}
	public Integer getId_pedido() {
		return id_pedido;
	}
	public void setId_pedido(Integer id_pedido) {
		this.id_pedido = id_pedido;
	}
	public Integer getId_producto() {
		return id_producto;
	}
	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio_unitario() {
		return precio_unitario;
	}
	public void setPrecio_unitario(double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	
	
}
