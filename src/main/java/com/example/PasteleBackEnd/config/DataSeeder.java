package com.example.PasteleBackEnd.config;

import com.example.PasteleBackEnd.model.Pastel;
import com.example.PasteleBackEnd.model.AdminUser;
import com.example.PasteleBackEnd.repository.PastelRepository;
import com.example.PasteleBackEnd.repository.AdminUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataSeeder {

                /**
                 * Semilla de datos:
                 * - Crea/actualiza catálogo base de pasteles
                 * - Garantiza un usuario ADMIN por defecto para pruebas
                 */
                @Bean
                CommandLineRunner seedPasteles(PastelRepository repo,
                                                                           AdminUserRepository adminRepo,
                                                                           PasswordEncoder passwordEncoder) {
                return args -> {
                        // Crear/actualizar ADMIN por defecto si no existe
                        String adminEmail = "admin@pasteleriamilsabores.cl";
                        String rawPass = "admin123";
                        adminRepo.findById(adminEmail).ifPresentOrElse(u -> {
                                // Si existe, aseguramos que la contraseña esté encriptada y válida
                                try {
                                        // Si no calza o parece no estar encriptada, la actualizamos
                                        boolean mismatch = !passwordEncoder.matches(rawPass, u.getPasswordHash());
                                        boolean looksPlain = u.getPasswordHash() != null && !u.getPasswordHash().startsWith("$2");
                                        if (mismatch || looksPlain) {
                                                u.setPasswordHash(passwordEncoder.encode(rawPass));
                                                adminRepo.save(u);
                                        }
                                } catch (Exception ex) {
                                        u.setPasswordHash(passwordEncoder.encode(rawPass));
                                        adminRepo.save(u);
                                }
                        }, () -> {
                                AdminUser u = new AdminUser(adminEmail, passwordEncoder.encode(rawPass));
                                adminRepo.save(u);
                        });

                        // Catálogo base de pasteles
                        List<Pastel> pasteles = Arrays.asList(
                    build("TC001", "Torta Cuadrada de Chocolate", "Tortas Cuadradas", 45000.0,
                            "Deliciosa torta de chocolate con capas de ganache y un toque de avellanas. Personalizable con mensajes especiales.",
                            true, "/img/tcchocolate.webp"),
                    build("TC002", "Torta Cuadrada de Frutas", "Tortas Cuadradas", 50000.0,
                            "Una mezcla de frutas frescas y crema chantilly sobre un suave bizcocho de vainilla, ideal para celebraciones.",
                            false, "/img/tccfrutas.jpg"),
                    build("TT001", "Torta Circular de Vainilla", "Tortas Circulares", 40000.0,
                            "Bizcocho de vainilla clásico relleno con crema pastelera y cubierto con un glaseado dulce, perfecto para cualquier ocasión.",
                            false, "/img/tcvainilla.jpg"),
                    build("TT002", "Torta Circular de Manjar", "Tortas Circulares", 42000.0,
                            "Torta tradicional chilena con manjar y nueces, un deleite para los amantes de los sabores dulces y clásicos.",
                            false, "/img/tcmanjar.jpg"),
                    build("PI001", "Mousse de Chocolate", "Postres Individuales", 5000.0,
                            "Postre individual cremoso y suave, hecho con chocolate de alta calidad, ideal para los amantes del chocolate.",
                            false, "/img/mchocolate.jpg"),
                    build("PI002", "Tiramisú Clásico", "Postres Individuales", 5500.0,
                            "Un postre italiano individual con capas de café, mascarpone y cacao, perfecto para finalizar cualquier comida.",
                            false, "/img/tiramisu.jpg"),
                    build("PSA001", "Torta Sin Azúcar de Naranja", "Productos Sin Azúcar", 48000.0,
                            "Torta ligera y deliciosa, endulzada naturalmente, ideal para quienes buscan opciones más saludables.",
                            false, "/img/tsanaranja.webp"),
                    build("PSA002", "Cheesecake Sin Azúcar", "Productos Sin Azúcar", 47000.0,
                            "Suave y cremoso, este cheesecake es una opción perfecta para disfrutar sin culpa.",
                            false, "/img/cheesecake.jpg"),
                    build("PT001", "Empanada de Manzana", "Pastelería Tradicional", 3000.0,
                            "Pastelería tradicional rellena de manzanas especiadas, perfecta para un dulce desayuno o merienda.",
                            false, "/img/emanzana.jpg"),
                    build("PT002", "Tarta de Santiago", "Pastelería Tradicional", 6000.0,
                            "Tradicional tarta española hecha con almendras, azúcar, y huevos, una delicia para los amantes de los postres clásicos.",
                            false, "/img/tsantiago.jpg"),
                    build("PG001", "Brownie Sin Gluten", "Productos Sin Gluten", 4000.0,
                            "Rico y denso, este brownie es perfecto para quienes necesitan evitar el gluten sin sacrificar el sabor.",
                            false, "/img/brownie.jpg"),
                    build("PG002", "Pan Sin Gluten", "Productos Sin Gluten", 3500.0,
                            "Suave y esponjoso, ideal para sándwiches o para acompañar cualquier comida.",
                            false, "/img/pan.jpg"),
                    build("PV001", "Torta Vegana de Chocolate", "Producto Vegano", 50000.0,
                            "Torta de chocolate húmeda y deliciosa, hecha sin productos de origen animal, perfecta para veganos.",
                            false, "/img/tcvegana.jpeg"),
                    build("PV002", "Galletas Veganas de Avena", "Producto Vegano", 4500.0,
                            "Crujientes y sabrosas, estas galletas son una excelente opción para un snack saludable y vegano.",
                            false, "/img/galletas.jpg"),
                    build("TE001", "Torta Especial de Cumpleaños", "Torta Especial", 55000.0,
                            "Diseñada especialmente para celebraciones, personalizable con decoraciones y mensajes únicos.",
                            true, "/img/tortacumple.jpg"),
                    build("TE002", "Torta Especial de Boda", "Torta Especial", 60000.0,
                            "Elegante y deliciosa, esta torta está diseñada para ser el centro de atención en cualquier boda.",
                            false, "/img/tortaboda.jpeg")
            );
            

                        for (Pastel p : pasteles) {
                                // Si existe, actualiza campos clave (incluida la imagen); si no, crea
                                repo.findById(p.getId()).ifPresentOrElse(existing -> {
                                        existing.setNombre(p.getNombre());
                                        existing.setCategoria(p.getCategoria());
                                        existing.setPrecio(p.getPrecio());
                                        existing.setDescripcion(p.getDescripcion());
                                        existing.setPersonalizable(p.getPersonalizable());
                                        existing.setImagen(p.getImagen()); // asegurar ruta /img/...
                                        repo.save(existing);
                                }, () -> repo.save(p));
                        }
        };
    }
    

    private Pastel build(String id, String nombre, String categoria, Double precio,
                         String descripcion, boolean personalizable, String imagen) {
        Pastel p = new Pastel();
        p.setId(id);
        p.setNombre(nombre);
        p.setCategoria(categoria);
        p.setPrecio(precio);
        p.setDescripcion(descripcion);
        p.setPersonalizable(personalizable);
        p.setImagen(imagen);
        return p;
    }
    
}

