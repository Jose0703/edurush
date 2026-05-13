package com.edurush.pe.service;

import java.util.List;

import com.edurush.pe.model.Seccion;

public interface SeccionService {
    List<Seccion> listarVigentes();
    Seccion guardar(Seccion seccion);
    Seccion buscarPorId(Integer id);
    void eliminarLogico(Integer id);
}