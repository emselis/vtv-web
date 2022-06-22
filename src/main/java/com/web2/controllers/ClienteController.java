package com.web2.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web2.entities.Cliente;
import com.web2.services.ClienteService;

@Controller
@RequestMapping("/")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping("/cargaCliente")
	public String cargaCliente(Cliente cliente){
		
		return"persona/cargaCliente";
	}

	
	@PostMapping("/guardarCliente")
	public String guardarCliente(@Valid Cliente cliente, Errors errores) {
		if(errores.hasErrors()) {
			return "persona/cargaCliente";
		}
		clienteService.guardarCliente(cliente);
		return "redirect:/listarClientes";
	}
	
	@GetMapping("/editarCliente")	// <--- Acá pasamos query parameters (ver HTML)
	public String editarCliente(Cliente cliente, Model model) {
		cliente= clienteService.encontrarCliente(cliente);
		model.addAttribute("cliente", cliente);
		return "persona/cargaCliente";
	}
	
	@GetMapping("/eliminarCliente/{documento}")	// <--- Así pasamos el IdPersona en el path
	public String eliminarCliente(Cliente cliente) {
		clienteService.eliminarCliente(cliente);
		return "redirect:/listarClientes";
	}
	
	@GetMapping("/listarClientes")
	public String listarC(Model model) {
		var clientes = clienteService.listarClientes();
		model.addAttribute("clientes", clientes);
		return "informes/listarClientes";
	}	
	
	
	@GetMapping("/buscarCliente")
	public String buscarC(Model modelo) {
		modelo.addAttribute("cliente", new Cliente());
		return "persona/buscarCliente";
	}

	@GetMapping("/encontrarCliente")
	public String encontrarC(@RequestParam String documento, 
			Model modelo, @ModelAttribute("cliente") Cliente cliente){
	
//		Agregar validacionDNI()
		
		cliente = clienteService.encontrarCliente(cliente);
		modelo.addAttribute("cliente", cliente);
		
		return "informes/muestraCliente";
	}	

	
}
