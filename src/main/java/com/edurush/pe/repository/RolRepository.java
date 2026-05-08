package com.edurush.pe.repository;

import com.edurush.pe.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    List<Rol> findByEstado(String estado);
}
