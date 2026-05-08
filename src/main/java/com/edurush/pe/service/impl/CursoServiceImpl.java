package com.edurush.pe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edurush.pe.model.Curso;
import com.edurush.pe.repository.CursoRepository;
import com.edurush.pe.service.CursoService;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository repository;

    @Override
    public List<Curso> listar() {
        return repository.findByEstado("activo");
    }

    @Override
    public void guardar(Curso curso) {
        repository.save(curso);
    }

    @Override
    public Curso buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {

        Curso curso = repository.findById(id).orElse(null);

        if (curso != null) {

            curso.setEstado("inactivo");

            repository.save(curso);
        }
    }
}