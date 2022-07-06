package com.web2.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(referencedColumnName = "documento")
public class Cliente extends Persona{
	
	private static final long serialVersionUID = 1L;

	private String exento;

	public Cliente() {}
	
	public Cliente(@Size(min = 7, max = 8, message = "El documento debe tener entre 7 y 8 dígitos.") String documento,
			@Size(min = 2, max = 30, message = "El nombre debe tener entre 2 y 30 caracteres.") String nombre,
			@Size(min = 2, max = 30, message = "El apellido debe tener entre 2 y 30 caracteres.") String apellido,
			String exento) {
		super(documento, nombre, apellido);
		this.exento = exento;
	}


	
}

//	@OneToMany(mappedBy = "propietario")
//	private List<Auto> autos;
	
