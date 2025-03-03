package uniandes.dpoo.aerolinea.modelo.tarifas;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {
	protected int COSTO_POR_KM_NATURAL = 600;
	protected int COSTO_POR_KM_CORPORATIVO = 900;
	protected double DESCUENTO_PEQ= 0.02;
	protected double DESCUENTO_MEDIANAS= 0.1;
	protected double DESCUENTO_GRANDES= 0.2;
	
	public CalculadoraTarifasTemporadaBaja() {
	}
	
	
	public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		String tipo= cliente.getTipoCliente();
		if (tipo=="Corporativo") {
			return COSTO_POR_KM_CORPORATIVO*calcularDistanciaVuelo​(vuelo.getRuta());
		}
		else {
			return COSTO_POR_KM_NATURAL*calcularDistanciaVuelo​(vuelo.getRuta());
		}
	}
	public double calcularPorcentajeDescuento(Cliente cliente) {
		String tipo= cliente.getTipoCliente();
		if(tipo!="Corporativo") {
			return 0;
		}
		else {
			ClienteCorporativo cli=(ClienteCorporativo) cliente;
			int tamano= cli.getTamanoEmpresa();
			if(tamano==1) {
				return DESCUENTO_GRANDES;
			}
			else if(tamano==2) {
				return DESCUENTO_MEDIANAS;
			}
			else {
				return DESCUENTO_PEQ;
			}
		}
	}
}