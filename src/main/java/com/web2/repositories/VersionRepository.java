package com.web2.repositories;

import com.web2.entities.Modelo;
import com.web2.entities.Version;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("versionRepository")
public interface VersionRepository extends JpaRepository<Version, Integer> {

	@Query("SELECT v FROM Version v WHERE v.modelo = (:modelo)")
	public abstract List<Version> listarPorModelo(@Param("modelo") Modelo modelo);
}
