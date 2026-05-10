package com.edurush.pe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edurush.pe.model.EstudianteCarrera;
import com.edurush.pe.repository.EstudianteCarreraRepository;
import com.edurush.pe.service.EstudianteCarreraService;

@Service
public class EstudianteCarreraServiceImpl implements EstudianteCarreraService {

    @Autowired
    private EstudianteCarreraRepository ecRepo;

    @Override
    public List<EstudianteCarrera> listarTodos() {
        return ecRepo.findAll();
    }

    @Override
    public EstudianteCarrera guardar(EstudianteCarrera ec) {
        return ecRepo.save(ec);
    }

    @Override
    public EstudianteCarrera buscarPorId(Integer id) {
        return ecRepo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        ecRepo.deleteById(id);
    }
}