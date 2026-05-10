package com.edurush.pe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edurush.pe.model.Periodo;

@Repository
public interface PeriodoRepository extends JpaRepository<Periodo, Integer> {
	//Realizado por Mechato
    List<Periodo> findByEstadoNot(String estado);
    
    List<Periodo> findByEstado(String estado);
}