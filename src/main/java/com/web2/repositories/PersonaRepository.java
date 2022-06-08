package com.web2.repositories;

import com.web2.entities.Persona;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;

//@Repository("personaRepository")
public interface PersonaRepository extends CrudRepository<Persona, Long>{

}
