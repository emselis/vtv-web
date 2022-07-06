package com.web2.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.web2.enumeraciones.PuestosEmpleados;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "empleados")
@PrimaryKeyJoinColumn(referencedColumnName = "documento")
public class Empleado extends Persona {
	
	private static final long serialVersionUID = 1L;
	
	private PuestosEmpleados puesto;
	
	private String estado;

	public Empleado() {}
	
	public Empleado(@Size(min = 7, max = 8, message = "El documento debe tener entre 7 y 8 dígitos.") String documento,
			@Size(min = 2, max = 30, message = "El nombre debe tener entre 2 y 30 caracteres.") String nombre,
			@Size(min = 2, max = 30, message = "El apellido debe tener entre 2 y 30 caracteres.") String apellido, PuestosEmpleados puesto, String estado) {
		super(documento, nombre, apellido);
		this.puesto = puesto;
		this.estado = estado;
	}
	
	// private long idEmpleado;
	
	
	

}
