package com.web2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web2.entities.Empleado;
import com.web2.repositories.EmpleadoRepository;

@Service("empleadoService")
public class EmpleadoService {

	@Autowired
	@Qualifier("empleadoRepository")
	private EmpleadoRepository empleadoRepository;
	
	@Transactional(readOnly = true)
	public List<Empleado> listarEmpleados() {
		// Cast -> List xq findAll devuelve Object
		return (List<Empleado>) empleadoRepository.findAll();
	}

	@Transactional
	public void guardarEmpleado(Empleado empleado) {
		empleadoRepository.save(empleado);
	}

	@Transactional
	public void eliminarEmpleado(Empleado empleado) {
		empleadoRepository.delete(empleado);
	}

	@Transactional(readOnly = true)
	public Empleado encontrarEmpleado(Empleado empleado) {
		//findById devuelve objeto "Opcional", entonces:
		//Lanzamos excepción con método .orElseThrow o mostramos null con .orElse
		return empleadoRepository.findById(empleado.getDocumento()).orElse(null);
	}	

}
