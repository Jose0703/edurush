package com.edurush.pe.service;

import java.util.List;
import com.edurush.pe.model.Pago;

public interface PagoService {
    List<Pago> listarTodos();
    Pago guardar(Pago pago);
    Pago buscarPorId(Integer id);
    void eliminar(Integer id);
}