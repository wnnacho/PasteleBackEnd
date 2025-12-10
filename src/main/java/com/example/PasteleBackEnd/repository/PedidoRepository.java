// src/main/java/com/example/PasteleBackEnd/repository/PedidoRepository.java
package com.example.PasteleBackEnd.repository;

import com.example.PasteleBackEnd.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, String> {
    List<Pedido> findByUsuarioEmail(String email);
    List<Pedido> findByEstado(String estado);
}