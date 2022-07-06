package com.web2.entities;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;


@Data
@Entity
@Table(name = "marcas")
public class Marca implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMarca;
	
	private String marca;

	public Marca() {}
	
	public Marca(int idMarca, String marca) {
		super();
		this.idMarca = idMarca;
		this.marca = marca;
	}

	
	
	
}

//	@OneToMany(mappedBy = "marca")
//	private List<Modelo> modelos;
	
