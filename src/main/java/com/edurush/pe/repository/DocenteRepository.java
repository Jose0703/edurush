package com.edurush.pe.repository;

import com.edurush.pe.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Integer> {

    List<Docente> findByEstado(String estado);

    boolean existsByDni(String dni);

    boolean existsByEmail(String email);

    boolean existsByUsuario_IdUsuario(Integer idUsuario);
}