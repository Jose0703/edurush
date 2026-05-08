package com.edurush.pe.repository;

import com.edurush.pe.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    @Query("SELECT e FROM Estudiante e " +
            "WHERE LOWER(CONCAT(e.nombres, ' ', e.apellidos)) LIKE LOWER(CONCAT('%', :texto, '%')) " +
            "OR LOWER(e.nombres) LIKE LOWER(CONCAT('%', :texto, '%')) " +
            "OR LOWER(e.apellidos) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Estudiante> buscarPorNombreApellido(@Param("texto") String texto);

    List<Estudiante> findByEstado(String estado);
}
