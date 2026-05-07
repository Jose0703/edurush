package com.edurush.pe.service;

import com.edurush.pe.model.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> listarTodosActivos();
    Usuario guardar(Usuario usuario);
    Usuario buscarPorId(Integer id);
    void eliminar(Integer id);
}