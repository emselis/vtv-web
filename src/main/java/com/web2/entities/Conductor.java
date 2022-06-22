package com.web2.entities;


//import javax.persistence.*;

import lombok.*;

@Data
//@Entity
//@Table(name = "conductores")
//@PrimaryKeyJoinColumn(referencedColumnName = "documento")
public class Conductor extends Persona {

	private static final long serialVersionUID = 1L;

	private String esPropietario;

}
