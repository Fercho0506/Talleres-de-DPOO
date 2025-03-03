package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteTiqueteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea{
	
	@Override
    public void cargarAerolinea( String archivo, Aerolinea aerolinea ) throws Exception, InformacionInconsistenteException
    {
		String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
        JSONObject raiz = new JSONObject( jsonCompleto );
        cargarAviones ( aerolinea, raiz.getJSONArray( "aviones" ) );
        HashMap<String, Aeropuerto> airs=cargarAeropuertos( aerolinea, raiz.getJSONArray( "aeropuertos" ) );
        cargarRutas( aerolinea, raiz.getJSONArray( "rutas" ) , airs);
        cargarVuelos( aerolinea, raiz.getJSONArray( "vuelos" ));
        
    }
	private void cargarAviones( Aerolinea aerolinea, JSONArray jAvion ) throws InformacionInconsistenteTiqueteException{
		for (int i=0; i < jAvion.length(); i++){
			 JSONObject avion = jAvion.getJSONObject( i );
			 String nombre= avion.getString("nombre");
			 int capacidad= avion.getInt("capacidad");
			 Avion nAvion= new Avion(nombre, capacidad);
			 aerolinea.agregarAvion(nAvion);
			 
		 }
	}
	
	private HashMap<String, Aeropuerto> cargarAeropuertos( Aerolinea aerolinea, JSONArray jAir) throws InformacionInconsistenteTiqueteException{
		HashMap<String, Aeropuerto> aeropuertos= new HashMap<String, Aeropuerto>();
		for (int i=0; i < jAir.length(); i++){
			 JSONObject air = jAir.getJSONObject( i );
			 String nombre= air.getString("nombre");
			 double latitud= air.getDouble("latitud");
			 double longitud= air.getDouble("longitud");
			 String codigo= air.getString("codigo");
			 String ciudad= air.getString("ciudad");
			 Aeropuerto nAero= new Aeropuerto(nombre, codigo, ciudad, latitud, longitud);
			 aeropuertos.put(nombre, nAero); 
		 }
		return aeropuertos;
	}
	
	private void cargarRutas( Aerolinea aerolinea, JSONArray jRutas, HashMap<String, Aeropuerto> airs ) throws InformacionInconsistenteTiqueteException
	{
		for (int i=0; i < jRutas.length(); i++){
			 JSONObject ruta = jRutas.getJSONObject( i );
			 String codigo= ruta.getString("codigoRuta");
			 String horaLlegada= ruta.getString("horaLlegada");
			 String horaSalida= ruta.getString("horaSalida");
			 Aeropuerto destino= airs.get(ruta.getString("destino"));
			 Aeropuerto origen= airs.get(ruta.getString("origen"));
			 Ruta nRuta= new Ruta(origen, destino, horaSalida, horaLlegada, codigo);
			 aerolinea.agregarRuta(nRuta);
		 }
	}
	private void cargarVuelos( Aerolinea aerolinea, JSONArray jVuelo ) throws Exception
	{
		 for (int i=0; i < jVuelo.length(); i++){
			 JSONObject vuelo = jVuelo.getJSONObject( i );
			 String fecha= vuelo.getString("fecha");
			 String ruta= vuelo.getString("ruta");
			 String avion= vuelo.getString("avion");
			 aerolinea.programarVuelo(fecha, ruta, avion);
		 }
	 }

    @Override
    public void salvarAerolinea( String archivo, Aerolinea aerolinea ) throws IOException
    {
    	JSONObject jobject = new JSONObject( );
    	JSONArray jAviones = new JSONArray( );
    	JSONArray jVuelos = new JSONArray( );
    	JSONArray jRutas = new JSONArray( );
    	JSONArray jAeropuertos= new JSONArray( );
    	HashMap<String, Aeropuerto> aeropuertos= new HashMap<String, Aeropuerto>();
    	
    	for (Vuelo vuelo: aerolinea.getVuelos()) {
    		JSONObject jVuelo = new JSONObject( );
    		jVuelo.put("vuelo", vuelo);
    		jVuelo.put("avion", vuelo.getAvion().getNombre());
    		jVuelo.put("ruta", vuelo.getRuta().getCodigoRuta());
    		jVuelos.put(jVuelo);
    	}
    	for (Avion avion: aerolinea.getAviones()) {
    		JSONObject jAvion = new JSONObject( );
    		jAvion.put("nombre", avion.getNombre());
    		jAvion.put("capacidad", avion.getCapacidad());
    		jAviones.put(jAvion);

    	}
    	for (Ruta ruta: aerolinea.getRutas()) {
    		JSONObject jRuta = new JSONObject( );
    		
    		jRuta.put("codigoRuta", ruta.getCodigoRuta());
    		jRuta.put("horaLlegada", ruta.getHoraLlegada());
    		jRuta.put("horaSalida", ruta.getHoraSalida());
    		jRuta.put("destino", ruta.getDestino().getNombre());
    		jRuta.put("origen", ruta.getOrigen().getNombre());
    		Aeropuerto origen= ruta.getOrigen();
    		Aeropuerto destino= ruta.getDestino();
    		salvarAeropuerto(origen, jAeropuertos, aeropuertos);
    		salvarAeropuerto(destino, jAeropuertos, aeropuertos);
    		jRutas.put(jRuta);
    		
    	}
    	jobject.put("vuelos", jVuelos);
    	jobject.put("aviones", jAviones);
    	jobject.put("rutas", jRutas);
    	jobject.put("aeropuertos", jAeropuertos);
    	
    	PrintWriter pw = new PrintWriter( archivo );
    	jobject.write( pw, 2, 0 );
        pw.close( );
    }
    
    private void salvarAeropuerto(Aeropuerto aer, JSONArray arreglo, HashMap<String, Aeropuerto> aeropuertos) 
    {
    	if (!aeropuertos.containsKey(aer.getNombre())) {
    		aeropuertos.put(aer.getNombre(), aer);
    		JSONObject jAir = new JSONObject( );
    		jAir.put("nombre", aer.getNombre());
    		jAir.put("latitud", aer.getLatitud());
    		jAir.put("longitud", aer.getLongitud());
    		jAir.put("codigo", aer.getCodigo());
    		jAir.put("ciudad", aer.getNombreCiudad());
    		arreglo.put(jAir);
    	}
    }
    
}