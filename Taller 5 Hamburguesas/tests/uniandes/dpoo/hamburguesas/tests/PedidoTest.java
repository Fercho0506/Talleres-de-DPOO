package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {
	private static final String nombreCliente= "Jose";
	private static final String direccion= "Calle 170";
	private ProductoMenu producto1;
	private ProductoMenu productoBase;
	private ProductoAjustado producto2;
	private Pedido pedido;
	
	@BeforeEach
    void setUp() {
	producto1= new ProductoMenu("hamburguesa", 10);
	productoBase = new ProductoMenu("papas", 5);
	producto2= new ProductoAjustado(productoBase);
	pedido= new Pedido(nombreCliente, direccion);
	}
	
	@AfterEach
	void reset() {
		producto1=null;
		producto2=null;
		pedido=null;
		productoBase=null;
	}
	
	@Test
	void getIdTest() {
		assertTrue(pedido.getIdPedido() >= 0, "El id del pedido es incorrecto");
	}
	
	@Test
	void getNombreClienteTest() {
		assertEquals(nombreCliente, pedido.getNombreCliente(), "El pedido no retorna el nombre del cliente bien");
	}
	
	@Test
	void agregarProductoTest() {
		pedido.agregarProducto(producto1);
		pedido.agregarProducto(producto2);
		assertTrue(pedido.generarTextoFactura().contains("hamburguesa"), "El pedido no agrega el producto bien");
		assertTrue(pedido.generarTextoFactura().contains("papas"), "El pedido no agrega el producto bien");
	}
	
	@Test
	void getPrecioTest() {
		pedido.agregarProducto(producto1);
		pedido.agregarProducto(producto2);
		int precioIva= (int) (15*0.19);
		assertEquals(15+precioIva, pedido.getPrecioTotalPedido(), "EL precio otorgado no es el correcto");
	}
	
	@Test
	void generarFacturaTest() {
		pedido.agregarProducto(producto1);
		pedido.agregarProducto(producto2);
		int precioIva= (int) (15*0.19);
		int precioTotal= 15+precioIva;
		String texto= "Cliente: " + nombreCliente + "\n"
				+"Dirección: " + direccion + "\n"
				+ "----------------\n"
				+"hamburguesa"+"\n"+"            " + 10 + "\n"
				+"papas" +"            " + 5 + "\n"
				+"----------------\n"
				+ "Precio Neto:  " + 15 + "\n"
				+ "IVA:          " + precioIva + "\n"
				+ "Precio Total: " + precioTotal + "\n";
		assertEquals(texto, pedido.generarTextoFactura(), "No se generó la factura correctamente");
	}
	
	@Test
	void guardarFacturaTest() throws IOException {
		pedido.agregarProducto(producto1);
		pedido.agregarProducto(producto2);
		File archivo = File.createTempFile("factura_test", ".txt");
        
        int precioIva= (int) (15*0.19);
		int precioTotal= 15+precioIva;
        String texto= "Cliente: " + nombreCliente + "\n"
				+"Dirección: " + direccion + "\n"
				+ "----------------\n"
				+"hamburguesa"+"\n"+"            " + 10 + "\n"
				+"papas" +"            " + 5 + "\n"
				+"----------------\n"
				+ "Precio Neto:  " + 15 + "\n"
				+ "IVA:          " + precioIva + "\n"
				+ "Precio Total: " + precioTotal + "\n";
        
        pedido.guardarFactura(archivo);
        Scanner scanner = new Scanner(archivo);
        String contenido = scanner.useDelimiter("\\A").next();
        assertEquals(contenido, texto, "No se guardó la factura correctamente");
        archivo.deleteOnExit();
	}
	
	@Test
	void exceptionGuardarTest() {
		pedido.agregarProducto(producto1);
		pedido.agregarProducto(producto2);
		try {
			File archivo = File.createTempFile("factura_test", ".txt");
			assertDoesNotThrow(() -> {
                pedido.guardarFactura(archivo);
            });
			archivo.deleteOnExit();
			
		} catch (Exception e) {
            assertEquals(" ", "", "La excpecion no se lanzó correctamente");
        }
	}
}