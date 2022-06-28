package com.web2.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Data;

@Data
@Entity(name = "automoviles")
public class Auto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Size(min=6, max=7, message="El dominio debe tener entre 6 y 7 dígitos.")
	private String dominio;
	
//	@NotEmpty(message="Debe ingrese el nombre")
//	@Size(min=2, max=30, message="El nombre debe tener entre 2 y 30 caracteres.")
	private String propietario;
	
	private int idMarca;
	private int idModelo;
	private int idVersion;
//	
//	private Marca marca;
//	private Modelo modelo;
//	private Version version;
	
	@OneToMany(mappedBy = "idMarca")
	private List<Marca> marcas;
	
	@OneToMany(mappedBy = "idModelo")
	private List<Modelo> modelos;

	@OneToMany(mappedBy = "idVersion")
	private List<Version> versiones;

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