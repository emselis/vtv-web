package com.web2.entities;

import java.io.Serializable;

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
	

	@ManyToOne
	@JoinColumn(name = "idMarca")
	private Marca marca;
	
	@ManyToOne
	@JoinColumn(name = "idModelo")
	private Modelo modelo;
	
	@ManyToOne
	@JoinColumn(name = "idVersion")
	private Version version;
	
	@ManyToOne
	@JoinColumn(name = "propietario")
	private Cliente propietario;
	
	private String estado = "SIN VERIFICAR";
	
//	@ManyToOne()
//	private Cliente duenio;
	
//	@OneToMany(mappedBy = "idMarca")
//	private List<Marca> marcas;
//	
//	@OneToMany(mappedBy = "idModelo")
//	private List<Modelo> modelos;
//
//	@OneToMany(mappedBy = "idVersion")
//	private List<Version> versiones;

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