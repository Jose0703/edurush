package com.edurush.pe.repository;

import com.edurush.pe.model.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SeccionRepository extends JpaRepository<Seccion, Integer> {
    List<Seccion> findByEstadoNot(String estado);
}