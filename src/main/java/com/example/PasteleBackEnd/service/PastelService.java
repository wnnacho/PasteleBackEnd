package com.example.PasteleBackEnd.service;

import com.example.PasteleBackEnd.model.Pastel;
import com.example.PasteleBackEnd.repository.PastelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PastelService {

    @Autowired
    private PastelRepository repo;

    // Obtener todos los pasteles
    public List<Pastel> getAll() {
        return repo.findAll();
    }

    // Buscar pastel por ID
    public Pastel getById(String id) {
        return repo.findById(id).orElse(null);
    }

    // Crear pastel
    public Pastel create(Pastel pastel) {
        return repo.save(pastel);
    }

    // Actualizar pastel
    public Pastel update(String id, Pastel pastel) {
        Pastel existing = getById(id);
        if (existing == null) return null;

        existing.setNombre(pastel.getNombre());
        existing.setCategoria(pastel.getCategoria());
        existing.setPrecio(pastel.getPrecio());
        existing.setDescripcion(pastel.getDescripcion());
        existing.setPersonalizable(pastel.getPersonalizable());
        existing.setImagen(pastel.getImagen());

        return repo.save(existing);
    }

    // Eliminar pastel
    public void delete(String id) {
        repo.deleteById(id);
    }
}
