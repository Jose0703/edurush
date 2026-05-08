package com.edurush.pe.service;

import java.util.List;

import com.edurush.pe.model.Curso;

public interface CursoService {

    List<Curso> listar();

    void guardar(Curso curso);

    Curso buscarPorId(Integer id);

    void eliminar(Integer id);

}