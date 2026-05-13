package com.edurush.pe.service.impl;

import com.edurush.pe.model.Usuario;
import com.edurush.pe.repository.UsuarioRepository;
import com.edurush.pe.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> listarTodosActivos() {
    	return usuarioRepo.findByEstado("activo");
	}

    @Override
    public Usuario guardar(Usuario usuario) {
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            String passwordEncriptado = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(passwordEncriptado);
        }
        
        return usuarioRepo.save(usuario);
    }
    @Override
    public Usuario buscarPorId(Integer id) {
        return usuarioRepo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        Usuario u = usuarioRepo.findById(id).orElse(null);
        if (u != null) {
            u.setEstado("inactivo");
            usuarioRepo.save(u);
        }
    }
    
    @Override
    public List<Usuario> listarUsuariosEstudiantesSinAsignar() {
        return usuarioRepo.findEstudiantesSinAsignar();
    }
}