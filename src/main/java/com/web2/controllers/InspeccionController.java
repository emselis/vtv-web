package com.web2.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.web2.entities.*;
import com.web2.services.*;


@Controller
@RequestMapping("/inspecciones")
public class InspeccionController {

	@Autowired
//	@Qualifier("inspeccionService")
	private InspeccionService inspeccionService;
	
	@Autowired
//	@Qualifier("empleadoService")
	private EmpleadoService empleadoService;
	
	@Autowired
	private ClienteService clienteService;
	
	
	@GetMapping("/iniciaInspeccion")
	public String asignaInspector( Model modelo) {
//		var empleados = empleadoService.listarEmpleados();
		var empleados = empleadoService.empleadosEstadoPuesto("ALTA", "INSPECTOR");
		modelo.addAttribute("empleados", empleados);
		modelo.addAttribute("empleado", new Empleado());
		return "inspecciones/asignaInspector";
	}

	
	@PostMapping("/asignaCliente")
	public String asignaCliente(@RequestParam String documento, Model modelo,
			Empleado empleado, Cliente cliente) {
		
		empleado = empleadoService.encontrarEmpleado(empleado);
		modelo.addAttribute("empleado", empleado);
		modelo.addAttribute("cliente", new Cliente());
		return "inspecciones/asignaCliente";
	}
	
	@GetMapping("/asignaPropietario")
	public String asignaPropietario(@RequestParam String documento, Model modelo) {
//	cliente = clienteService.encontrarCliente(cliente);
		
//		TODO
		
	return"";
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
