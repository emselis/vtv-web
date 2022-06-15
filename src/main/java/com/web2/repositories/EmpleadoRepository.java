package com.web2.repositories;

import com.web2.entities.Empleado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("empleadoRepository")
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

}
