package com.edurush.pe.service;

import com.edurush.pe.model.Ciclo;

import java.util.List;

public interface CicloService {

    List<Ciclo> listarTodos();

    List<Ciclo> listarTodosActivos();

    Ciclo buscarPorId(Integer id);

    Ciclo guardarWeb(Ciclo ciclo);

    void eliminarLogicoWeb(Integer id);
}