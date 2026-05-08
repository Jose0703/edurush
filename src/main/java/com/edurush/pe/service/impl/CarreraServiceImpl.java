package com.edurush.pe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edurush.pe.model.Carrera;
import com.edurush.pe.repository.CarreraRepository;
import com.edurush.pe.service.CarreraService;

@Service
public class CarreraServiceImpl implements CarreraService {

    @Autowired
    private CarreraRepository repository;

    @Override
    public List<Carrera> listar() {
    	return repository.findByEstado("vigente");
    }

    @Override
    public void guardar(Carrera carrera) {
        repository.save(carrera);
    }

    @Override
    public Carrera buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
    	Carrera carrera = repository.findById(id).orElse(null);

        if (carrera != null) {

            carrera.setEstado("inactivo");

            repository.save(carrera);
        }
    }
}