package com.web2.repositories;

import com.web2.entities.Inspeccion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("inspeccionRepository")
public interface InspeccionRepository extends JpaRepository<Inspeccion, Long>{

	
}
