package com.edurush.pe.service;

import java.util.List;
import com.edurush.pe.model.Periodo;

public interface PeriodoService {
    List<Periodo> listarVigentes();
    List<Periodo> listarAbiertos();
    Periodo guardar(Periodo periodo);
    Periodo buscarPorId(Integer id);
    void eliminarLogico(Integer id);
}