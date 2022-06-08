package com.web2.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.web2.entities.Empleado;
import com.web2.entities.Persona;

 
@Controller
@RequestMapping("/")
public class InicioController {
	
	 @GetMapping("/")
	    public String inicio(Model model) {
	       
		 var persona = new Empleado();
		 
		 persona.setNombre("juan");
		 persona.setPuesto("Inspector");
		 
//	        model.addAttribute("personas", personas);
	        
	        return "index";
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