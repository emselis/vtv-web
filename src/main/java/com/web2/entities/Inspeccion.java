package com.web2.entities;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "inspecciones")
public class Inspeccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idInspeccion;
	
	private LocalDate fecha;
	
	@ManyToOne
	@JoinColumn(name="empleado")
	private Empleado inspector;

	@ManyToOne
	@JoinColumn(name="dominio")
	private Auto auto;
	
	private String observaciones;
	private String medibles = "NO PASO";
	private String visuales = "NO PASO";
	private String estado = "SIN VERIFICAR";
	
}
