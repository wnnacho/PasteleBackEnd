package com.example.PasteleBackEnd.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admin_users")
public class AdminUser {
    
    @Id
    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    // Constructores, getters y setters (ya los tienes)
    public AdminUser() {}

    public AdminUser(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}
