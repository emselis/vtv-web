package com.web2.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "inspecciones")
public class Inspeccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idInspeccion;
	
	private LocalDateTime fecha;
	private Empleado inspector;
	private String conductor;
	private String propietario;
	private String estado;
	private String observaciones;
	
}
