package com.edurush.pe.repository;

import com.edurush.pe.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer> {

    List<Aula> findByEstadoNot(String estado);

    List<Aula> findByEstado(String estado);

    boolean existsByCodigo(String codigo);
}