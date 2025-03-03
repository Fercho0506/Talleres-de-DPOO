package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {
	public CalculadoraTarifasTemporadaAlta() {
	}
	
	protected int COSTO_POR_KM = 1000;
	
	public int calcularCostoBase(Vuelo vuelo, Cliente ciente) {
		return COSTO_POR_KM*calcularDistanciaVuelo​(vuelo.getRuta());
	}
	
	public double calcularPorcentajeDescuento(Cliente cliente) {
		return 0;
	}
}