package com.edurush.pe.service.impl;

import com.edurush.pe.model.Carrera;
import com.edurush.pe.model.PlanEstudio;
import com.edurush.pe.repository.CarreraRepository;
import com.edurush.pe.repository.PlanEstudioRepository;
import com.edurush.pe.service.PlanEstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanEstudioServiceImpl implements PlanEstudioService {

    @Autowired
    private PlanEstudioRepository planEstudioRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @Override
    public List<PlanEstudio> listarTodos() {
        return planEstudioRepository.findAll();
    }

    @Override
    public List<PlanEstudio> listarTodosActivos() {
        return planEstudioRepository.findByEstadoNot("inactivo");
    }

    @Override
    public PlanEstudio buscarPorId(Integer id) {
        return planEstudioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan de estudio no encontrado"));
    }

    @Override
    public PlanEstudio guardarWeb(PlanEstudio planEstudio) {

        if (planEstudio.getCarrera() == null || planEstudio.getCarrera().getIdCarrera() == null) {
            throw new RuntimeException("Debe seleccionar una carrera");
        }

        Carrera carreraBD = carreraRepository.findById(planEstudio.getCarrera().getIdCarrera())
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        planEstudio.setCarrera(carreraBD);

        if (planEstudio.getEstado() == null || planEstudio.getEstado().isBlank()) {
            planEstudio.setEstado("vigente");
        }

        return planEstudioRepository.save(planEstudio);
    }

    @Override
    public void eliminarLogicoWeb(Integer id) {
        PlanEstudio planEstudio = buscarPorId(id);
        planEstudio.setEstado("inactivo");
        planEstudioRepository.save(planEstudio);
    }
}