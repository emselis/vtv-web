package com.web2.entities;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(referencedColumnName = "documento")
public class Cliente extends Persona{
	
	private static final long serialVersionUID = 1L;

	private String exento;
	
}
