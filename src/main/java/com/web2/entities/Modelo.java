package com.web2.entities;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="modelos")
public class Modelo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idModelo;
	
	private String modelo;
		
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMarca")
 	private Marca marca;

    
    public Modelo() {}
    
    
	public Modelo(int idModelo, String modelo) {
		super();
		this.idModelo = idModelo;
		this.modelo = modelo;
	}
	
	
    
}
