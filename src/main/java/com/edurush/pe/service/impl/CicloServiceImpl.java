package com.edurush.pe.service.impl;

import com.edurush.pe.model.Ciclo;
import com.edurush.pe.model.PlanEstudio;
import com.edurush.pe.repository.CicloRepository;
import com.edurush.pe.repository.PlanEstudioRepository;
import com.edurush.pe.service.CicloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CicloServiceImpl implements CicloService {

    @Autowired
    private CicloRepository cicloRepository;

    @Autowired
    private PlanEstudioRepository planEstudioRepository;

    @Override
    public List<Ciclo> listarTodos() {
        return cicloRepository.findAll();
    }

    @Override
    public List<Ciclo> listarTodosActivos() {
        return cicloRepository.findByEstadoNot("inactivo");
    }

    @Override
    public Ciclo buscarPorId(Integer id) {
        return cicloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciclo no encontrado"));
    }

    @Override
    public Ciclo guardarWeb(Ciclo ciclo) {

        if (ciclo.getPlanEstudio() == null || ciclo.getPlanEstudio().getIdPlanEstudio() == null) {
            throw new RuntimeException("Debe seleccionar un plan de estudio");
        }

        if (ciclo.getNumeroCiclo() == null || ciclo.getNumeroCiclo() <= 0) {
            throw new RuntimeException("El número de ciclo debe ser mayor a 0");
        }

        Integer idPlan = ciclo.getPlanEstudio().getIdPlanEstudio();

        PlanEstudio planBD = planEstudioRepository.findById(idPlan)
                .orElseThrow(() -> new RuntimeException("Plan de estudio no encontrado"));

        if (ciclo.getIdCiclo() == null) {
            boolean existe = cicloRepository.existsByPlanEstudio_IdPlanEstudioAndNumeroCiclo(
                    idPlan,
                    ciclo.getNumeroCiclo()
            );

            if (existe) {
                throw new RuntimeException("Este plan de estudio ya tiene registrado ese número de ciclo");
            }
        } else {
            boolean existeOtro = cicloRepository.existsByPlanEstudio_IdPlanEstudioAndNumeroCicloAndIdCicloNot(
                    idPlan,
                    ciclo.getNumeroCiclo(),
                    ciclo.getIdCiclo()
            );

            if (existeOtro) {
                throw new RuntimeException("Otro ciclo ya usa ese número en el mismo plan de estudio");
            }
        }

        ciclo.setPlanEstudio(planBD);

        if (ciclo.getEstado() == null || ciclo.getEstado().isBlank()) {
            ciclo.setEstado("activo");
        }

        return cicloRepository.save(ciclo);
    }

    @Override
    public void eliminarLogicoWeb(Integer id) {
        Ciclo ciclo = buscarPorId(id);
        ciclo.setEstado("inactivo");
        cicloRepository.save(ciclo);
    }
}