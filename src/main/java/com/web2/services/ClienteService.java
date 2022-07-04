package com.web2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web2.entities.Cliente;
import com.web2.repositories.ClienteRepository;

@Service("ClienteService")
public class ClienteService {

	@Autowired
	@Qualifier("clienteRepository")
	private ClienteRepository clienteRepository;
	
	@Transactional(readOnly = true)
	public List<Cliente> listarClientes() {
		// Cast -> List xq findAll devuelve Object
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Transactional
	public void guardarCliente(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Transactional
	public void eliminarCliente(Cliente cliente) {
		clienteRepository.delete(cliente);
	}

	@Transactional(readOnly = true)
	public Cliente encontrarCliente(Cliente cliente) {
		//findById devuelve objeto "Opcional", entonces:
		//Lanzamos excepción con método .orElseThrow o mostramos null con .orElse
		return clienteRepository.findById(cliente.getDocumento()).orElse(null);
	}	

	@Transactional(readOnly = true)
	public Cliente encontrarCliente(String documento) {
		//findById devuelve objeto "Opcional", entonces:
		//Lanzamos excepción con método .orElseThrow o mostramos null con .orElse
		return clienteRepository.findById(documento).orElse(null);
	}
}
