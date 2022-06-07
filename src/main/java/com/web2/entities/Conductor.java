package com.web2.entities;

public class Conductor extends Persona{
	
	private boolean duenio;
	// private String email;

	public Conductor(String numeroDocumeto, String nombreRazon, String apellido,
			boolean duenio) {
		super(numeroDocumeto, nombreRazon, apellido);
		this.duenio = duenio;
	}

	public boolean isDuenio() {
		return duenio;
	}

	public void setDuenio(boolean duenio) {
		this.duenio = duenio;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Conductor [Duenio= ");
		builder.append(duenio);
		builder.append(" , ");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}


	
}
