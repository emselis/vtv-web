package com.web2.entities;


import java.util.List;

import javax.persistence.*;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(referencedColumnName = "documento")
public class Cliente extends Persona{
	
	private static final long serialVersionUID = 1L;

	private String exento;
	
//	@OneToMany(mappedBy = "propietario")
//	private List<Auto> autos;
	
}
