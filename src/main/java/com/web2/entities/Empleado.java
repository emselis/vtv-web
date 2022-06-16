package com.web2.entities;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Data
@Entity
@Table(name = "empleados")
@PrimaryKeyJoinColumn(referencedColumnName = "documento")
public class Empleado extends Persona {
	
	private static final long serialVersionUID = 1L;
	
//	@NotEmpty(message="Debe ingrese el puesto")
	private String puesto;

	public Empleado() {}
	
	public Empleado(long documento, String nombre, String apellido) {
		super(documento, nombre, apellido);
		this.puesto=puesto;
	}	

}
