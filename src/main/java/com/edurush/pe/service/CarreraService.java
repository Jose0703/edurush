package com.edurush.pe.service;

import java.util.List;

import com.edurush.pe.model.Carrera;

public interface CarreraService {

    List<Carrera> listar();

    void guardar(Carrera carrera);

    Carrera buscarPorId(Integer id);

    void eliminar(Integer id);
}