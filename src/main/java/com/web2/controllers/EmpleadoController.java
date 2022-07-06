package com.web2.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web2.entities.Empleado;
import com.web2.services.ClienteService;
import com.web2.services.EmpleadoService;
import com.web2.enumeraciones.*;

@Controller
@RequestMapping("/")
public class EmpleadoController {
	
	@Autowired
//	@Qualifier("empleadoService")
	private EmpleadoService empleadoService;
	
	@Autowired
//	@Qualifier("clienteService")
	private ClienteService clienteService;
	
	@GetMapping("/cargaEmpleado")
	public String agregaEmpleado(Model modelo, Empleado empleado) {
		
		modelo.addAttribute("empleado", empleado);
		modelo.addAttribute("puestos", PuestosEmpleados.values());
		return "persona/cargaEmpleado";
	}
	
	
	@PostMapping("/guardarEmpleado")
	public String guardarEmpleado(@Valid @ModelAttribute Empleado empleado, Errors errores, Model modelo, BindingResult resultado, RedirectAttributes atributos) {
		// Sin verificacar estos errores pincha html
		modelo.addAttribute("empleado", empleado);
		modelo.addAttribute("puestos", PuestosEmpleados.values());	// Puestos de Enum
		System.out.println("Errores al cargar el empleado");
		if(errores.hasErrors()) {
			return "persona/cargaEmpleado";
		}
		String dni = empleado.getDocumento();
		System.out.println("DNI solo numeros: " + dni.matches("[0-9]+"));
//		if(!empleado.getDocumento().matches("[0-9]+")) {
//			FieldError error = new FieldError("empleado", "documento", "El documento solo debe contener numeros.");
//			resultado.addError(error);
//		}
//		if(dni.length()>8 || dni.length()<7) {
//				FieldError error = new FieldError("empleado", "documento", "El documento debe tener entre 7 y 8 caracteres.");
//				resultado.addError(error);
//		}			
		if(empleadoService.encontrarEmpleado(empleado) != null) {
			FieldError error = new FieldError("empleado", "documento", "Ya existe un emleado con este documento.");
			resultado.addError(error);
			return "persona/cargaEmpleado";
		}
		
		// Verifica si DNI también en tabla clientes: <SI> guarda solo campos en tabla "empleados" -- <NO> Guarda empleado normalmente.
		// Como guardar: DNI y campos de empleado (activo + puesto) Solo en tabla "empleados" ? solo por Entity?
		
//		if(resultado.hasErrors()) {
//			modelo.addAttribute("empleado", empleado);
//			modelo.addAttribute("puestos", PuestosEmpleados.values());	// Puestos de Enum
//			System.out.println(resultado);
//			return "persona/cargaEmpleado";
//		}
		
		if(clienteService.encontrarCliente(empleado.getDocumento()) != null) {
			var cliente = clienteService.encontrarCliente(empleado.getDocumento());
			System.out.println("Se inserta empleado a tabla. Ya existe un cliente con ese documento.");
			empleado.setNombre(cliente.getNombre());
			empleado.setApellido(cliente.getApellido());
			empleadoService.insertarEmpleado(empleado);
			System.out.println("Empleado insertado OK");
			modelo.addAttribute("empleado", empleado);
			System.out.println(empleado.toString().toString());
			atributos.addFlashAttribute("success", "Empleado creado correctamente.");
			return "/informes/muestraEmpleado";
		}
		
		empleadoService.guardarEmpleado(empleado);
		System.out.println("Empleado guardado OK");
		atributos.addFlashAttribute("success", "Empleado creado correctamente.");
		return "/informes/muestraEmpleado";
	}
	
	
	@GetMapping("/editarEmpleado")	// <--- Acá pasamos query parameters (ver HTML)
	public String editarEmpleado(Empleado empleado, Model modelo) {
		empleado= empleadoService.encontrarEmpleado(empleado);
		modelo.addAttribute("empleado", empleado);
		modelo.addAttribute("puestos", PuestosEmpleados.values());	// Puestos de Enum
		return "persona/editarEmpleado";
	}
	
	@PostMapping("/editarEmpleado")
	public String editEmpleado(@Valid @ModelAttribute Empleado empleado, Errors errores, Model modelo) {
		// Sin verificacar estos errores pincha html
		if(errores.hasErrors()) {
			modelo.addAttribute("empleado", empleado);
			System.out.println("Errores al cargar el empleado");
			return "persona/editarEmpleado";
		}
		empleadoService.guardarEmpleado(empleado);
		System.out.println("Empleado guardado OK");
		return "/informes/muestraEmpleado";
	}
	
	@GetMapping("/eliminarEmpleado/{documento}")	// <--- Así pasamos el IdPersona en el path
	public String eliminarEmpleado(Empleado empleado) {
		empleadoService.eliminarEmpleado(empleado);
		return "redirect:/listarEmpleados";
	}
	
	@GetMapping("/listarEmpleados")
	public String listarE(Model model) {
		model.addAttribute("titulo", "Lista de Empleados");
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
			Model modelo, Empleado empleado, BindingResult resultado){
	
//		Agregar validacionDNI()
		String dni = documento;
		System.out.println("DNI solo numeros: " + dni.matches("[0-9]+"));
		if(!dni.matches("[0-9]+")) {
			FieldError error = new FieldError("empleado", "documento", "El documento solo debe contener numeros.");
			resultado.addError(error);
		}
		if(dni.length()>8 || dni.length()<7) {
				FieldError error = new FieldError("empleado", "documento", "El documento debe tener entre 7 y 8 caracteres.");
				resultado.addError(error);
		}		
		if(resultado.hasErrors()) {
			modelo.addAttribute("empleado", empleado);
			System.out.println("Errores al cargar el empleado");
			return "persona/buscarEmpleado";
		}		
		empleado = empleadoService.encontrarEmpleado(empleado);
		modelo.addAttribute("empleado", empleado);
		
		return "informes/muestraEmpleado";
	}	
	
}


//@PostMapping("/guardarEmpleado")
//public String guardarEmpleado(@Valid Empleado empleado, Errors errores) {
//	System.out.println("Solo numeros" + empleado.getDocumento().matches("[0-9]+"));
//	if(errores.hasErrors()) {
//		return "persona/cargaEmpleado";
//	}
//	empleadoService.guardarEmpleado(empleado);
//	return "redirect:/listarEmpleados";
//}
