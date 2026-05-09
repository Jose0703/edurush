package com.edurush.pe.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edurush.pe.model.OrdenPago;
import com.edurush.pe.repository.OrdenPagoRepository;
import com.edurush.pe.service.OrdenPagoService;

@Service
public class OrdenPagoServiceImpl implements OrdenPagoService {
    @Autowired private OrdenPagoRepository ordenRepo;

    @Override
    public List<OrdenPago> listarTodas() {
    	return ordenRepo.findAll();
    }
    @Override
    public OrdenPago guardar(OrdenPago o) {
    	return ordenRepo.save(o);
    }
    @Override
    public OrdenPago buscarPorId(Integer id) {
    	return ordenRepo.findById(id).orElse(null);
    }
    @Override
    public void eliminar(Integer id) {
    	ordenRepo.deleteById(id);
    }
}