package com.web2.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@NotEmpty(message="Debe ingrese el documento")
	private long documento;
	
	@NotEmpty(message="Debe ingrese el nombre")
	private String nombre;
	
	@NotEmpty(message="Debe ingrese el apellido")
	private String apellido;
	
}




