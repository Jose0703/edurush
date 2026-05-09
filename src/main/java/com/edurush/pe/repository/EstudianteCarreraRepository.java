package com.edurush.pe.repository;

import com.edurush.pe.model.EstudianteCarrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, Integer> {
    
	List<EstudianteCarrera> findByEstudiante_IdEstudiante(Integer idEstudiante);
}