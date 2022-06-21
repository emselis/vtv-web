package com.web2.entities;
//
//import javax.persistence.*;
//
import lombok.*;
//
@Data
//@EqualsAndHashCode(callSuper=true)
//@Entity
//@Table(name = "conductores")
////@PrimaryKeyJoinColumn(referencedColumnName = "documento")
public class Conductor extends Persona {
//	
//	private static final long serialVersionUID = 1L;
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long idConductor;
//
	private String esPropietario;
//	
//	@OneToOne
//	@JoinColumn(name="documento")
//	private Persona persona;
}
