package com.web2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web2.entities.Marca;
import com.web2.repositories.MarcaRepository;

@Service("marcaService")
public class MarcaService{

	@Autowired
	@Qualifier("marcaRepository")
	private MarcaRepository marcaRepository;
	
	@Transactional(readOnly = true)
	public List<Marca> listarMarcas() {
		// Cast -> List xq findAll devuelve Object
		return (List<Marca>) marcaRepository.findAll();
	}


}
