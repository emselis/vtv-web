package com.web2.repositories;

import com.web2.entities.Marca;
import com.web2.entities.Modelo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("modeloRepository")
public interface ModeloRepository extends JpaRepository<Modelo, Integer> {
	
	@Query("SELECT m FROM Modelo m WHERE m.marca = (:marca)")
	public abstract List<Modelo> listarPorMarca(@Param("marca") Marca marca);
	
}
