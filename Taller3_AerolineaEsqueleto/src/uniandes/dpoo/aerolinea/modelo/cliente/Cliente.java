package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.List;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {
	private String tipoCliente;
	private String identificador;
	private List<Tiquete> tiquetesUsados;
	private List<Tiquete> tiquetesSinUsar;
	
	public Cliente(String tipoCliente) {
		this.tipoCliente=tipoCliente;
		this.tiquetesUsados= new ArrayList<Tiquete>();
		this.tiquetesSinUsar= new ArrayList<Tiquete>();
	}
	
	public String getTipoCliente() {
		return tipoCliente;
	}
	
	public void agregarTiquete(Tiquete tiquete) {
		tiquetesSinUsar.add(tiquete);
	}
	
	public String getIdentificador() {
		return identificador;
	}
	
	public void usarTiquetes(Vuelo vuelo) {
		int numero=0;
		boolean no_esta=true;
		while(numero < tiquetesSinUsar.size() && no_esta) {
			Tiquete i= tiquetesSinUsar.get(numero);
			if(i.getVuelo()==vuelo) {
				i.marcarComoUsado();
				tiquetesUsados.addLast(i);
				tiquetesSinUsar.remove(numero);
				no_esta=false;
			}
			numero+=1;
		}
	}
	
	public int calcularValorTotalTiquetes() {
		int total=0;
		for (Tiquete i: tiquetesSinUsar) {
			total += i.getTarifa();
		}
		return total;
	}
}