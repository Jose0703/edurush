package com.edurush.pe.repository;

import com.edurush.pe.model.DocumentoEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DocumentoEstudianteRepository extends JpaRepository<DocumentoEstudiante, Integer> {
    
    List<DocumentoEstudiante> findByEstudiante_IdEstudiante(Integer idEstudiante);
}