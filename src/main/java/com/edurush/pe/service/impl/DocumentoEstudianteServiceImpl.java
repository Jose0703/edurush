package com.edurush.pe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edurush.pe.model.DocumentoEstudiante;
import com.edurush.pe.repository.DocumentoEstudianteRepository;
import com.edurush.pe.service.DocumentoEstudianteService;

@Service
public class DocumentoEstudianteServiceImpl implements DocumentoEstudianteService {

    @Autowired
    private DocumentoEstudianteRepository docRepo;

    @Override
    public List<DocumentoEstudiante> listarTodos() {
        return docRepo.findAll();
    }

    @Override
    public DocumentoEstudiante guardar(DocumentoEstudiante doc) {
        return docRepo.save(doc);
    }

    @Override
    public DocumentoEstudiante buscarPorId(Integer id) {
        return docRepo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        docRepo.deleteById(id);
    }

    @Override
    public List<DocumentoEstudiante> buscarPorEstudiante(Integer idEstudiante) {
        return docRepo.findByEstudiante_IdEstudiante(idEstudiante);
    }
}