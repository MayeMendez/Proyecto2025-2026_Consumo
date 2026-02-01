package com.uisrael.proyectoconsumo.modelo;

public class RepartidorModelo {

	private int id_repartidor;
	private String nombres;
	private String apellidos;
	private String ci;
	private String telefono;
	private String email;
	private String estado;
	
	public RepartidorModelo(int id_repartidor, String nombres, String apellidos, String ci, String telefono,
			String email, String estado) {
		super();
		this.id_repartidor = id_repartidor;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.ci = ci;
		this.telefono = telefono;
		this.email = email;
		this.estado = estado;
	}

	
	
	public RepartidorModelo() {
		
	}



	public int getId_repartidor() {
		return id_repartidor;
	}

	public void setId_repartidor(int id_repartidor) {
		this.id_repartidor = id_repartidor;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	

	
}
