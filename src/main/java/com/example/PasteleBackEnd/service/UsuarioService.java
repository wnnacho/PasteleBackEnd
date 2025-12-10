// src/main/java/com/example/PasteleBackEnd/service/UsuarioService.java
package com.example.PasteleBackEnd.service;

import com.example.PasteleBackEnd.model.Usuario;
import com.example.PasteleBackEnd.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Usuario registrarUsuario(String email, String nombre, String password, 
                                   LocalDate fechaNacimiento, String telefono) {
        
        if (usuarioRepository.existsByEmail(email)) {
            throw new RuntimeException("El email ya está registrado");
        }
        
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setNombre(nombre);
        usuario.setPasswordHash(passwordEncoder.encode(password));
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setTelefono(telefono);
        
        // Verificar si es estudiante Duoc
        if (email.toLowerCase().contains("@duoc.cl") || 
            email.toLowerCase().contains("@duocuc.cl")) {
            usuario.setEsEstudianteDuoc(true);
        }
        
        // Verificar si es mayor de 50 años
        if (fechaNacimiento != null) {
            int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
            usuario.setMayor50(edad > 50);
        }
        
        return usuarioRepository.save(usuario);
    }
    
    public Usuario login(String email, String password) {
        Usuario usuario = usuarioRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if (!passwordEncoder.matches(password, usuario.getPasswordHash())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        
        return usuario;
    }
}