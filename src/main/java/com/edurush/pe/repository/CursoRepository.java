package com.edurush.pe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edurush.pe.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer> {

    List<Curso> findByEstado(String estado);

}