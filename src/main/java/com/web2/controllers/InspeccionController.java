package com.web2.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web2.entities.Inspeccion;
import com.web2.services.InspeccionService;


@Controller
@RequestMapping("/")
public class InspeccionController {

	@Autowired
	@Qualifier("inspeccionService")
	private InspeccionService inspeccionService;
	
	@GetMapping("/cargaInspeccion")
	public String agregaInspeccion(Inspeccion inspeccion) {
		return "inspecciones/cargaInspeccion";
	}

	@PostMapping("/guardarInspeccion")
	public String guardarInspeccion(@Valid Inspeccion inspeccion, Errors errores) {
		if(errores.hasErrors()) {
			return "inspecciones/cargaInspeccion";
		}
		inspeccionService.guardarInspeccion(inspeccion);
		return "redirect:/infoInspecciones";
	}
	
	@GetMapping("/editarInspeccion")	// <---  pasamos query parameters (ver HTML)
	public String editarInspeccion(Inspeccion inspeccion, Model model) {
		inspeccion=inspeccionService.encontrarInspeccion(inspeccion);
		model.addAttribute("inspeccion", inspeccion);
		return "persona/cargaInspeccion";
	}
	
		
	@GetMapping("/infoClientes")
	public String listarC(Model model) {
		var inspecciones = inspeccionService.listarInspecciones();
		model.addAttribute("inspecciones", inspecciones);
		return "informes/infoInspecciones";
	}	

}
