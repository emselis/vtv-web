//package com.web2.services;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.web2.entities.Persona;
//import com.web2.repositories.PersonaRepository;
//
//@Service("clienteService")
//public class ClienteService{
//
//	@Autowired
//	@Qualifier("personaRepository")
//	private PersonaRepository clienteRepository;
//	
//	@Transactional(readOnly = true)
//	public List<Persona> listarClientes() {
//		// Cast -> List xq findAll devuelve Object
//		return (List<Persona>) clienteRepository.findAll();
//	}
//
//	@Transactional
//	public void guardarCliente(Persona cliente) {
//		clienteRepository.save(cliente);
//	}
//
//	@Transactional
//	public void eliminarCliente(Persona cliente) {
//		clienteRepository.delete(cliente);
//	}
//
//	@Transactional(readOnly = true)
//	public Persona encontrarCliente(Persona cliente) {
//		//findById devuelve objeto "Opcional", entonces:
//		//Lanzamos excepción con método .orElseThrow o mostramos null con .orElse
//		return clienteRepository.findById(cliente.getDocumento()).orElse(null);
//	}
//
//}
