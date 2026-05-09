package com.edurush.pe.repository;

import com.edurush.pe.model.OrdenPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdenPagoRepository extends JpaRepository<OrdenPago, Integer> {
    List<OrdenPago> findByEstudiante_IdEstudiante(Integer idEstudiante);
}