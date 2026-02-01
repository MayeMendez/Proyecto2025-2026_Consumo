package com.uisrael.proyectoconsumo.modelo;

public class ZonaEntregaModelo {

	private Integer id_zona_entrega;
	private String nombre_zona;
	private String ciudad;
	private String provincia;
	
	public ZonaEntregaModelo(Integer id_zona_entrega, String nombre_zona, String ciudad, String provincia) {
		super();
		this.id_zona_entrega = id_zona_entrega;
		this.nombre_zona = nombre_zona;
		this.ciudad = ciudad;
		this.provincia = provincia;
	}

	public ZonaEntregaModelo() {
		
	}


	public Integer getId_zona_entrega() {
		return id_zona_entrega;
	}

	public void setId_zona_entrega(Integer id_zona_entrega) {
		this.id_zona_entrega = id_zona_entrega;
	}

	public String getNombre_zona() {
		return nombre_zona;
	}

	public void setNombre_zona(String nombre_zona) {
		this.nombre_zona = nombre_zona;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	
}
