package com.web2.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web2.entities.Auto;
import com.web2.services.AutoService;
import com.web2.services.MarcaService;
import com.web2.services.ModeloService;
import com.web2.services.VersionService;

@Controller
@RequestMapping("/auto")
public class AutoController {

	@Autowired
	@Qualifier("autoService")
	private AutoService autoService;

	@Autowired
	@Qualifier("marcaService")
	private MarcaService marcaService;
	
	@Autowired
	@Qualifier("modeloService")
	private ModeloService modeloService;
	
	@Autowired
	@Qualifier("versionService")
	private VersionService versionService;
	
	
	private boolean validarDominio(String patente) {

		boolean control = true;
		int cantidad = patente.length();
		patente = patente.toUpperCase();

		switch (cantidad) {
		// Patente vieja
		case 6:
			System.out.println("");
			System.out.println(patente);
			System.out.println(patente.matches("[A-Z][A-Z][A-Z][0-9][0-9][0-9]"));
			if(!patente.matches("[A-Z][A-Z][A-Z][0-9][0-9][0-9]")){
				control= false;
			}
			break;

		// Patente nueva
		case 7:
			System.out.println("");
			System.out.println(patente);
			System.out.println(patente.matches("[A-Z][A-Z][0-9][0-9][0-9][A-Z][A-Z]"));
			if(!patente.matches("[A-Z][A-Z][0-9][0-9][0-9][A-Z][A-Z]")) {
				control = false;
			}
			break;

		// Fuera de Rango
		default:
			System.out.println("");
			System.out.println(patente);
			System.out.println("Fuera del rango. Ingrese 6 o 7 caracteres.");
			control = false;
			break;
		}

		return control;
	}

	private Model agregarMarModVer(Model modelo) {
		
		var marcas = marcaService.listarMarcas();
		modelo.addAttribute("marcas", marcas);
		var modelos = modeloService.listarModelos();
		modelo.addAttribute("modelos", modelos);
		var versiones = versionService.listarVersiones();
		modelo.addAttribute("versiones", versiones);
		return modelo;
	}
	
	
	@GetMapping("/cargaAuto")
	public String agregaAuto(Model modelo) {
		
		modelo.addAttribute("auto", new Auto());
		modelo = agregarMarModVer(modelo);

		return "auto/cargaAuto";
	}

	@PostMapping("/guardarAuto")
	public String guardarAuto(@Valid @ModelAttribute Auto auto, Errors errores, Model modelo, BindingResult resultado,
			RedirectAttributes atributos) {

		if (errores.hasErrors()) {
			modelo.addAttribute("auto", auto);
			System.out.println("Errores al cargar el auto");
			
			modelo = agregarMarModVer(modelo);

			
			return "auto/cargaAuto";
		}
		if(!validarDominio(auto.getDominio())) {
			FieldError error = new FieldError("auto", "dominio", "Dominio con formato incorrecto");
			resultado.addError(error);
			modelo.addAttribute("auto", auto);
			System.out.println("Errores al cargar el auto");
			modelo = agregarMarModVer(modelo);
			
			return "auto/cargaAuto";
		}

		
		
//		autoService.guardarAuto(auto);
		System.out.println("Auto guardado OK");
		System.out.println(auto.getDominio());
		System.out.println(auto.getIdMarca());
		System.out.println(auto.getIdModelo());
		System.out.println(auto.getIdVersion());
//		System.out.println(auto.toString());

		atributos.addFlashAttribute("success", "Auto creado correctamente.");
		return "informes/muestraAuto";
	}

	
	
	
	@GetMapping("/editarAuto") // <--- Acá pasamos query parameters (ver HTML)
	public String editarAuto(Auto auto, Model model) {
		auto = autoService.encontrarAuto(auto);
		model.addAttribute("auto", auto);
		return "auto/editarAuto";
	}

	@PostMapping("/editarAuto")
	public String editAuto(@Valid @ModelAttribute Auto auto, Errors errores, Model modelo) {
		// Sin verificacar estos errores pincha html
		if (errores.hasErrors()) {
			modelo.addAttribute("auto", auto);
			System.out.println("Errores al cargar el auto");
			return "auto/editarAuto";
		}
		autoService.guardarAuto(auto);
		System.out.println("Auto guardado OK");
		return "/informes/muestraAuto";
	}

	@GetMapping("/eliminarAuto/{dominio}") // <--- Así pasamos el IdPersona en el path
	public String eliminarAuto(Auto auto) {
		autoService.eliminarAuto(auto);
		return "redirect:/listarAutos";
	}

	@GetMapping("/listarAutos")
	public String listarE(Model model) {
		var auto = autoService.listarAutos();
		model.addAttribute("auto", auto);
		return "informes/listarAutos";
	}

	@GetMapping("/buscarAuto")
	public String buscarE(Model modelo) {
		modelo.addAttribute("auto", new Auto());
		return "auto/buscarAuto";
	}

	@GetMapping("/encontrarAuto")
	public String encontrarE(@RequestParam String dominio, Model modelo, Auto auto, BindingResult resultado) {

//		Agregar validacionDominio()
//		String patente = dominio;
//		System.out.println("DNI solo numeros: " + dni.matches("[0-9]+"));
//		if(!dni.matches("[0-9]+")) {
//			FieldError error = new FieldError("auto", "documento", "El documento solo debe contener numeros.");
//			resultado.addError(error);
//		}
//		if(dni.length()>8 || dni.length()<7) {
//				FieldError error = new FieldError("auto", "documento", "El documento debe tener entre 7 y 8 caracteres.");
//				resultado.addError(error);
//		}		
//		if(resultado.hasErrors()) {
//			modelo.addAttribute("auto", auto);
//			System.out.println("Errores al cargar el empleado");
//			return "persona/buscarEmpleado";
//		}		

		auto = autoService.encontrarAuto(auto);
		modelo.addAttribute("auto", auto);

		return "informes/muestraAuto";
	}

}

//var marcas = marcaService.listarMarcas();
//for(Marca n: marcas) {
//	System.out.println(n.toString());
//}
//var modelos = modeloService.listarModelos();
//for(Modelo m: modelos) {
//	System.out.println(m.toString());
//}
//var versiones = versionService.listarVersiones();
//for(Version v: versiones) {
//	System.out.println(v.toString());
//}

//@PostMapping("/guardarEmpleado")
//public String guardarEmpleado(@Valid Empleado empleado, Errors errores) {
//	System.out.println("Solo numeros" + empleado.getDocumento().matches("[0-9]+"));
//	if(errores.hasErrors()) {
//		return "persona/cargaEmpleado";
//	}
//	empleadoService.guardarEmpleado(empleado);
//	return "redirect:/listarEmpleados";
//}



//var marcas = marcaService.listarMarcas();
//modelo.addAttribute("marcas", marcas);
//var modelos = modeloService.listarModelos();
//modelo.addAttribute("modelos", modelos);
//var versiones = versionService.listarVersiones();
//modelo.addAttribute("versiones", versiones);


//for(Marca n: marcas) {
//System.out.println(n.toString());
//}
//for(Modelo m: modelos) {
//System.out.println(m.toString());
//}
//for(Version v: versiones) {
//System.out.println(v.toString());
//}