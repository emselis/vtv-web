package com.web2.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.*;

@Data
@Entity(name = "empleados")
@PrimaryKeyJoinColumn(referencedColumnName = "documento")
public class Empleado extends Persona {
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Debe ingrese el puesto")
	private String puesto;
	
}
