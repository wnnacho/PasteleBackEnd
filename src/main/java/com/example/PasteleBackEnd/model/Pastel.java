package com.example.PasteleBackEnd.model;

import jakarta.persistence.*; // o javax.persistence.* según versión

@Entity
@Table(name = "pasteles")
public class Pastel {
    
    @Id
    @Column(name = "id", length = 10)
    private String id;

    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    @Column(name = "categoria", length = 50)
    private String categoria;

    @Column(name = "precio", columnDefinition = "DECIMAL(10,2)")
    private Double precio;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "personalizable")
    private boolean personalizable = false;

    @Column(name = "imagen", length = 255)
    private String imagen;

    public Pastel(){}

    //getters y setters
    public String getId(){return id;}
    public void setId(String id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getCategoria(){return categoria;}
    public void setCategoria(String categoria){this.categoria = categoria;}

    public Double getPrecio() {return precio;}
    public void setPrecio(Double precio) {this.precio=precio;}

    public String getDescripcion(){return descripcion;}
    public void setDescripcion (String descripcion) {this.descripcion = descripcion;}

    public boolean getPersonalizable(){return personalizable;}
    public void setPersonalizable(boolean personalizable){this.personalizable = personalizable;}

    public String getImagen(){return imagen;}
    public void setImagen(String imagen) {this.imagen=imagen;}
}