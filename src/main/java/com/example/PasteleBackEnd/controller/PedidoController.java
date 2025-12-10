// src/main/java/com/example/PasteleBackEnd/controller/PedidoController.java
package com.example.PasteleBackEnd.controller;

import com.example.PasteleBackEnd.model.Pedido;
import com.example.PasteleBackEnd.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/pedidos")
@CrossOrigin(origins = "http://localhost:3000")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;
    
    // Crear pedido (usuario autenticado)
    @PostMapping
    public ResponseEntity<?> crearPedido(@RequestBody Map<String, Object> body) {
        try {
            String usuarioEmail = (String) body.get("usuarioEmail");
            List<Map<String, Object>> items = (List<Map<String, Object>>) body.get("items");
            String direccionEnvio = (String) body.get("direccionEnvio");
            String telefonoContacto = (String) body.get("telefonoContacto");
            String notas = (String) body.get("notas");
            
            Pedido pedido = pedidoService.crearPedido(
                    usuarioEmail, items, direccionEnvio, telefonoContacto, notas);
            
            return ResponseEntity.ok(Map.of(
                    "message", "Pedido creado exitosamente",
                    "pedidoId", pedido.getId(),
                    "total", pedido.getTotal()
            ));
            
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    // Obtener pedidos del usuario
    @GetMapping("/usuario/{email}")
public ResponseEntity<?> obtenerPedidosUsuario(@PathVariable String email) {
    try {
        System.out.println("Buscando pedidos para usuario: " + email);
        List<Pedido> pedidos = pedidoService.obtenerPedidosPorUsuario(email);
        System.out.println("Encontrados " + pedidos.size() + " pedidos");
        return ResponseEntity.ok(pedidos);
    } catch (Exception e) {
        System.err.println("Error obteniendo pedidos: " + e.getMessage());
        e.printStackTrace();
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }
}
    
    // Obtener todos los pedidos (ADMIN)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> obtenerTodosPedidos() {
        try {
            List<Pedido> pedidos = pedidoService.obtenerTodosLosPedidos();
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    // Cambiar estado del pedido (ADMIN)
    @PutMapping("/{id}/estado")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> cambiarEstado(@PathVariable String id, @RequestBody Map<String, String> body) {
        try {
            String nuevoEstado = body.get("estado");
            Pedido pedido = pedidoService.cambiarEstado(id, nuevoEstado);
            return ResponseEntity.ok(Map.of(
                    "message", "Estado actualizado",
                    "pedido", pedido
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    // Eliminar pedido (ADMIN)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> eliminarPedido(@PathVariable String id) {
        try {
            pedidoService.eliminarPedido(id);
            return ResponseEntity.ok(Map.of("message", "Pedido eliminado"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}