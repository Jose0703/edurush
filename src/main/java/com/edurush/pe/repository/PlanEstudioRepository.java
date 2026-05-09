package com.edurush.pe.repository;

import com.edurush.pe.model.PlanEstudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanEstudioRepository extends JpaRepository<PlanEstudio, Integer> {

    List<PlanEstudio> findByEstadoNot(String estado);

    List<PlanEstudio> findByEstado(String estado);

    List<PlanEstudio> findByCarrera_IdCarrera(Integer idCarrera);
}