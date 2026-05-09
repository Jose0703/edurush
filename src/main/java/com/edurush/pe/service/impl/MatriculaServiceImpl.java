package com.edurush.pe.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edurush.pe.model.Matricula;
import com.edurush.pe.repository.MatriculaRepository;
import com.edurush.pe.service.MatriculaService;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepo;

    @Override
    public List<Matricula> listarVigentes() {
        return matriculaRepo.findByEstadoNot("inactivo");
    }

    @Override
    public List<Matricula> listarTodas() {
        return matriculaRepo.findAll();
    }

    @Override
    public Matricula guardar(Matricula matricula) {
        return matriculaRepo.save(matricula);
    }

    @Override
    public Matricula buscarPorId(Integer id) {
        return matriculaRepo.findById(id).orElse(null);
    }

    @Override
    public void eliminarLogico(Integer id) {
        Matricula m = matriculaRepo.findById(id).orElse(null);
        if (m != null) {
            m.setEstado("inactivo");
            matriculaRepo.save(m);
        }
    }
}