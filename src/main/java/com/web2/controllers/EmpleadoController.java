package com.web2.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.web2.entities.Empleado;
import com.web2.services.EmpleadoService;

@Controller
@RequestMapping("/")
public class EmpleadoController {
	
	@Autowired
	@Qualifier("empleadoService")
	private EmpleadoService empleadoService;
	
	@GetMapping("/cargaEmpleado")
	public String agregaEmpleado(Empleado empleado) {
		return "persona/cargaEmpleado";
	}
	
	@PostMapping("/guardarEmpleado")
	public String guardarEmpleado(@Valid Empleado empleado, Errors errores) {
		if(errores.hasErrors()) {
			return "persona/cargaEmpleado";
		}
		empleadoService.guardarEmpleado(empleado);
		return "redirect:/listarEmpleados";
	}
	
	@GetMapping("/editarEmpleado")	// <--- Acá pasamos query parameters (ver HTML)
	public String editarEmpleado(Empleado empleado, Model model) {
		empleado= empleadoService.encontrarEmpleado(empleado);
		model.addAttribute("empleado", empleado);
		return "persona/cargaEmpleado";
	}
	
	@GetMapping("/eliminarEmpleado/{documento}")	// <--- Así pasamos el IdPersona en el path
	public String eliminarEmpleado(Empleado empleado) {
		empleadoService.eliminarEmpleado(empleado);
		return "redirect:/listarEmpleados";
	}
	
	@GetMapping("/listarEmpleados")
	public String listarE(Model model) {
		var empleados = empleadoService.listarEmpleados();
		model.addAttribute("empleados", empleados);
		return "informes/listarEmpleados";
	}	
	
	
	@GetMapping("/buscarEmpleado")
	public String buscarE(Model modelo) {
		modelo.addAttribute("empleado", new Empleado());
		return "persona/buscarEmpleado";
	}

	@GetMapping("/encontrarEmpleado")
	public String encontrarE(@RequestParam String documento, 
			Model modelo, @ModelAttribute("empleado") Empleado empleado){
	
//		Agregar validacionDNI()
		
		empleado = empleadoService.encontrarEmpleado(empleado);
		modelo.addAttribute("empleado", empleado);
		
		return "informes/muestraEmpleado";
	}	
	

}
