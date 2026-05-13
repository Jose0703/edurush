package com.edurush.pe.repository;

import com.edurush.pe.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);
    List<Usuario> findByEstado(String estado);
    
    @Query("SELECT u FROM Usuario u WHERE u.rol.nombre = 'ESTUDIANTE' " +
            "AND u.idUsuario NOT IN (SELECT e.usuario.idUsuario FROM Estudiante e)")
     List<Usuario> findEstudiantesSinAsignar();
}