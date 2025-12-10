// src/main/java/com/example/PasteleBackEnd/service/PedidoService.java
package com.example.PasteleBackEnd.service;

import com.example.PasteleBackEnd.model.*;
import com.example.PasteleBackEnd.repository.PedidoRepository;
import com.example.PasteleBackEnd.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Transactional
    public Pedido crearPedido(String usuarioEmail, List<Map<String, Object>> items, 
                             String direccionEnvio, String telefonoContacto, String notas) {
        
        Usuario usuario = usuarioRepository.findById(usuarioEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setDireccionEnvio(direccionEnvio);
        pedido.setTelefonoContacto(telefonoContacto);
        pedido.setNotas(notas);
        pedido.setFechaPedido(LocalDateTime.now());
        
        double total = 0.0;
        
        // Procesar cada item del carrito
        for (Map<String, Object> item : items) {
            String productoId = (String) item.get("productoId");
            String productoNombre = (String) item.get("productoNombre");
            Double precio = ((Number) item.get("precio")).doubleValue();
            Integer cantidad = ((Number) item.get("cantidad")).intValue();
            String personalizacion = (String) item.get("personalizacion");
            
            ItemPedido itemPedido = new ItemPedido(productoId, productoNombre, precio, cantidad);
            itemPedido.setPersonalizacion(personalizacion);
            
            pedido.agregarItem(itemPedido);
            total += itemPedido.getSubtotal();
        }
        
        // Aplicar descuentos
        if (usuario.isMayor50()) {
            total *= 0.5; // 50% de descuento
        } else if (usuario.isEsEstudianteDuoc()) {
            // Podrías aplicar otro descuento aquí
        }
        
        pedido.setTotal(total);
        
        return pedidoRepository.save(pedido);
    }
    
    public List<Pedido> obtenerPedidosPorUsuario(String usuarioEmail) {
        return pedidoRepository.findByUsuarioEmail(usuarioEmail);
    }
    
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }
    
    public Pedido cambiarEstado(String pedidoId, String nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        
        pedido.setEstado(nuevoEstado);
        return pedidoRepository.save(pedido);
    }
    
    public void eliminarPedido(String pedidoId) {
        pedidoRepository.deleteById(pedidoId);
    }
}