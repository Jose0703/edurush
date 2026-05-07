package com.edurush.pe.service.impl;

import com.edurush.pe.model.Usuario;
import com.edurush.pe.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class UsuarioDetallesServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + correo));

        if (!"activo".equalsIgnoreCase(usuario.getEstado())) {
            throw new UsernameNotFoundException("La cuenta se encuentra inactiva o suspendida.");
        }

        // Spring Security requiere el prefijo "ROLE_" para los roles, asi que lo convertimos
        String nombreRol = "ROLE_" + usuario.getRol().getNombre();

        return new User(
                usuario.getCorreo(),
                usuario.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(nombreRol))
        );
    }
}