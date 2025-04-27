package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
	
	private static final String nombre= "sopa";
	private static final int precio= 10;
	private ProductoMenu producto;
	
	@BeforeEach
    void setUp() {
		producto= new ProductoMenu(nombre, precio);
	}
	
	@AfterEach
    void reset() {
		producto=null;
	}
	
	@Test
	void testGetNombre() {
		assertEquals(producto.getNombre(), nombre, "No se devuelve el nombre del producto correctamente");
	}
	
	@Test
	void testGetPrecioBase() {
		assertEquals(producto.getPrecio(), precio, "No se devuelve el precio del producto correctamente");
	}
	
	@Test
	void testGenerarFactura() {
        String texto =nombre+"\n"+"            " + precio + "\n";
        assertEquals(producto.generarTextoFactura(), texto, "No se genera la factura correctamente");
	}
}
