package com.edurush.pe.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.edurush.pe.model.Pago;
import com.edurush.pe.model.OrdenPago;
import com.edurush.pe.repository.PagoRepository;
import com.edurush.pe.repository.OrdenPagoRepository;
import com.edurush.pe.service.PagoService;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired private PagoRepository pagoRepo;
    @Autowired private OrdenPagoRepository ordenRepo;

    @Override
    public List<Pago> listarTodos() {
    	return pagoRepo.findAll();
    }

    @Override
    @Transactional
    public Pago guardar(Pago pago) {
        Pago nuevoPago = pagoRepo.save(pago);
        

        if (pago.getEstado().equals("confirmado")) {
            OrdenPago orden = pago.getOrdenPago();
            orden.setEstado("pagado");
            ordenRepo.save(orden);
        }
        return nuevoPago;
    }

    @Override
    public Pago buscarPorId(Integer id) {
    	return pagoRepo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
    	pagoRepo.deleteById(id);
    }
}