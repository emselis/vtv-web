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
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idModelo")
 	private Modelo modelo;

    
    public Version () {}
    
	public Version(int idVersion, String version) {
		super();
		this.idVersion = idVersion;
		this.version = version;
	}
	
    
    
	
}
