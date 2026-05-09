package com.edurush.pe.repository;

import com.edurush.pe.model.Ciclo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CicloRepository extends JpaRepository<Ciclo, Integer> {

    List<Ciclo> findByEstado(String estado);

    List<Ciclo> findByEstadoNot(String estado);

    List<Ciclo> findByPlanEstudio_IdPlanEstudio(Integer idPlanEstudio);

    boolean existsByPlanEstudio_IdPlanEstudioAndNumeroCiclo(Integer idPlanEstudio, Integer numeroCiclo);

    boolean existsByPlanEstudio_IdPlanEstudioAndNumeroCicloAndIdCicloNot(
            Integer idPlanEstudio,
            Integer numeroCiclo,
            Integer idCiclo
    );
}