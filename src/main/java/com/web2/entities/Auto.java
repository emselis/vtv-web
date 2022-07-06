package com.web2.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Data;

@Data
@Entity
@Table(name = "automoviles")
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
	
	private LocalDate ultimaVerificacion;

	public Auto() {}
	
	public Auto(@Size(min = 6, max = 7, message = "El dominio debe tener entre 6 y 7 dígitos.") String dominio,
			Marca marca, Modelo modelo, Version version, Cliente propietario, String estado,
			LocalDate ultimaVerificacion) {
		super();
		this.dominio = dominio;
		this.marca = marca;
		this.modelo = modelo;
		this.version = version;
		this.propietario = propietario;
		this.estado = estado;
		this.ultimaVerificacion = ultimaVerificacion;
	}
	
	

	
	
	
}


//	@OneToMany(mappedBy = "idMarca")
//	private List<Marca> marcas;
//	
//	@OneToMany(mappedBy = "idModelo")
//	private List<Modelo> modelos;
//
//	@OneToMany(mappedBy = "idVersion")
//	private List<Version> versiones;


