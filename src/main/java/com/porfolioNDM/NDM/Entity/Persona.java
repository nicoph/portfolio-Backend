/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.porfolioNDM.NDM.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Nico
 */
@Entity
public class Persona {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Size(min=2, max=50, message = "No cumple con la longitud")
    private String nombre;
    @NotNull
    @Size(min=2, max=50, message = "No cumple con la longitud")
    private String apellido;
    
    @NotNull
    @Size(min=2, max=500, message = "No cumple con la longitud")
    private String descripcion;
    
    
    private String img;
    

    public Persona() {
    }

    public Persona(String nombre, String apellido, String descripcion, String img) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
    
    
