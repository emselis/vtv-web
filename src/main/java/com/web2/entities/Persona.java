package com.web2.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Data;

@Data
@Entity(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull(message="Debe ingrese el documento")
//	@Size(min=7, max=8)
	protected long documento;
	
	@NotEmpty(message="Debe ingrese el nombre")
//	@Size(min=2, max=30)
	protected String nombre;
	
	@NotEmpty(message="Debe ingrese el apellido")
//	@Size(min=2, max=30)
	protected String apellido;

	protected Persona () {}
	
	protected Persona(long documento, String nombre, String apellido) {
		super();
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
	}	
	
}





//	protected Persona(@NotEmpty(message = "Debe ingrese el documento") @Size(min = 7, max = 8) long documento,
//			@NotEmpty(message = "Debe ingrese el nombre") @Size(min = 2, max = 30) String nombre,
//			@NotEmpty(message = "Debe ingrese el apellido") @Size(min = 2, max = 30) String apellido) {
//		super();
//		this.documento = documento;
//		this.nombre = nombre;
//		this.apellido = apellido;
//	}