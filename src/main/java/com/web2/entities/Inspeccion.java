package com.web2.entities;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Inspeccion {

	private long idInspeccion;
	private LocalDateTime fecha;
	private int inspector;
	private int conductor;
	private int propietario;
	private String estado;
	private String observaciones;
	
	public Inspeccion() {}

	public Inspeccion(long idInspeccion, LocalDateTime fecha, int inspector, int conductor, int propietario,
			String estado, String observaciones) {
		super();
		this.idInspeccion = idInspeccion;
		this.fecha = fecha;
		this.inspector = inspector;
		this.conductor = conductor;
		this.propietario = propietario;
		this.estado = estado;
		this.observaciones = observaciones;
	}
	
	
}
