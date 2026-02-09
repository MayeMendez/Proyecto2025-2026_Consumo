package com.uisrael.proyectoconsumo.modelo;

import java.util.ArrayList;
import java.util.List;

public class ZonaConRutasForm {

	private ZonaEntregaModelo zona = new ZonaEntregaModelo();
	private List<RutaEntregaModelo> rutas = new ArrayList<RutaEntregaModelo>();

	public ZonaEntregaModelo getZona() {
		return zona;
	}

	public void setZona(ZonaEntregaModelo zona) {
		this.zona = zona;
	}

	public List<RutaEntregaModelo> getRutas() {
		return rutas;
	}

	public void setRutas(List<RutaEntregaModelo> rutas) {
		this.rutas = rutas;
	}
}
