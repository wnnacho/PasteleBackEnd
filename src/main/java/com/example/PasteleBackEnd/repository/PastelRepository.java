package com.example.PasteleBackEnd.repository;

import com.example.PasteleBackEnd.model.Pastel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PastelRepository extends JpaRepository<Pastel, String> {
// Si necesitas búsquedas específicas, puedes agregar métodos aquí
}