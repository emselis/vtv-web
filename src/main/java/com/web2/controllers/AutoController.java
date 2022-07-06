package com.web2.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import com.web2.entities.Auto;
import com.web2.entities.Cliente;
import com.web2.services.AutoService;
import com.web2.services.ClienteService;
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

	@Autowired
	private ClienteService clienteService;

	public boolean validarDominio(String patente) {

		boolean control = true;
		int cantidad = patente.length();
		patente = patente.toUpperCase();

		switch (cantidad) {
		// Patente vieja
		case 6:
			System.out.println("");
			System.out.println(patente);
			System.out.println(patente.matches("[A-Z][A-Z][A-Z][0-9][0-9][0-9]"));
			if (!patente.matches("[A-Z][A-Z][A-Z][0-9][0-9][0-9]")) {
				control = false;
			}
			break;

		// Patente nueva
		case 7:
			System.out.println("");
			System.out.println(patente);
			System.out.println(patente.matches("[A-Z][A-Z][0-9][0-9][0-9][A-Z][A-Z]"));
			if (!patente.matches("[A-Z][A-Z][0-9][0-9][0-9][A-Z][A-Z]")) {
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

	@GetMapping("/cargaAuto")
	public String mDominio(Model modelo, Auto auto) {

		modelo.addAttribute("titulo", "Carga de Vehiculo");
		modelo.addAttribute("auto", auto);
		return "auto/cargaDominio";
	}

	@PostMapping("/cargaAuto")
	public String lDominio(@Valid @ModelAttribute Auto auto, Errors errores, Model modelo, BindingResult resultado) {

		if (errores.hasErrors()) {
			modelo.addAttribute("titulo", "Carga de Vehiculo");
			modelo.addAttribute("auto", auto);
			System.out.println("Errores al cargar el auto 1");
			return "auto/cargaDominio";
		}
		if (!validarDominio(auto.getDominio())) {
			FieldError error = new FieldError("auto", "dominio", "Dominio con formato incorrecto");
			resultado.addError(error);
			modelo.addAttribute("titulo", "Carga de Vehiculo");
			modelo.addAttribute("auto", auto);
			System.out.println("Errores al cargar el auto 2");
			return "auto/cargaDominio";
		}
		if (autoService.buscarPorDominio(auto) != null) {
			modelo.addAttribute("titulo", "Vehiculo Registrado");
			FieldError error = new FieldError("auto", "dominio", "Este dominio se encunetra cargado");
			resultado.addError(error);
			auto = autoService.buscarPorDominio(auto);
			modelo.addAttribute("auto", auto);
			error = new FieldError("auto", "propietario", "Cambio de propietario (opcional)");
			resultado.addError(error);

			var propietarios = clienteService.listarClientes();
			modelo.addAttribute("propietarios", propietarios);
			return "/auto/cargaPropietario";
		}

		String patente = auto.getDominio().toUpperCase();
		auto.setDominio(patente);

		modelo.addAttribute("titulo", "Carga de Vehiculo");
		modelo.addAttribute("auto", auto);
		System.out.println(auto);
		var marcas = marcaService.listarMarcas();
		modelo.addAttribute("marcas", marcas);
		return "/auto/cargaMarca";
	}

	@PostMapping("/cargaMarca")
	public String lMarca(Model modelo, Auto auto) {
		modelo.addAttribute("titulo", "Carga de Vehiculo");
		modelo.addAttribute("auto", auto);
		System.out.println(auto);
		var modelos = modeloService.modeloPorMarca(auto.getMarca());
		modelo.addAttribute("modelos", modelos);
		return "/auto/cargaModelo";
	}

	@PostMapping("/cargaModelo")
	public String mModelo(Model modelo, Auto auto) {
		modelo.addAttribute("titulo", "Carga de Vehiculo");
		modelo.addAttribute("auto", auto);
		System.out.println(auto);
		var versiones = versionService.versionPorModelo(auto.getModelo()); // modelosPorMarca(auto.getMarca().getIdMarca());
		modelo.addAttribute("versiones", versiones);
		return "auto/cargaVersion";
	}

	@PostMapping("/cargaVersion")
	public String lModelo(Model modelo, Auto auto) {
		modelo.addAttribute("titulo", "Carga de Vehiculo");
		modelo.addAttribute("auto", auto);
		System.out.println(auto);
		var propietarios = clienteService.listarClientes();
		modelo.addAttribute("propietarios", propietarios);
		return "/auto/cargaPropietario";
	}

	@PostMapping("/cargaPropietario")
	public String mPropietario(Model modelo, Auto auto) {
		modelo.addAttribute("auto", auto);
		System.out.println(auto);
		System.out.println(auto.getPropietario().getNombre() + " " + auto.getPropietario().getApellido());

		autoService.guardarAuto(auto);
		System.out.println("Auto guardado OK");
		return "/informes/muestraAuto";
	}

// -----------------BUSCAR--DOMINIO---------------------------------------------------------	

	@GetMapping("/buscaDominio")
	public String buscarDominio(Model modelo, Auto auto) {

		modelo.addAttribute("titulo", "Buscar Dominio");
		modelo.addAttribute("auto", auto);
		return "auto/buscaDominio";
	}

	@PostMapping("/buscaDominio")
	public String muestraDominio(@Valid @ModelAttribute Auto auto, Errors errores, Model modelo,
			BindingResult resultado) {

		modelo.addAttribute("titulo", "Buscar Dominio");
		modelo.addAttribute("auto", auto);

		if (errores.hasErrors()) {
			System.out.println("Errores al buscar el auto 1");
			return "auto/buscaDominio";
		}
		if (!validarDominio(auto.getDominio())) {
			FieldError error = new FieldError("auto", "dominio", "Dominio con formato incorrecto");
			resultado.addError(error);
			System.out.println("Errores al buscar el auto 2");
			return "auto/buscaDominio";
		}
		auto = autoService.buscarPorDominio(auto);
		modelo.addAttribute("auto", auto);
		return "/informes/muestraAuto";
	}

// -----------------BUSCAR--PROPIETARIO---------------------------------------------------------	

	@GetMapping("/buscaPropietario")
	public String buscarPropietario(Model modelo, Auto auto) {

		modelo.addAttribute("titulo", "Buscar Propietario");
		modelo.addAttribute("auto", auto);

//		var propietarios = clienteService.listarClientes();
//		modelo.addAttribute("propietarios", propietarios);
		return "auto/buscaPropietario";
	}

	@PostMapping("/buscaPropietario")
	public String muestraPropietario (Auto auto, Errors errores, Model modelo, BindingResult resultado)
	{
		modelo.addAttribute("titulo", "Buscar Propietario");
		modelo.addAttribute("auto", auto);
		String dni = auto.getPropietario().getDocumento();
		System.out.println(dni);
		System.out.println("DNI solo numeros: " + dni.matches("[0-9]+"));
		
		if(dni.length()>8 || dni.length()<7) {
			FieldError error = new FieldError("auto", "propietario", "El documento debe tener entre 7 y 8 caracteres.");
			resultado.addError(error);
			System.out.println("Errores dni 1");
		}else {
			if(!dni.matches("[0-9]+")) {
			FieldError error = new FieldError("auto", "propietario", "El documento solo debe contener numeros.");
			resultado.addError(error);
			System.out.println("Errores dni 2");
			}
		}
		if(resultado.hasErrors()) {
//			modelo.addAttribute("auto", auto);
			return "auto/buscaPropietario";
		}
		
		try {
			Cliente propietario = new Cliente();
			propietario.setDocumento(dni);
			var autos = autoService.buscarPorPropietario(propietario);
			modelo.addAttribute("autos", autos);
			modelo.addAttribute("titulo", "Autos por Propietario");
			}
		catch (Exception e) {
			System.out.println("Error al buscar por Propietario" + e);
		}
		return "informes/listaAutos";
	}
		
		
// -----------------Listar--TODOS--APTOS--CONDICIONALES--RECHASADOS---------------------------------------------------------	

	@GetMapping("/listaAutosTodos")
	public String listarAt(Model model) {
		model.addAttribute("titulo", "Todos los Autos");
		var autos = autoService.listarAutos();
		model.addAttribute("autos", autos);
		return "informes/listaAutos";
	}

	@GetMapping("/listaAutos")
	public String listarAfiltro(Model model, Auto auto) {
		
		var autos = autoService.buscarPorEstado(auto);
		model.addAttribute("autos", autos);
		return "informes/listaAutos";
	}
	
	
	// ----------------------------------------------------------------------------	

	@GetMapping("/editarAuto") // <--- Acá pasamos query parameters (ver HTML)
	public String editarAuto(Auto auto, Model model) {
		auto = autoService.buscarPorDominio(auto);
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

		auto = autoService.buscarPorDominio(auto);
		modelo.addAttribute("auto", auto);

		return "informes/muestraAuto";
	}

}

//private Model agregarMarModVer(Model modelo) {
//	
//	var marcas = marcaService.listarMarcas();
//	modelo.addAttribute("marcas", marcas);
//	var modelos = modeloService.listarModelos();
//	modelo.addAttribute("modelos", modelos);
//	var versiones = versionService.listarVersiones();
//	modelo.addAttribute("versiones", versiones);
//	return modelo;
//}