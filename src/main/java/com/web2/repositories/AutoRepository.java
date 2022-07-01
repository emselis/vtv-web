package com.web2.repositories;

import com.web2.entities.Auto;
import com.web2.entities.Cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("autoRepository")
public interface AutoRepository extends JpaRepository<Auto, String> {

//	@Query("SELECT a FROM Auto a JOIN a.propietario p WHERE p = (:propietario)")
	@Query("SELECT a FROM Auto a WHERE a.propietario = (:propietario)")
	public abstract List<Auto> findByPropietario(@Param("propietario") Cliente propietario);
	
	
	@Query("SELECT a FROM Auto a WHERE a.estado = (:estado)")
	public abstract List<Auto> findByEstado(@Param("estado") String estado);
	
	
// 			Sacar comentario cuando Entity Inspeccion este mapeada !!!
//	@Query("SELECT a FROM Auto a, Inspeccion i WHERE a.dominio = i.dominio AND i.estado = (:estado)")
//	public abstract List<Auto> autosPorEstado(
//			@Param("estado") String estado);
	
	
}
