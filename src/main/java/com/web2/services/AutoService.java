package com.web2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.web2.entities.Auto;
import com.web2.entities.Cliente;
import com.web2.repositories.AutoRepository;

@Service("autoService")
public class AutoService {

	@Autowired
	@Qualifier("autoRepository")
	private AutoRepository autoRepository;
	
	public AutoService() {}
	
//	Constructor creado para Test
	public AutoService(AutoRepository autoRepo) {
		this.autoRepository = autoRepo;
	}

	
	public List<Auto> listarAutos() {
		// Cast -> List xq findAll devuelve Object
		return (List<Auto>) autoRepository.findAll();
	}

	public void guardarAuto(Auto auto) {
		autoRepository.save(auto);
	}

	public Auto buscarPorDominio(Auto auto) {
		//findById devuelve objeto "Opcional", entonces:
		//Lanzamos excepción con método .orElseThrow o mostramos null con .orElse
		return autoRepository.findById(auto.getDominio()).orElse(null);
	}
	
	public Auto buscarPorDominio(String dominio) {
		//findById devuelve objeto "Opcional", entonces:
		//Lanzamos excepción con método .orElseThrow o mostramos null con .orElse
		return autoRepository.findById(dominio).orElse(null);
	}
	
	public List<Auto> buscarPorPropietario(Cliente propietario) {
		return autoRepository.findByPropietario(propietario);
	}
	
	// SOLO PARA TEST
	public List<Auto> buscarPorPropietario(String documento) {
		return autoRepository.findByPropietario(documento);
	}
	
	public List<Auto> buscarPorEstado(Auto auto) {
		return autoRepository.findByEstado(auto.getEstado());
	}	
	
	
	public List<Auto> buscarPorEstado(String estado) {
		return autoRepository.findByEstado(estado);
	}
	
	public void eliminarAuto(Auto auto) {
		autoRepository.delete(auto);
	}

}
