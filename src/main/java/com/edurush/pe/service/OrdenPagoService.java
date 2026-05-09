package com.edurush.pe.service;

import java.util.List;
import com.edurush.pe.model.OrdenPago;

public interface OrdenPagoService {
    List<OrdenPago> listarTodas();
    OrdenPago guardar(OrdenPago orden);
    OrdenPago buscarPorId(Integer id);
    void eliminar(Integer id);
}