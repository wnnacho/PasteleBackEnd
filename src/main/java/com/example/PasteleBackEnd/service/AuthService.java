package com.example.PasteleBackEnd.service;

import com.example.PasteleBackEnd.model.AdminUser;
import com.example.PasteleBackEnd.repository.AdminUserRepository;
import com.example.PasteleBackEnd.security.CustomUserDetailsService;
import com.example.PasteleBackEnd.security.JwtUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servicio de autenticación basado en JWT (stateless).
 * Genera un token JWT al validar credenciales del administrador.
 */
@Service
public class AuthService {
    private final AdminUserRepository adminRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public AuthService(AdminUserRepository adminRepo,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil,
                       CustomUserDetailsService userDetailsService) {
        this.adminRepo = adminRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    public String login(String username, String password) {
        AdminUser user = adminRepo.findById(username).orElse(null);
        if (user != null && passwordEncoder.matches(password, user.getPasswordHash())) {
            // Construir UserDetails (con rol ADMIN) y firmar token
            UserDetails details = userDetailsService.loadUserByUsername(username);
            return jwtUtil.generateToken(details);
        }
        return null;
    }

    // Logout en esquema stateless: no hay que invalidar en servidor
    public void logout(String token) {
        // no-op; el front debe eliminar el token del almacenamiento local
    }
}
