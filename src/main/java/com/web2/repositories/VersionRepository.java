package com.web2.repositories;

import com.web2.entities.Version;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("versionRepository")
public interface VersionRepository extends JpaRepository<Version, Integer> {

	// Usar Entity - No la tabla de la base
//	@Query("SELECT v, m, n FROM Version v, Modelo m, Marca n WHERE v.idModelo = m.idModelo AND m.idMarca = n.idMarca")
//	public abstract List<Version> versionesMarcas();
	
}
