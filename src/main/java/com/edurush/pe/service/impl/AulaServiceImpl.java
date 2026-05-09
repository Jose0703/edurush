package com.edurush.pe.service.impl;

import com.edurush.pe.model.Aula;
import com.edurush.pe.repository.AulaRepository;
import com.edurush.pe.service.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AulaServiceImpl implements AulaService {

    @Autowired
    private AulaRepository aulaRepository;

    @Override
    public List<Aula> listarTodas() {
        return aulaRepository.findAll();
    }

    @Override
    public List<Aula> listarTodasOperativas() {
        return aulaRepository.findByEstadoNot("inactivo");
    }

    @Override
    public Aula buscarPorId(Integer id) {
        return aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula no encontrada"));
    }

    @Override
    public Aula guardarWeb(Aula aula) {

        if (aula.getEstado() == null || aula.getEstado().isBlank()) {
            aula.setEstado("disponible");
        }

        return aulaRepository.save(aula);
    }

    @Override
    public void eliminarLogicoWeb(Integer id) {
        Aula aula = buscarPorId(id);
        aula.setEstado("inactivo");
        aulaRepository.save(aula);
    }
}