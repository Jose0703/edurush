package com.edurush.pe.service;

import com.edurush.pe.model.PlanEstudio;

import java.util.List;

public interface PlanEstudioService {

    List<PlanEstudio> listarTodos();

    List<PlanEstudio> listarTodosActivos();

    PlanEstudio buscarPorId(Integer id);

    PlanEstudio guardarWeb(PlanEstudio planEstudio);

    void eliminarLogicoWeb(Integer id);
}