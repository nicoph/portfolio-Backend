/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.porfolioNDM.NDM.Controller;

import com.porfolioNDM.NDM.Dto.dtoEducacion;
import com.porfolioNDM.NDM.Entity.Educacion;
import com.porfolioNDM.NDM.Security.Controller.Mensaje;
import com.porfolioNDM.NDM.Services.SEducacion;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Nico
 */
@Controller
@RequestMapping("/educacion")
@CrossOrigin(origins="http://localhost:4200")
public class CEducacion {
    
    @Autowired
    SEducacion sEducacion;
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.BAD_REQUEST);
        }
        Educacion educacion = sEducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoedu){
        
        if(StringUtils.isBlank(dtoedu.getNombreE()))
            return new ResponseEntity(new Mensaje("Completar Nombre"), HttpStatus.BAD_REQUEST);
        if(sEducacion.existsByNombreE(dtoedu.getNombreE()))
            return new ResponseEntity(new Mensaje("Educacion repetida"), HttpStatus.BAD_REQUEST);
        
        Educacion educacion = new Educacion(dtoedu.getNombreE(), dtoedu.getDescripcionE());
        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Educacion subida"),HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoedu){
        if(!sEducacion.existsById(id))
                return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.BAD_REQUEST);
        if(sEducacion.existsByNombreE(dtoedu.getNombreE()) && sEducacion.getByNombreE(dtoedu.getNombreE()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Educacion duplicada"),HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoedu.getNombreE()))
            return new ResponseEntity(new Mensaje("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        
        Educacion educacion = sEducacion.getOne(id).get();
        educacion.setNombreE(dtoedu.getNombreE());
        educacion.setDescripcionE(dtoedu.getDescripcionE());
        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Educacion actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sEducacion.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.BAD_REQUEST);
        sEducacion.delete(id);
        return new ResponseEntity(new Mensaje("Educacion eliminada"),HttpStatus.OK);
    }
    
    
}
