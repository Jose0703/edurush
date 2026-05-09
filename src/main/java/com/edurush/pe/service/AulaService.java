package com.edurush.pe.service;

import com.edurush.pe.model.Aula;

import java.util.List;

public interface AulaService {

    List<Aula> listarTodas();

    List<Aula> listarTodasOperativas();

    Aula buscarPorId(Integer id);

    Aula guardarWeb(Aula aula);

    void eliminarLogicoWeb(Integer id);
}