package com.web2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web2.entities.Version;
import com.web2.repositories.VersionRepository;

@Service("versionService")
public class VersionService{

	@Autowired
	@Qualifier("versionRepository")
	private VersionRepository versionRepository;
	
	@Transactional(readOnly = true)
	public List<Version> listarVersiones() {
		// Cast -> List xq findAll devuelve Object
		return (List<Version>) versionRepository.findAll();
	}
	
//	public List<Version> versionesMarcas(){
//		return (List<Version>) versionRepository.versionesMarcas();
//	}
	
	
}
