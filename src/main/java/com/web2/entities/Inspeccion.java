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
	
	
	public Inspeccion() {}
	
	private String observaciones;
	private String medibles = "NO PASO";
	private String visuales = "NO PASO";
	private String estado = "SIN VERIFICAR";
	public Inspeccion(long idInspeccion, LocalDate fecha, Empleado inspector, Auto auto, String observaciones,
			String medibles, String visuales, String estado) {
		super();
		this.idInspeccion = idInspeccion;
		this.fecha = fecha;
		this.inspector = inspector;
		this.auto = auto;
		this.observaciones = observaciones;
		this.medibles = medibles;
		this.visuales = visuales;
		this.estado = estado;
	}
	
	
	
	
}
