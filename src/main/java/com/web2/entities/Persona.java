package com.web2.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

import lombok.Data;

@Data
@Entity(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
//	@NotEmpty(message="Debe ingrese el documento")
//	@NotNull(message="Debe ingrese el documento") <--- Si es de tipo long
	@Size(min=7, max=8, message="El documento debe tener entre 7 y 8 dígitos.")
	private String documento;
	
//	@NotEmpty(message="Debe ingrese el nombre")
	@Size(min=2, max=30, message="El nombre debe tener entre 2 y 30 caracteres.")
	private String nombre;
	
//	@NotEmpty(message="Debe ingrese el apellido")
	@Size(min=2, max=30, message="El apellido debe tener entre 2 y 30 caracteres.")
	private String apellido;

	
}





//	protected Persona () {}
//	
//	protected Persona(long documento, String nombre, String apellido) {
//		super();
//		this.documento = documento;
//		this.nombre = nombre;
//		this.apellido = apellido;
//	}	

//	protected Persona(@NotEmpty(message = "Debe ingrese el documento") @Size(min = 7, max = 8) long documento,
//			@NotEmpty(message = "Debe ingrese el nombre") @Size(min = 2, max = 30) String nombre,
//			@NotEmpty(message = "Debe ingrese el apellido") @Size(min = 2, max = 30) String apellido) {
//		super();
//		this.documento = documento;
//		this.nombre = nombre;
//		this.apellido = apellido;
//	}