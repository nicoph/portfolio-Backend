
package com.porfolioNDM.NDM.Interface;

import com.porfolioNDM.NDM.Entity.Persona;
import java.util.List;


public interface IPersonaService {
    // Traer lista de personas
    public List<Persona> getPersonas();
    
    //Guardar Persona
    public void savePersona(Persona persona);
    
    //Eliminar usuario
    public void deletePersona(Long id);
    
    //Buscar persona
    public Persona findPersona(Long id);
    
}
