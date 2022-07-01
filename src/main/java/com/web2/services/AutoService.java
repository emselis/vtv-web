package com.web2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web2.entities.Auto;
import com.web2.entities.Cliente;
import com.web2.repositories.AutoRepository;

@Service("autoService")
public class AutoService {

	@Autowired
	@Qualifier("autoRepository")
	private AutoRepository autoRepository;
	
	@Transactional(readOnly = true)
	public List<Auto> listarAutos() {
		// Cast -> List xq findAll devuelve Object
		return (List<Auto>) autoRepository.findAll();
	}

	@Transactional
	public void guardarAuto(Auto auto) {
		autoRepository.save(auto);
	}

	@Transactional(readOnly = true)
	public Auto buscarPorDominio(Auto auto) {
		//findById devuelve objeto "Opcional", entonces:
		//Lanzamos excepción con método .orElseThrow o mostramos null con .orElse
		return autoRepository.findById(auto.getDominio()).orElse(null);
	}	
	
	public List<Auto> buscarPorPropietario(Cliente propietario) {
		return autoRepository.findByPropietario(propietario);
	}
	
	@Transactional(readOnly = true)
	public List<Auto> buscarPorEstado(Auto auto) {
		//findById devuelve objeto "Opcional", entonces:
		//Lanzamos excepción con método .orElseThrow o mostramos null con .orElse
		return autoRepository.findByEstado(auto.getEstado());
	}	
	
	
	
	@Transactional
	public void eliminarAuto(Auto auto) {
		autoRepository.delete(auto);
	}


	// Sacar comentario cuando Entity Inspeccion este mapeada !!!  VER AutoRepository
//	public List<Auto> autosPorEstado(
//			@Param("estado") String estado){
//		return (List<Auto>) autoRepository.autosPorEstado(estado);
//	}
	
	
	
}
