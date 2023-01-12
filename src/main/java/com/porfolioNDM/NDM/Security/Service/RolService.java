/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.porfolioNDM.NDM.Security.Service;

import com.porfolioNDM.NDM.Security.Entity.Rol;
import com.porfolioNDM.NDM.Security.Enums.RolNombre;
import com.porfolioNDM.NDM.Security.Repository.iRolRepository;
import java.util.Optional;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nico
 */
@Service
@Transactional
public class RolService {
    
    @Autowired
    iRolRepository iRolRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return iRolRepository.findByRolNombre(rolNombre);
    }
    
    public void save(Rol rol){
        iRolRepository.save(rol);
    }
    
}
