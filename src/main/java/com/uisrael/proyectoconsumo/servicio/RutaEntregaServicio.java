package com.uisrael.proyectoconsumo.servicio;

import java.util.List;

import com.uisrael.proyectoconsumo.modelo.RutaEntregaModelo;

public interface RutaEntregaServicio {

    List<RutaEntregaModelo> listar();
    RutaEntregaModelo buscarPorId(Integer id);
    void guardar(RutaEntregaModelo ruta);
    void actualizar(RutaEntregaModelo ruta);
    void eliminar(Integer id);
    List<RutaEntregaModelo> listarPorZona(Integer idZona);
}
