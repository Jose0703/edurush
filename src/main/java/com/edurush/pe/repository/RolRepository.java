package com.edurush.pe.repository;

import com.edurush.pe.model.Rol;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
	Optional<Rol> findByNombre(String nombre);
}