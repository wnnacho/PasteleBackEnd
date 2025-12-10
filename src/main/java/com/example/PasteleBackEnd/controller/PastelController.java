package com.example.PasteleBackEnd.controller;

import com.example.PasteleBackEnd.model.Pastel;
import com.example.PasteleBackEnd.service.PastelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pasteles")
@CrossOrigin(origins = "http://localhost:3000")  // Permite peticiones desde tu frontend
public class PastelController {

    @Autowired
    private PastelService service;

    // GET: Obtener todos los pasteles
    @GetMapping
    public List<Pastel> list() {
        return service.getAll();
    }

    // GET: Obtener un pastel por ID
    @GetMapping("/{id}")
    public Pastel get(@PathVariable String id) {
        return service.getById(id);
    }

    // POST: Crear un pastel
    @PostMapping
    public Pastel create(@RequestBody Pastel pastel) {
        return service.create(pastel);
    }

    // PUT: Actualizar un pastel
    @PutMapping("/{id}")
    public Pastel update(@PathVariable String id, @RequestBody Pastel pastel) {
        return service.update(id, pastel);
    }

    // DELETE: Eliminar un pastel
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
