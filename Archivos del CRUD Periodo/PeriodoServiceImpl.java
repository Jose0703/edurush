package com.edurush.pe.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edurush.pe.model.Periodo;
import com.edurush.pe.repository.PeriodoRepository;
import com.edurush.pe.service.PeriodoService;

@Service
public class PeriodoServiceImpl implements PeriodoService {

    @Autowired
    private PeriodoRepository periodoRepo;

    @Override
    public List<Periodo> listarVigentes() {
        return periodoRepo.findByEstadoNot("inactivo");
    }

    @Override
    public List<Periodo> listarAbiertos() {
        return periodoRepo.findByEstado("abierto");
    }

    @Override
    public Periodo guardar(Periodo periodo) {
        return periodoRepo.save(periodo);
    }

    @Override
    public Periodo buscarPorId(Integer id) {
        return periodoRepo.findById(id).orElse(null);
    }

    @Override
    public void eliminarLogico(Integer id) {
        Periodo p = periodoRepo.findById(id).orElse(null);
        if (p != null) {
            p.setEstado("inactivo");
            periodoRepo.save(p);
        }
    }
}