package com.web2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web2.entities.Empleado;
import com.web2.enumeraciones.PuestosEmpleados;
import com.web2.repositories.EmpleadoRepository;

@Service("empleadoService")
public class EmpleadoService {

	@Autowired
	@Qualifier("empleadoRepository")
	private EmpleadoRepository empleadoRepository;
	
	public List<Empleado> listarEmpleados() {
		// Cast -> List xq findAll devuelve Object
		return (List<Empleado>) empleadoRepository.findAll();
	}

	public void guardarEmpleado(Empleado empleado) {
		empleadoRepository.save(empleado);
	}

	@Transactional
	public void insertarEmpleado(Empleado empleado) {
		var documento = empleado.getDocumento();
		var estado = empleado.getEstado();
		var puesto = empleado.getPuesto();
		empleadoRepository.insertarEmpl(documento, puesto, estado);
	}
	
	public void eliminarEmpleado(Empleado empleado) {
		empleadoRepository.delete(empleado);
	}

	public Empleado encontrarEmpleado(Empleado empleado) {
		//findById devuelve objeto "Opcional", entonces:
		//Lanzamos excepción con método .orElseThrow o mostramos null con .orElse
		return empleadoRepository.findById(empleado.getDocumento()).orElse(null);
	}	

	public List<Empleado> empleadosEstadoPuesto(
			@Param("estado") String estado, @Param("puesto") PuestosEmpleados puesto){
		return (List<Empleado>) empleadoRepository.findByEstadoPuesto(estado, puesto);
	}
	
	
	
}
