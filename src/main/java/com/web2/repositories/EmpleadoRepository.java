package com.web2.repositories;

import com.web2.entities.Empleado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("empleadoRepository")
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {

	// Usar Entity - No la tabla de la base
	@Query("SELECT e FROM Empleado e WHERE e.estado = (:estado) AND e.puesto = (:puesto)")
	public abstract List<Empleado> empleadosEstadoPuesto(
			@Param("estado") String estado, @Param("puesto") String puesto);
	
	@Modifying	// Al usar nativeQuery -> usar nombre de tabla - No Entity
	@Query(value="INSERT INTO empleados(documento, puesto, estado) VALUES(:documento, :puesto, :estado)", nativeQuery=true)
	public abstract void insertarEmpl(@Param("documento") String documento, @Param("puesto") String puesto, @Param("estado") String estado);
	
}
