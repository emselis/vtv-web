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
	
}
