package com.web2.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "persona")
public class Persona implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersona;
	
	@NotEmpty(message="Debe ingrese el nombre")
	protected String nombre;
	
	@NotEmpty(message="Debe ingrese el apellido")
	protected String apellido;
	
	@NotEmpty(message="Debe ingrese el documento")
	protected String documento;


}
