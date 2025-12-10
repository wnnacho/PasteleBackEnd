// src/main/java/com/example/PasteleBackEnd/repository/UsuarioRepository.java
package com.example.PasteleBackEnd.repository;

import com.example.PasteleBackEnd.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    boolean existsByEmail(String email);
}