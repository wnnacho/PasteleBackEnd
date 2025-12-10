// src/main/java/com/example/PasteleBackEnd/model/Usuario.java
package com.example.PasteleBackEnd.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    private String email;  // Usamos email como ID
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String passwordHash;
    
    private LocalDate fechaNacimiento;
    private String telefono;
    private boolean esEstudianteDuoc = false;
    private boolean mayor50 = false;
    
    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();
    
    // Constructores
    public Usuario() {}
    
    public Usuario(String email, String nombre, String passwordHash) {
        this.email = email;
        this.nombre = nombre;
        this.passwordHash = passwordHash;
    }
    
    // Getters y Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public boolean isEsEstudianteDuoc() { return esEstudianteDuoc; }
    public void setEsEstudianteDuoc(boolean esEstudianteDuoc) { this.esEstudianteDuoc = esEstudianteDuoc; }
    
    public boolean isMayor50() { return mayor50; }
    public void setMayor50(boolean mayor50) { this.mayor50 = mayor50; }
    
    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }
}