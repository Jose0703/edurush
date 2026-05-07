package com.edurush.pe.security;

import com.edurush.pe.model.Rol;
import com.edurush.pe.model.Usuario;
import com.edurush.pe.repository.RolRepository;
import com.edurush.pe.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private RolRepository rolRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        
        Rol rolAdmin = rolRepo.findByNombre("ADMINISTRADOR")
                .orElseGet(() -> {
                    Rol nuevoRol = new Rol();
                    nuevoRol.setNombre("ADMINISTRADOR");
                    nuevoRol.setEstado("activo");
                    return rolRepo.save(nuevoRol);
                });

        String correoAdmin = "admin@edurush.pe";
        if (usuarioRepo.findByCorreo(correoAdmin).isEmpty()) {
            Usuario admin = new Usuario();
            admin.setCorreo(correoAdmin);
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRol(rolAdmin);
            admin.setEstado("activo");

            usuarioRepo.save(admin);
            System.out.println("--> EduRush: Usuario administrador inicializado.");
        }
    }
}