package com.web2.repositories;

import com.web2.entities.Inspeccion;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("inspeccionRepository")
public interface InspeccionRepository extends JpaRepository<Inspeccion, Long>{

	@Query("SELECT i FROM Inspeccion i JOIN i.auto a WHERE a.dominio = (:dominio)")
	public abstract Inspeccion findByDominio(
			@Param("dominio") String dominio);
}
