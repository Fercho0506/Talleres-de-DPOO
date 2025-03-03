package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public abstract class CalculadoraTarifas {
	protected double IMPUESTO=0.28;
	
	
	public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
		int costoBase= calcularCostoBase(vuelo, cliente);
		double descuento= calcularPorcentajeDescuento(cliente);
		int impuestos= calcularValorImpuestos​(costoBase);
		double respuesta=0;
		if (descuento == 0) {
			respuesta= costoBase + impuestos;
		}
		else {
			respuesta= costoBase*(1-descuento) + impuestos;
		}
		return (int)respuesta;
	}
	protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);
	
	protected abstract double calcularPorcentajeDescuento(Cliente cliente);
	
	protected int calcularDistanciaVuelo​(Ruta ruta) {
		Aeropuerto aeropuerto1= ruta.getOrigen();
		Aeropuerto aeropuerto2= ruta.getDestino();
		return Aeropuerto.calcularDistancia(aeropuerto1, aeropuerto2);
	}
	
	protected int calcularValorImpuestos​(int costoBase) {
		double respuesta= costoBase*IMPUESTO;
		return (int) respuesta;
	}
	
}