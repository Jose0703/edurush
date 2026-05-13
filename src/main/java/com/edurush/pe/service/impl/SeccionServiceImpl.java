package com.edurush.pe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edurush.pe.model.Seccion;
import com.edurush.pe.repository.SeccionRepository;
import com.edurush.pe.service.SeccionService;

@Service
public class SeccionServiceImpl implements SeccionService {
    @Autowired
    private SeccionRepository seccionRepo;

    @Override
    public List<Seccion> listarVigentes() {
    	return seccionRepo.findByEstadoNot("inactivo");
    }
    @Override
    public Seccion guardar(Seccion s) {
    	return seccionRepo.save(s);
    }
    @Override
    public Seccion buscarPorId(Integer id) {
    	return seccionRepo.findById(id).orElse(null);
    }
    
    @Override
    public void eliminarLogico(Integer id) {
        Seccion s = seccionRepo.findById(id).orElse(null);
        if (s != null) {
            s.setEstado("inactivo");
            seccionRepo.save(s);
        }
    }
}
