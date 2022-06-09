package com.web2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.web2.entities.Persona;
import com.web2.services.PersonaService;

@Controller
@RequestMapping("/")
public class InicioController {

	@Autowired
	private PersonaService personaService;

	@GetMapping("/")
	public String inicio(Model model) {
		var personas = personaService.listarPersonas();
		model.addAttribute("personas", personas);
		return "index";
	}
	
	
	@GetMapping("/cargaPersona")
	public String agregaPersona(Persona persona) {
		return "persona/modificarPersona";
	}

	@PostMapping("/guardar")
	public String guardarPersona(Persona persona) {
		personaService.guardar(persona);
		return "redirect:/";
	}
	
	@GetMapping("/editar/{id_persona}")
	public String editarPersona(Persona persona, Model model) {
		persona=personaService.encontrarPersona(persona);
		model.addAttribute("persona", persona);
		return "persona/modificarPersona";
	}
	
	
	@GetMapping("/cargaEmpleado")
	public String empleado() {

		return "persona/cargaEmpleado";
	}

	@GetMapping("/cargaConductor")
	public String conductor() {

		return "persona/cargaConductor";
	}
}