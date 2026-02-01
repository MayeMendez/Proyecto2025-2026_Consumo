package com.uisrael.proyectoconsumo.modelo;

public class ClienteModelo {

	private int id_cliente;
	private String tipo_cliente;
	private String razon_social;
	private String nombre_comercial;
	private String identificacion;
	private String telefono;
	private String email;
	private String direccion;
	
	
	
	public ClienteModelo() {
		
	}

	public ClienteModelo(int id_cliente, String tipo_cliente, String razon_social, String nombre_comercial,
			String identificacion, String telefono, String email, String direccion) {
		super();
		this.id_cliente = id_cliente;
		this.tipo_cliente = tipo_cliente;
		this.razon_social = razon_social;
		this.nombre_comercial = nombre_comercial;
		this.identificacion = identificacion;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
	}
	
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getTipo_cliente() {
		return tipo_cliente;
	}
	public void setTipo_cliente(String tipo_cliente) {
		this.tipo_cliente = tipo_cliente;
	}
	public String getRazon_social() {
		return razon_social;
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	public String getNombre_comercial() {
		return nombre_comercial;
	}
	public void setNombre_comercial(String nombre_comercial) {
		this.nombre_comercial = nombre_comercial;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	
}
