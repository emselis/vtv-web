package com.web2.entities;
//
//import javax.persistence.*;
//
import lombok.Data;
//import lombok.EqualsAndHashCode;
//
@Data
//@EqualsAndHashCode(callSuper=true)
//@Entity
//@Table(name = "propietarios")
////@PrimaryKeyJoinColumn(referencedColumnName = "documento")
public class Propietario extends Persona {
//	
//	private static final long serialVersionUID = 1L;
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPropietario;
//
//	private String exento;
//	
//	@OneToOne
//	@JoinColumn(name="documento")
//	private Persona persona;
//	
}
//
//
