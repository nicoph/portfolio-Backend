/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.porfolioNDM.NDM.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Nico
 */

@Entity
@Getter // ver si funciona con lombok
@Setter
public class Hys {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int porcentaje;

    public Hys() {
    }

    public Hys(String nombre, int porcentaje) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }
    
    
    
}
