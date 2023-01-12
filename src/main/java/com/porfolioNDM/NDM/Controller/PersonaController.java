/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.porfolioNDM.NDM.Controller;


import com.porfolioNDM.NDM.Dto.dtoPersona;
import com.porfolioNDM.NDM.Entity.Persona;
import com.porfolioNDM.NDM.Security.Controller.Mensaje;
import com.porfolioNDM.NDM.Services.ImpPersonaService;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins="http://localhost:4200")
public class PersonaController {
    
    @Autowired
    ImpPersonaService personaService;
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!personaService.existsById(id)){
            return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.BAD_REQUEST);
        }
        Persona persona = personaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = personaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    /*@PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoedu){
        
        if(StringUtils.isBlank(dtoedu.getNombreE()))
            return new ResponseEntity(new Mensaje("Completar Nombre"), HttpStatus.BAD_REQUEST);
        if(sEducacion.existsByNombreE(dtoedu.getNombreE()))
            return new ResponseEntity(new Mensaje("Educacion repetida"), HttpStatus.BAD_REQUEST);
        
        Educacion educacion = new Educacion(dtoedu.getNombreE(), dtoedu.getDescripcionE());
        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Educacion subida"),HttpStatus.OK);
    }*/
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtoper){
        if(!personaService.existsById(id))
                return new ResponseEntity(new Mensaje("El id no existe"),HttpStatus.BAD_REQUEST);
        if(personaService.existsByNombreE(dtoper.getNombre()) && personaService.getByNombre(dtoper.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Educacion duplicada"),HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoper.getNombre()))
            return new ResponseEntity(new Mensaje("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        
        Persona persona = personaService.getOne(id).get();
        persona.setNombre(dtoper.getNombre());
        persona.setDescripcion(dtoper.getDescripcion());
        personaService.save(persona);
        return new ResponseEntity(new Mensaje("Educacion actualizada"), HttpStatus.OK);
    }
    
    
    
    
}

