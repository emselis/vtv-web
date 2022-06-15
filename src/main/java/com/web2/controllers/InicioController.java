package com.web2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class InicioController {

	@GetMapping("/")
	public String inicio() {		
		return "index";
	}
	
	
}