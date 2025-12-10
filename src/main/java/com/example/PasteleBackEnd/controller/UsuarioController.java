// src/main/java/com/example/PasteleBackEnd/controller/UsuarioController.java
package com.example.PasteleBackEnd.controller;

import com.example.PasteleBackEnd.model.Usuario;
import com.example.PasteleBackEnd.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody Map<String, String> body) {
        try {
            String email = body.get("email");
            String nombre = body.get("nombre");
            String password = body.get("password");
            LocalDate fechaNacimiento = body.get("fechaNacimiento") != null ? 
                    LocalDate.parse(body.get("fechaNacimiento")) : null;
            String telefono = body.get("telefono");
            
            Usuario usuario = usuarioService.registrarUsuario(
                    email, nombre, password, fechaNacimiento, telefono);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Usuario registrado exitosamente");
            response.put("usuario", Map.of(
                    "email", usuario.getEmail(),
                    "nombre", usuario.getNombre(),
                    "esEstudianteDuoc", usuario.isEsEstudianteDuoc(),
                    "mayor50", usuario.isMayor50()
            ));
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody Map<String, String> body) {
        try {
            String email = body.get("email");
            String password = body.get("password");
            
            Usuario usuario = usuarioService.login(email, password);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login exitoso");
            response.put("usuario", Map.of(
                    "email", usuario.getEmail(),
                    "nombre", usuario.getNombre(),
                    "esEstudianteDuoc", usuario.isEsEstudianteDuoc(),
                    "mayor50", usuario.isMayor50()
            ));
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }
}