package com.web2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web2.entities.Persona;
import com.web2.repositories.PersonaRepository;

// Para poder inyectar la esta Clase-impl de PersonaCervice en el Controlador de Spring
@Service
public class PersonaServiceImpl implements PersonaService {

	// El controlador no usará capa Datos PersonaRepository
	// Usará PersonaServiceImpl -> interface PersonaService
	@Autowired
	private PersonaRepository personarepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Persona> listarPersonas() {
		// Cast -> List xq findAll devuelve Object
		return (List<Persona>) personarepository.findAll();
	}

	@Override
	@Transactional
	public void guardar(Persona persona) {
		personarepository.save(persona);
	}

	@Override
	@Transactional
	public void eliminar(Persona persona) {
		personarepository.delete(persona);
	}

	@Override
	@Transactional(readOnly = true)
	public Persona encontrarPersona(Persona persona) {
		//findById devuelve objeto "Opcional", entonces:
		//Lanzamos excepción con método .orElseThrow o mostramos null con .orElse
		return personarepository.findById(persona.getIdPersona()).orElse(null);
	}

}
