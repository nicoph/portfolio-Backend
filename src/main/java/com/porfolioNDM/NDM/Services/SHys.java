/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.porfolioNDM.NDM.Services;

import com.porfolioNDM.NDM.Entity.Hys;
import com.porfolioNDM.NDM.Repository.RHys;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nico
 */

@Transactional
@Service
public class SHys {
    
    @Autowired
    RHys rHys;
    
    public List<Hys> list(){
        return rHys.findAll();
    }
    
    public Optional<Hys> getOne(int id){
        
        return rHys.findById(id);
    }
    
    public Optional<Hys> getByNombre(String nombre){
        return rHys.findByNombre(nombre);
    }
    
    public void save(Hys skill){
        rHys.save(skill);
    }
    
    public void delete(int id){
        rHys.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rHys.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
       
        return rHys.existsByNombre(nombre);
    }
}
