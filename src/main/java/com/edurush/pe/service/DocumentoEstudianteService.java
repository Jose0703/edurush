package com.edurush.pe.service;

import java.util.List;

import com.edurush.pe.model.DocumentoEstudiante;

public interface DocumentoEstudianteService {
    List<DocumentoEstudiante> listarTodos();
    DocumentoEstudiante guardar(DocumentoEstudiante doc);
    DocumentoEstudiante buscarPorId(Integer id);
    void eliminar(Integer id);
    List<DocumentoEstudiante> buscarPorEstudiante(Integer idEstudiante);
}