package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
	private static final String nombre= "carne";
	private static final int precioProducto= 10;
	private ProductoMenu productoBase;
	private Ingrediente ingredienteAgregado;
	private Ingrediente ingredienteEliminado;
	private ProductoAjustado productoAjustado;
	
	@BeforeEach
    void setUp() {
		productoBase= new ProductoMenu(nombre, precioProducto);
		ingredienteAgregado= new Ingrediente("salsa", 2);
		ingredienteEliminado= new Ingrediente("cebolla", 4);
		productoAjustado = new ProductoAjustado(productoBase);
		
	}
	
	@AfterEach
    void reset() {
		productoBase= null;
		ingredienteAgregado= null;
		ingredienteEliminado= null;
		productoAjustado= null;
	}
	
	@Test
	void getNombreTest() {
		assertEquals(nombre, productoAjustado.getNombre(), "El producto no retorna el nombre bien");
	}
	
	@Test
	void getPrecioTest() {
		assertEquals(10+2, productoAjustado.getPrecio(), "El producto no retorna el precio bien");
	}
	
	@Test
	void generarFacturaTest() {
		String texto = ""
	            + "carne"
	            + "    +"+"salsa"+"                " + 2
	            + "    -"+"cebolla"
	            + "            "+12+"\n";
		assertEquals(texto, productoAjustado.generarTextoFactura(), "El producto no genera la factura bien");
	}
}
