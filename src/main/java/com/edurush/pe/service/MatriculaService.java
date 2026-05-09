package com.edurush.pe.service;

import java.util.List;
import com.edurush.pe.model.Matricula;

public interface MatriculaService {
    List<Matricula> listarVigentes();
    List<Matricula> listarTodas();
    Matricula guardar(Matricula matricula);
    Matricula buscarPorId(Integer id);
    void eliminarLogico(Integer id);
}
