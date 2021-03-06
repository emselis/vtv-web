package com.web2.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.web2.entities.*;
import com.web2.enumeraciones.*;
import com.web2.services.*;

@Controller
@RequestMapping("/inspecciones")
public class InspeccionController {

	@Autowired
//	@Qualifier("inspeccionService")
	private InspeccionService inspeccionService;

	@Autowired
//	@Qualifier("empleadoService")
	private EmpleadoService empleadoService;

	@Autowired
	private AutoService autoService;
	
	
//	--------	Genera Estado de Inspección		--------
	public String generaEstadoInspeccion(Inspeccion inspeccion) {
		var tiposDeEstados = EstadosInspeccion.values();
		String estado;
		if(inspeccion.getVisuales().equals(inspeccion.getMedibles())) {
			if(inspeccion.getVisuales().equals("PASO")) {
				estado= tiposDeEstados[0].name();
			}else {
				estado= tiposDeEstados[2].name();
			}
		}else {
			estado= tiposDeEstados[1].name();
		}
		return estado;
	}
	
	

	@GetMapping("/iniciaInspeccion")
	public String muestraInspectores(Model modelo, Inspeccion inspeccion) {

		var empleados = empleadoService.empleadosEstadoPuesto("ALTA", PuestosEmpleados.valueOf("INSPECTOR"));
		modelo.addAttribute("empleados", empleados);
		return "inspecciones/asignaInspector";
	}

	@PostMapping("/asignaInspector")
	public String asignaInspector(Model modelo, Inspeccion inspeccion) {
		var autos = autoService.listarAutos();
		modelo.addAttribute("autos", autos);

		return "inspecciones/asignaAuto";
	}

	@PostMapping("/asignaAuto")
	public String asignaPropietario(Model modelo, Inspeccion inspeccion) {

		return "inspecciones/cargaInspeccion";
	}

	@PostMapping("/guardarInspeccion")
	public String guardarInspeccion(Model modelo, Inspeccion inspeccion) { 
		
//		String dominio = inspeccion.getAuto().getDominio();
//		if(inspeccionService.encontrarInspeccion(dominio) != null) {
//			System.out.println("Existe Insp. -> Verificar si está completa.");
//			
////		------------	TODO	------------
//			// si verificacion existe con fecha de hoy -> editar de condicional a apto
////				return "inspecciones/resumenInspeccion";
//
//			// si verificacion existe con fecha mayor a hoy -> pasar a rechazada
////				return "inspecciones/mostrarInspeccion";
//		}
		
//		if(inspeccion.getAuto().getUltimaVerificacion() =! null || ) {
//			
//		}
		
		LocalDate fechaHoy = LocalDate.now();
		inspeccion.setFecha(fechaHoy);
		System.out.println(inspeccion);

		inspeccion.setEstado(generaEstadoInspeccion(inspeccion));
		
//		if(inspeccion.getVisuales().equals(inspeccion.getMedibles())) {
//			System.out.println(inspeccion.getVisuales());
//			System.out.println(inspeccion.getMedibles());
//			if(inspeccion.getVisuales().equals("PASO")) {
//				inspeccion.setEstado("APTO");
//			}else {
//				inspeccion.setEstado("RECHAZADO");
//			}
//		}else {
//			inspeccion.setEstado("CONDICIONAL");
//		}
		
		try {
		inspeccionService.guardarInspeccion(inspeccion);
		} catch (Exception e) {
			System.out.println("Eror al guardar la Inspección: " + e);
			
		}
		
		return "inspecciones/resumenInspeccion";
	}

//	@PostMapping("/guardarControles")
//	public String guardarControlesMV(Model modelo, Inspeccion inspeccion) {
//		String visuales = inspeccion.getVisuales();
//		String 
//		inspeccion = inspeccionService.encontrarInspeccion(inspeccion.getIdInspeccion());
//		
//		return "inspecciones/resumenInspeccion";
//	}
	
	
	@GetMapping("/editarInspeccion") // <--- pasamos query parameters (ver HTML)
	public String editarInspeccion(Inspeccion inspeccion, Model model) {
		
		inspeccion = inspeccionService.encontrarInspeccion(inspeccion.getIdInspeccion());
		model.addAttribute("inspeccion", inspeccion);
		return "persona/cargaInspeccion";
	}

	@GetMapping("/infoClientes")
	public String listarC(Model model) {
		var inspecciones = inspeccionService.listarInspecciones();
		model.addAttribute("inspecciones", inspecciones);
		return "informes/infoInspecciones";
	}

}
