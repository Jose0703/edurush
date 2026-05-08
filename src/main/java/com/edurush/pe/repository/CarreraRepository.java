package com.edurush.pe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edurush.pe.model.Carrera;

public interface CarreraRepository extends JpaRepository<Carrera, Integer> {
	List<Carrera> findByEstado(String estado);
}