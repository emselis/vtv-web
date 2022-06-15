//package com.web2.controllers;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.web2.entities.Persona;
//import com.web2.services.ClienteService;
//
//
//@Controller
//@RequestMapping("/")
//public class ClienteController {
//
//	@Autowired
//	@Qualifier("clienteService")
//	private ClienteService clienteService;
//	
//	@GetMapping("/cargaCliente")
//	public String agregaCliente(Persona cliente) {
//		return "persona/cargaCliente";
//	}
//
//	@PostMapping("/guardarCliente")
//	public String guardarCliente(@Valid Persona cliente, Errors errores) {
//		if(errores.hasErrors()) {
//			return "persona/cargaCliente";
//		}
//		clienteService.guardarCliente(cliente);
//		return "redirect:/infoClientes";
//	}
//	
//	@GetMapping("/editarCliente")	// <---  pasamos query parameters (ver HTML)
//	public String editarCliente(Persona cliente, Model model) {
//		cliente=clienteService.encontrarCliente(cliente);
//		model.addAttribute("cliente", cliente);
//		return "persona/cargaCliente";
//	}
//	
//	@GetMapping("/eliminarCliente/{documento}")	// <--- pasamos el IdPersona en el path
//	public String eliminarCliente(Persona cliente) {
//		clienteService.eliminarCliente(cliente);
//		return "redirect:/infoClientes";
//	}
//		
//	@GetMapping("/infoClientes")
//	public String listarC(Model model) {
//		var clientes = clienteService.listarClientes();
//		model.addAttribute("clientes", clientes);
//		return "informes/infoClientes";
//	}	
//
//}
