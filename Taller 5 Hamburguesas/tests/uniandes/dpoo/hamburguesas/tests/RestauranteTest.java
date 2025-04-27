package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;

import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class RestauranteTest {
	private Restaurante restaurante;
	private File archivoIngredientes;
	private File archivoMenu;
	private File archivoCombos;
	
	@BeforeEach
	void setUp() {
		restaurante= new Restaurante();
		archivoIngredientes = new File("./data/ingredientes.txt");
		archivoMenu = new File("./data/menu.txt");
		archivoCombos = new File("./data/combos.txt");
		
	}
	
	@AfterEach
	void reset() {
		restaurante=null;
	}
	
	@Test
	void iniciarPedidoTest() throws YaHayUnPedidoEnCursoException {
		restaurante.iniciarPedido("Esteban", "Calle 2");
		assertNotNull(restaurante.getPedidoEnCurso(), "Debe haber un pedido en curso");
		assertEquals(restaurante.getPedidoEnCurso().getNombreCliente(), "Esteban", "El pedido en Curso es incorrecto");
	}
	
	@Test
	void PedidoEnCursoTest() {
		assertNull(restaurante.getPedidoEnCurso(), "No hay un pedido en curso");
	}
	
	@Test
	void excepcionPedidoEnCursoTest() throws YaHayUnPedidoEnCursoException {
		restaurante.iniciarPedido("Esteban", "Calle 2");
        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
            restaurante.iniciarPedido("Carlos", "Calle 4");
        });
	}
	
	@Test
	void excepcionNoHayPedidoTest() throws NoHayPedidoEnCursoException{
		assertThrows(NoHayPedidoEnCursoException.class, () -> {
            restaurante.cerrarYGuardarPedido();
        });
	}
	
	@Test
    void IOExceptionTest() {
        File archivo= new File("no_existe.txt");
        assertThrows(IOException.class, () -> {
            restaurante.cargarInformacionRestaurante(archivo, archivo, archivo);
        });
    }
	
	 @Test
	 void cerrarYGuardarPedidoTest() throws Exception {
		 restaurante.iniciarPedido("Esteban", "Calle 2");
		 restaurante.cerrarYGuardarPedido();
		 assertNull(restaurante.getPedidoEnCurso(), "No debe haber un pedido en curso");
	 }
	 
	 @Test
	 void cargarIngredientesTest() throws HamburguesaException, NumberFormatException, IOException {
	     restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
	     assertEquals(15, restaurante.getIngredientes().size(), "Debe haber cargado 15 ingredientes");
	 }
	 
	 @Test
	 void cargarCombosTest() throws HamburguesaException, NumberFormatException, IOException {
	     restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
	     assertEquals(4, restaurante.getMenuCombos().size(), "Debe haber cargado 4 combos");
	 }
	 
	 @Test
	 void cargarProductosTest() throws HamburguesaException, NumberFormatException, IOException {
	     restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
	     assertEquals(22, restaurante.getIngredientes().size(), "Debe haber cargado 22 productos");
	 }
	 
	 @Test
	 void ingredientesRepetidoExceptionTest() throws IOException {
		 File ingredientes = File.createTempFile("ingredientes", ".txt");
		 try (FileWriter writer = new FileWriter(ingredientes)) {
			 writer.write("Lechuga;500\nLechuga;400\n");
			 }
		 assertThrows(IngredienteRepetidoException.class, () -> {
			 restaurante.cargarInformacionRestaurante(ingredientes, archivoMenu, archivoCombos);
			 });
	 }
	 
	 @Test
	 void productosRepetidoExceptionTest() throws IOException {
		 File productos = File.createTempFile("productos", ".txt");
		 try (FileWriter writer = new FileWriter(productos)) {
			 writer.write("corral;14000\ncorral;16000\n");
			 }
		 assertThrows(ProductoRepetidoException.class, () -> {
			 restaurante.cargarInformacionRestaurante(archivoIngredientes, productos, archivoCombos);
			 });
	 }
	 
	 @Test
	 void combosRepetidoExceptionTest() throws IOException {
		 File combos = File.createTempFile("combos", ".txt");
		 try (FileWriter writer = new FileWriter(combos)) {
			 writer.write("combo corral;10%;corral;papas medianas;gaseosa\ncombo corral;5%;corral;papas medianas;agua cristal sin gas\n");
			 }
		 assertThrows(ProductoRepetidoException.class, () -> {
			 restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, combos);
			 });
	 }
	 
	 @Test
	 void productoFaltanteExceptionTest() throws IOException{
		 File combos = File.createTempFile("combos", ".txt");
		 try (FileWriter writer = new FileWriter(combos)) {
			 writer.write("combo corral;10%;corral;papas medianas;jugo\n");
			 }
		 assertThrows(ProductoFaltanteException.class, () -> {
			 restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, combos);
			 });
	 }
}
