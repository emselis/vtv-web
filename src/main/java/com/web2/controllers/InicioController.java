package com.web2.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

 
@Controller
@RequestMapping("/")
public class InicioController {
	
	 @GetMapping("/")
	    public String inicio(Model model) {
	       
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