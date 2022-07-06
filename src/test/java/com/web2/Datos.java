package com.web2;

import java.time.LocalDate;
import java.util.*;

import com.web2.entities.Auto;
import com.web2.entities.Cliente;
import com.web2.entities.Marca;
import com.web2.entities.Modelo;
import com.web2.entities.Version;

public class Datos {
	
//		-------------- AUTOS -------------- 
	public static Auto auto1 = new Auto("AB123CD", new Marca(1,"CHEVROLET"),
			new Modelo(1, "AGILE"), new Version(1, "5P 1,4 LS"),
			new Cliente("29191555", "Eze", "Sel", "SI"),
			"SIN VERIFICAR", LocalDate.now());
	
	public static Auto auto2 = new Auto("AB124CD", new Marca(1,"CHEVROLET"),
			new Modelo(1, "CLASSIC"), new Version(1, "3P-1,4-LS-CITY-A/C"),
			new Cliente("29191555", "Eze", "Sel", "SI"),
			"APTO", LocalDate.now());
	
	public static final Auto auto3 = new Auto("CD111YZ", new Marca(2,"FIAT"),
			new Modelo(5, "CRONOS"), new Version(1, "'4P 1,8 HGT PACK"),
			new Cliente("15666777", "Pablo", "Torres", "SI"),
			"SIN VERIFICAR", LocalDate.now());

	public static final Auto auto4 = new Auto("EFG456", new Marca(2,"FIAT"),
			new Modelo(5, "CRONOS"), new Version(1, "'4P 1,8 HGT PACK"),
			new Cliente("3288299", "Cecilia", "Ro", "NO"),
			"APTO", LocalDate.now());
	
	public static final List<Auto> autosSegunEstado = new ArrayList<Auto>(Arrays.asList(auto1, auto3)); 
	public static final List<Auto> autosSegunDuenio = new ArrayList<Auto>(Arrays.asList(auto1, auto2)); 

	
//	-------------- Clientes --------------
	public static final Cliente duenio12 = new Cliente("29191555", "Eze", "Sel", "SI");
	public static final Cliente duenio3 = new Cliente("15666777", "Pablo", "Torres", "SI");
	public static final Cliente duenio4 = new Cliente("3288299", "Cecilia", "Ro", "NO");

	
//	-------------- MARCAS --------------
	public static List<String> marcas = Arrays.asList("nada", "CHEVROLET", "FIAT");
	public static List<String> modelos = Arrays.asList("nada", "AGILE", "CLASSIC", "CRONOS", "PALIO");
	public static List<String> versiones = Arrays.asList("nada", "1.4", "1.4 FULL", "1.6", "1.6 FULL", "1.8", "1.8 FULL", "1.5", "1.5 FULL");

}
