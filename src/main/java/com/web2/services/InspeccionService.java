package com.web2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web2.entities.Inspeccion;
import com.web2.repositories.InspeccionRepository;

@Service("inspeccionService")
public class InspeccionService{

	@Autowired
	@Qualifier("inspeccionRepository")
	private InspeccionRepository inspeccionRepository;
	
	@Transactional(readOnly = true)
	public List<Inspeccion> listarInspecciones() {
		// Cast -> List xq findAll devuelve Object
		return (List<Inspeccion>) inspeccionRepository.findAll();
	}

	@Transactional
	public void guardarInspeccion(Inspeccion inspeccion) {
		inspeccionRepository.save(inspeccion);
	}

	@Transactional(readOnly = true)
	public Inspeccion encontrarInspeccion(Inspeccion inspeccion) {
		//findById devuelve objeto "Opcional", entonces:
		//Lanzamos excepción con método .orElseThrow o mostramos null con .orElse
		return inspeccionRepository.findById(inspeccion.getIdInspeccion()).orElse(null);
	}

}
