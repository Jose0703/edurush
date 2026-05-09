package com.edurush.pe.service;

import com.edurush.pe.model.Docente;
import java.util.List;

public interface DocenteService {

    List<Docente> listarTodosActivos();

    List<Docente> listarTodos();

    Docente buscarPorId(Integer id);

    Docente guardarWeb(Docente docente);

    void eliminarLogicoWeb(Integer id);
}