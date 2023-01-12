/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.porfolioNDM.NDM.Controller;

import com.porfolioNDM.NDM.Dto.dtoHys;
import com.porfolioNDM.NDM.Entity.Hys;
import com.porfolioNDM.NDM.Security.Controller.Mensaje;
import com.porfolioNDM.NDM.Services.SHys;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nico
 */

@RestController
@RequestMapping("/hys")
@CrossOrigin(origins="http://localhost:4200")
public class CHys {
    
    @Autowired
    SHys sHys;
    
    
    @GetMapping("/lista")
    public ResponseEntity<List<Hys>> list(){
        List<Hys> list = sHys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHys dtohys){
        
        if(StringUtils.isBlank(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje("Completar Nombre"), HttpStatus.BAD_REQUEST);
        if(sHys.existsByNombre(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje("Hys repetida"), HttpStatus.BAD_REQUEST);
        
        Hys hys = new Hys(dtohys.getNombre(), dtohys.getPorcentaje());
        sHys.save(hys);
        return new ResponseEntity(new Mensaje("Skill subida"),HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHys dtohys){
        if(!sHys.existsById(id))
                return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.BAD_REQUEST);
        if(sHys.existsByNombre(dtohys.getNombre()) && sHys.getByNombre(dtohys.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("skill duplicada"),HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        
        Hys hys = sHys.getOne(id).get();
        hys.setNombre(dtohys.getNombre());
        hys.setPorcentaje(dtohys.getPorcentaje());
        sHys.save(hys);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sHys.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.BAD_REQUEST);
        sHys.delete(id);
        return new ResponseEntity(new Mensaje("Skill eliminada"),HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Hys> getById(@PathVariable("id") int id){
        if(!sHys.existsById(id)){
            return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.BAD_REQUEST);
        }
        Hys hys = sHys.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }
    
}

    

