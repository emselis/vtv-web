package com.web2.entities;

import javax.persistence.*;

import lombok.*;

@Data
@Entity
@Table(name = "empleados")
@PrimaryKeyJoinColumn(referencedColumnName = "documento")
public class Empleado extends Persona {
	
	private static final long serialVersionUID = 1L;
	
	private String puesto;
	
	private String estado;
	
	// private long idEmpleado;

}
