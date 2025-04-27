package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	private static final double descuento = 0;
	private static final String nombre= "Combo Hamburguesa";
	private ProductoMenu producto1;
	private ProductoMenu producto2;
	private ArrayList<ProductoMenu> itemscombo;
	private Combo combo;
	
	@BeforeEach
    void setUp() {
		producto1= new ProductoMenu("hamburguesa", 14);
		producto2= new ProductoMenu("papas", 8);
		itemscombo= new ArrayList<ProductoMenu>();
		itemscombo.addLast(producto1);
		itemscombo.addLast(producto2);
		combo= new Combo(nombre, descuento, itemscombo);
	}
	
	@AfterEach
    void reset() {
		producto1=null;
		producto2=null;
		combo=null;
		itemscombo=null;
	}
	
	@Test
	void getNombreTest() {
		assertEquals(nombre, combo.getNombre(), "El combo no retorna el nombre bien");
	}
	
	@Test
	void getPrecioTest() {
		assertEquals(14+8, combo.getPrecio(), "El combo no retorna el precio correctamente");
	}
	
	@Test
	void generarFacturaTest() {
		String texto= "Combo " + nombre + "\n"
				+" Descuento: " + descuento + "\n"
				+"            " + 22 + "\n";
		assertEquals(texto, combo.generarTextoFactura(), "El combo no genera la factura bien");
	}
}
