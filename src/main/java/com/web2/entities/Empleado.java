package com.web2.entities;

public class Empleado extends Persona {
	
	private String idEmpleado;
	private String puesto;
	
	public Empleado(String numeroDocumeto, String nombreRazon, String apellido,
			String idEmpleado, String puesto) {
		super(numeroDocumeto, nombreRazon, apellido);
		this.idEmpleado = idEmpleado;
		this.puesto = puesto;
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	// Asignar al iniciar tomando ID desde DB - BORRAR METODO
	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Empleado [idEmpleado= ");
		builder.append(idEmpleado);
		builder.append(", Puesto= ");
		builder.append(puesto);
		builder.append(" , ");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
