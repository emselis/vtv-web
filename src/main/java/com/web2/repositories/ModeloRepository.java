package com.web2.repositories;

import com.web2.entities.Modelo;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("modeloRepository")
public interface ModeloRepository extends JpaRepository<Modelo, Integer> {

	// Sacar comentario cuando Entity Inspeccion este mapeada !!!
//	@Query("SELECT a FROM Auto a, Inspeccion i WHERE a.dominio = i.dominio AND i.estado = (:estado)")
//	public abstract List<Auto> autosPorEstado(
//			@Param("estado") String estado);
	
	
}
