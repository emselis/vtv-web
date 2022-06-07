package com.web2.entities;

public class Persona {
	
	protected String numeroDocumeto;
	protected String nombreRazon;
	protected String apellido;
	
	public Persona() {
		
	}
	
	public Persona(String numeroDocumeto, String nombreRazon, String apellido) {  //String tipoDocumento, 
		this.numeroDocumeto = numeroDocumeto;
		this.nombreRazon = nombreRazon;
		this.apellido = apellido;
	}

	
	public String getNumeroDocumeto() {
		return this.numeroDocumeto;
	}
	public void setNumeroDocumeto(String numeroDocumeto) {
		this.numeroDocumeto = numeroDocumeto;
	}
	public String getNombreRazon() {
		return this.nombreRazon;
	}
	public void setNombreRazon(String nombreRazon) {
		this.nombreRazon = nombreRazon;
	}
	public String getApellido() {
		return this.apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Persona [Numero de Documeto= ");
		builder.append(numeroDocumeto);
		builder.append(", Nombre o Razon= ");
		builder.append(nombreRazon);
		builder.append(", Apellido= ");
		builder.append(apellido);
		builder.append("]");
		return builder.toString();
	}
}
