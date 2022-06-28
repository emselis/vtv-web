package com.web2.repositories;

import com.web2.entities.Version;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("versionRepository")
public interface VersionRepository extends JpaRepository<Version, Integer> {

//	@Query("SELECT v FROM Version v WHERE v.idModelo = (:idModelo)")
//	public abstract List<Version> listarPorModelo(
//			@Param("idModelo") int idModelo);
}
