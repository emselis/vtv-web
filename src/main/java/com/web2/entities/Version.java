package com.web2.entities;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="version")
public class Version implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVersion;
	
	private String version;
	
	// NO declarar el atributo de FK
//	private int idMarca;
	
	
    @ManyToOne
    @JoinColumn(name = "idModelo")
 	private Modelo modelos;
	
	
}
