package com.edurush.pe.service.impl;

import com.edurush.pe.model.Docente;
import com.edurush.pe.model.Usuario;
import com.edurush.pe.repository.DocenteRepository;
import com.edurush.pe.repository.UsuarioRepository;
import com.edurush.pe.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteServiceImpl implements DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Docente> listarTodosActivos() {
        return docenteRepository.findByEstado("activo");
    }

    @Override
    public List<Docente> listarTodos() {
        return docenteRepository.findAll();
    }

    @Override
    public Docente buscarPorId(Integer id) {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
    }

    @Override
    public Docente guardarWeb(Docente docente) {

        if (docente.getUsuario() == null || docente.getUsuario().getIdUsuario() == null) {
            throw new RuntimeException("Debe seleccionar un usuario");
        }

        Usuario usuarioBD = usuarioRepository.findById(docente.getUsuario().getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        docente.setUsuario(usuarioBD);

        if (docente.getEstado() == null || docente.getEstado().isBlank()) {
            docente.setEstado("activo");
        }

        return docenteRepository.save(docente);
    }

    @Override
    public void eliminarLogicoWeb(Integer id) {
        Docente docente = buscarPorId(id);
        docente.setEstado("inactivo");
        docenteRepository.save(docente);
    }
}