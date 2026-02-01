package com.uisrael.proyectoconsumo.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PedidoModelo {

	@JsonProperty("id_pedido")
	private Integer id_pedido;

	@JsonProperty("id_cliente")
	private Integer id_cliente;

	@JsonProperty("id_ruta_entrega")
	private Integer id_ruta_entrega;

	@JsonProperty("id_repartidor")
	private Integer id_repartidor;

	@JsonProperty("fecha_pedido")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime fecha_pedido;

	@JsonProperty("fecha_entrega_programada")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha_entrega_programada;

	@JsonProperty("estado")
	private String estado;

	@JsonProperty("tipo_venta")
	private String tipo_venta;

	@JsonProperty("total_pedido")
	private Double total_pedido;


	@JsonProperty("id_detalle_pedido")
	private Integer id_detalle_pedido;

	public PedidoModelo() {
	}

	public Integer getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Integer id_pedido) {
		this.id_pedido = id_pedido;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Integer getId_ruta_entrega() {
		return id_ruta_entrega;
	}

	public void setId_ruta_entrega(Integer id_ruta_entrega) {
		this.id_ruta_entrega = id_ruta_entrega;
	}

	public Integer getId_repartidor() {
		return id_repartidor;
	}

	public void setId_repartidor(Integer id_repartidor) {
		this.id_repartidor = id_repartidor;
	}

	public LocalDateTime getFecha_pedido() {
		return fecha_pedido;
	}

	public void setFecha_pedido(LocalDateTime fecha_pedido) {
		this.fecha_pedido = fecha_pedido;
	}

	public LocalDate getFecha_entrega_programada() {
		return fecha_entrega_programada;
	}

	public void setFecha_entrega_programada(LocalDate fecha_entrega_programada) {
		this.fecha_entrega_programada = fecha_entrega_programada;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipo_venta() {
		return tipo_venta;
	}

	public void setTipo_venta(String tipo_venta) {
		this.tipo_venta = tipo_venta;
	}

	public Double getTotal_pedido() {
		return total_pedido;
	}

	public void setTotal_pedido(Double total_pedido) {
		this.total_pedido = total_pedido;
	}

	public Integer getId_detalle_pedido() {
		return id_detalle_pedido;
	}

	public void setId_detalle_pedido(Integer id_detalle_pedido) {
		this.id_detalle_pedido = id_detalle_pedido;
	}
}
