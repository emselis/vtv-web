package com.web2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.web2.controllers.*;
import com.web2.entities.*;
import com.web2.enumeraciones.PuestosEmpleados;
import com.web2.repositories.*;
import com.web2.services.*;

@SpringBootTest
class Web2ThymeleafApplicationTests {

//	--------------------------------------------------------	JUnit
	@Test
	void nombreEmpleado() {
		Empleado empleado = new Empleado();
		empleado.setNombre("Pablo");
		String esperado = "Pablo";
		String real = empleado.getNombre();
		// Con expresiones lambda solo se carga el mensaje si ocurre el error.
		Assertions.assertNotNull(real, () -> "El empleado no puede ser nulo.");
		Assertions.assertEquals(esperado, real, () -> "El nombre no es el que se esperaba");
		Assertions.assertTrue(real.equals("Pablo"), () -> "Nombre de empleado esperado");

	}

//	@ParameterizedTest
//	@ValueSource(strings = { "aSb123", "23", "123EEf", "aSb-123", "123455", "asd4F.@", "qweqweqwe", "AB345FG" })
	@Test
	void validarDominios() { // String dominio) {
		AutoController autoController = new AutoController();
		String[] dominios = { "aSb123", "23", "123EEf", "aSb-123", "123455", "asd4F.@", "qweqweqwe", "AB345FG" };
		boolean[] esperado = { true, false, false, false, false, false, false, true };
		boolean[] resultado = new boolean[dominios.length];

		for (int i = 0; i < dominios.length; i++) {
			resultado[i] = autoController.validarDominio(dominios[i]);
		}
		Assertions.assertArrayEquals(esperado, resultado);
	}

	@Test
	void estadoInspeccion() {
		InspeccionController inspController = new InspeccionController();
		Inspeccion inspeccion = new Inspeccion();
		String[] controles = { "PASO", "NO-PASO" };
		List<String> estadosEsperados = Arrays.asList("APTO", "CONDICIONAL", "CONDICIONAL", "RECHAZADO");
		List<String> estadosReales = new ArrayList<String>();

		for (String control : controles) {
			inspeccion.setMedibles(control);
			for (String control2 : controles) {
				inspeccion.setVisuales(control2);
				estadosReales.add(inspController.generaEstadoInspeccion(inspeccion));
			}
		}
		Assertions.assertEquals(estadosEsperados, estadosReales);
	}

//	--------------------------------------------------------------------------------
//											MOCKITO
//	--------------------------------------------------------------------------------

//	@Mock		// Annotation Mockito
	@MockBean // Annotation Spring
	AutoRepository autoRepository;

	@MockBean
	EmpleadoRepository empleadoRepository;

	@MockBean
	ClienteRepository clienteRepository;
	
	
//	@InjectMocks	// Annotation Mockito
	@Autowired // Annotation Spring
	AutoService autoService;

	@Autowired
	EmpleadoService empleadoService;

	@Autowired
	ClienteService clienteService;
	
//	--------------------------------------------------------	AUTO

	@Disabled
	@Test
	void encuentraAutoDominio() {

		// TODO -> Definir estas dos lineas como Opcional<>
		when(autoRepository.findById("AB132CC")).thenReturn(Datos.auto1);
		when(autoRepository.findById("EFG456")).thenReturn(Datos.auto2);

		Auto autoA = autoService.buscarPorDominio("AB123CC");
		Auto autoB = autoService.buscarPorDominio("EFG456");

		assertEquals("CHEVROLET", autoA.getMarca());
		assertEquals("FIAT", autoB.getMarca());

	}

	@Test
	void encuentraAutoEstado() {

		when(autoRepository.findByEstado("SIN VERIFICAR")).thenReturn(Datos.autosSegunEstado);

		List<Auto> autosEst = autoService.buscarPorEstado("SIN VERIFICAR");
		assertEquals("AB123CD", autosEst.get(0).getDominio());
		assertEquals("FIAT", autosEst.get(1).getMarca().getMarca());
		verify(autoRepository, times(1)).findByEstado("SIN VERIFICAR"); // default times=1

	}

	@Test
	void encuentraAutoDuenio() {

		when(autoRepository.findByPropietario(Datos.duenio12.getDocumento())).thenReturn(Datos.autosSegunDuenio);

		List<Auto> autosDue = autoService.buscarPorPropietario("29191555");

		assertAll(() -> {
			assertEquals("29191555", Datos.duenio12.getDocumento());
		}, () -> {
			assertEquals("AGILE", autosDue.get(0).getModelo().getModelo());
		}, () -> {
			assertEquals("CLASSIC", autosDue.get(1).getModelo().getModelo());
		}, () -> {
			assertEquals("Eze", autosDue.get(0).getPropietario().getNombre());
		}, () -> {
			assertEquals(1, autosDue.get(1).getModelo().getIdModelo());
		});
	}

//	--------------------------------------------------------	Empleados	

	@Test
	void listarEmpleados() {

		when(empleadoRepository.findAll()).thenReturn(Datos.empleadosTodos);

		List<Empleado> listaEmpl = empleadoService.listarEmpleados();

		assertAll(() -> {
			assertEquals("29222333", listaEmpl.get(0).getDocumento());
		}, () -> {
			assertEquals("Damian", listaEmpl.get(1).getNombre());
		}, () -> {
			assertEquals("ALTA", listaEmpl.get(1).getEstado());
		}, () -> {
			assertEquals(PuestosEmpleados.GERENTE, listaEmpl.get(2).getPuesto());
		}, () -> {
			assertEquals("Marco", listaEmpl.get(2).getApellido());
		});
	}
	
	@Test
	void empleadosPorEstadoPuesto() {
		when(empleadoRepository.findByEstadoPuesto("ALTA", PuestosEmpleados.INSPECTOR)).thenReturn(Datos.empleadosInspAlta);

		List<Empleado> listaEmpl = empleadoService.empleadosEstadoPuesto("ALTA", PuestosEmpleados.INSPECTOR);
		
		assertAll(() -> {
			assertEquals("29222333", listaEmpl.get(0).getDocumento());
		}, () -> {
			assertEquals("Damian", listaEmpl.get(1).getNombre());
		}, () -> {
			assertEquals("ALTA", listaEmpl.get(1).getEstado());
		}, () -> {
			assertEquals(PuestosEmpleados.INSPECTOR, listaEmpl.get(0).getPuesto());
		}, () -> {
			assertEquals("Loss", listaEmpl.get(0).getApellido());
		});
	}

	
//	--------------------------------------------------------	Clientes	

	@Test
	void listarClientes() {

		when(clienteRepository.findAll()).thenReturn(Datos.clientesTodos);

		List<Cliente> listaClient = clienteService.listarClientes();

		assertAll(() -> {
			assertEquals("29191555", listaClient.get(0).getDocumento());
		}, () -> {
			assertEquals("Pablo", listaClient.get(1).getNombre());
		}, () -> {
			assertEquals("SI", listaClient.get(1).getExento());
		}, () -> {
			assertEquals("Ro", listaClient.get(2).getApellido());
		}, () -> {
			assertEquals("NO", listaClient.get(2).getExento());
		});
	}

}
