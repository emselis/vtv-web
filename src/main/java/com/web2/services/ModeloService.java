package com.web2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web2.entities.Modelo;
import com.web2.repositories.ModeloRepository;

@Service("modeloService")
public class ModeloService{

	@Autowired
	@Qualifier("modeloRepository")
	private ModeloRepository modeloRepository;
	
	@Transactional(readOnly = true)
	public List<Modelo> listarModelos() {
		// Cast -> List xq findAll devuelve Object
		return (List<Modelo>) modeloRepository.findAll();
	}
	
//	public List<Modelo> modeloPorMarca(@Param("idMarca") int idMarca){
//		return (List<Modelo>) modeloRepository.listarPorMarca(idMarca);
//	}
	
}









