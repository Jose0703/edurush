package com.edurush.pe.service.impl;

import com.edurush.pe.dto.MatriculaPostulanteDTO;
import com.edurush.pe.model.*;
import com.edurush.pe.repository.*;
import com.edurush.pe.service.PublicMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PublicMatriculaServiceImpl implements PublicMatriculaService {

    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private RolRepository rolRepo;
    @Autowired private EstudianteRepository estudianteRepo;
    @Autowired private BCryptPasswordEncoder encoder;
    @Autowired private CarreraRepository carreraRepo;
    @Autowired private EstudianteCarreraRepository ecRepo;

    @Override
    public boolean existeDni(String dni) {
        return estudianteRepo.existsByDni(dni);
    }

    @Override
    @Transactional
    public MatriculaPostulanteDTO registrarMatriculaCompleta(MatriculaPostulanteDTO dto) {
        
        // 1. GENERAR CORREO INSTITUCIONAL (RF12)
        // e + 8 cifras aleatorias + @edurush.pe
        String num = String.valueOf((int)(Math.random() * 90000000 + 10000000));
        String correoInst = "e" + num + "@edurush.pe";
        
        // 2. CREAR USUARIO (RF12 y RF42)
        Rol rolEst = rolRepo.findByNombre("ESTUDIANTE")
                .orElseThrow(() -> new RuntimeException("Error: El rol ESTUDIANTE no existe en la base de datos."));        
        Usuario user = new Usuario();
        user.setCorreo(correoInst);
        user.setPassword(encoder.encode(dto.getDni())); // Clave inicial = DNI
        user.setRol(rolEst);
        user.setEstado("activo");
        user = usuarioRepo.save(user);

        // 3. CREAR ESTUDIANTE (RF07)
        Estudiante est = new Estudiante();
        est.setUsuario(user);
        est.setNombres(dto.getNombres());
        est.setApellidos(dto.getApellidos());
        est.setDni(dto.getDni());
        est.setCelular(dto.getCelular());
        est.setEmail(dto.getEmailPersonal());
        est.setFechaNacimiento(dto.getFechaNacimiento());
        est.setEstado("activo");
        estudianteRepo.save(est);

        // 4. VINCULAR ESTUDIANTE CON CARRERA
        Carrera car = carreraRepo.findById(dto.getIdCarrera()).orElse(null);
        EstudianteCarrera ec = new EstudianteCarrera();
        ec.setEstudiante(est);
        ec.setCarrera(car);
        ec.setEstado("activo");
        ecRepo.save(ec);

        // Devolvemos los datos para mostrar en el Paso 4
        dto.setCorreoInstitucional(correoInst);
        dto.setPasswordTemporal(dto.getDni());
        
        return dto;
    }
}