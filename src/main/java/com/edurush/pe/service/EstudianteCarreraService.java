package com.edurush.pe.service;

import java.util.List;
import com.edurush.pe.model.EstudianteCarrera;

public interface EstudianteCarreraService {
    List<EstudianteCarrera> listarTodos();
    EstudianteCarrera guardar(EstudianteCarrera ec);
    EstudianteCarrera buscarPorId(Integer id);
    void eliminar(Integer id);
}