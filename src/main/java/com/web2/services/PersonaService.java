package com.web2.services;

import java.util.*;
import com.web2.entities.*;

public interface PersonaService {

	public List<Persona> listarPersonas();
	
	public void guardar(Persona persona);
	
	public void eliminar(Persona persona);
	
	public Persona encontrarPersona(Persona persona);
}
