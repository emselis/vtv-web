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
//		System.out.println(empleado.getApellido());	// --------------------  test
		return "persona/cargaEmpleado";
	}
	
	@PostMapping("/guardarEmpleado")
	public String guardarEmpleado(@Valid Empleado empleado, Errors errores) {
//		System.out.println("entra guardarEmpleado");
		if(errores.hasErrors()) {
//			System.out.println("Sale con error al guardarEmpleado");
//			System.out.println(errores.getFieldError());
			return "persona/cargaEmpleado";
		}
		empleadoService.guardarEmpleado(empleado);
//		System.out.println("guardarEmpleado --> OK " + empleado.getNombre());
		return "redirect:/listarEmpleados";
	}
	
	@GetMapping("/editarEmpleado")	// <--- Acá pasamos query parameters (ver HTML)
	public String editarEmpleado(Empleado empleado, Model model) {
		empleado= empleadoService.encontrarEmpleado(empleado);
//		System.out.println(empleado.getNombre()); // --------------------  test
		model.addAttribute("empleado", empleado);
//		return "redirect:/cargaEmpleado";
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
	public String buscarE(Empleado empleado, Model model) {
		return "persona/buscarEmpleado";
	}

	@PostMapping("/encontrarEmpleado")
	public String encontrarE(Empleado empleado, Model modelo) {
	
//		Agregar validacionDNI()
		
		empleado = empleadoService.encontrarEmpleado(empleado);
		modelo.addAttribute("empleado", empleado);
		
		return "informes/muestraEmpleado";
	}	
	


}
