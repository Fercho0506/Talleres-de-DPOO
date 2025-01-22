package uniandes.dpoo.estructuras.logica;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre arreglos de enteros y de cadenas.
 *
 * Todos los métodos deben operar sobre los atributos arregloEnteros y arregloCadenas.
 * 
 * No pueden agregarse nuevos atributos.
 * 
 * Implemente los métodos usando operaciones sobre arreglos (ie., no haga cosas como construir listas para evitar la manipulación de arreglos).
 */
public class SandboxArreglos
{
    /**
     * Un arreglo de enteros para realizar varias de las siguientes operaciones.
     * 
     * Ninguna posición del arreglo puede estar vacía en ningún momento.
     */
    private int[] arregloEnteros;

    /**
     * Un arreglo de cadenas para realizar varias de las siguientes operaciones
     * 
     * Ninguna posición del arreglo puede estar vacía en ningún momento.
     */
    private String[] arregloCadenas;

    /**
     * Crea una nueva instancia de la clase con los dos arreglos inicializados pero vacíos (tamaño 0)
     */
    public SandboxArreglos( )
    {
        arregloEnteros = new int[]{};
        arregloCadenas = new String[]{};
    }

    /**
     * Retorna una copia del arreglo de enteros, es decir un nuevo arreglo del mismo tamaño que contiene copias de los valores del arreglo original
     * @return Una copia del arreglo de enteros
     */
    public int[] getCopiaEnteros( )
    {

    	return arregloEnteros.clone();
    }

    /**
     * Retorna una copia del arreglo de cadenas, es decir un nuevo arreglo del mismo tamaño que contiene copias de los valores del arreglo original
     * @return Una copia del arreglo de cadenas
     */
    public String[] getCopiaCadenas( )
    {
    	return arregloCadenas.clone();
    }

    /**
     * Retorna la cantidad de valores en el arreglo de enteros
     * @return
     */
    public int getCantidadEnteros( )
    {
    	return arregloEnteros.length;
    }

    /**
     * Retorna la cantidad de valores en el arreglo de cadenas
     * @return
     */
    public int getCantidadCadenas( )
    {
    	return arregloCadenas.length;
    }

    /**
     * Agrega un nuevo valor al final del arreglo. Es decir que este método siempre debería aumentar en 1 la capacidad del arreglo.
     * 
     * @param entero El valor que se va a agregar.
     */
    public void agregarEntero( int entero )
    {
    	int [] arreglo_mas_entero;
    	arreglo_mas_entero = new int[getCantidadEnteros() +1];
    	for (int i=0; i < getCantidadEnteros(); i++)
    		arreglo_mas_entero[i]= arregloEnteros[i];
    	arreglo_mas_entero[arreglo_mas_entero.length-1]= entero;
    	arregloEnteros= arreglo_mas_entero;
    }

    /**
     * Agrega un nuevo valor al final del arreglo. Es decir que este método siempre debería aumentar en 1 la capacidad del arreglo.
     * 
     * @param cadena La cadena que se va a agregar.
     */
    public void agregarCadena( String cadena )
    {
    	String [] arreglo_mas_cadena;
    	arreglo_mas_cadena = new String[getCantidadCadenas() +1];
    	for (int i=0; i < getCantidadCadenas(); i++)
    		arreglo_mas_cadena[i]= arregloCadenas[i];
    	arreglo_mas_cadena[arreglo_mas_cadena.length-1]= cadena;
    	arregloCadenas= arreglo_mas_cadena;
    }

    /**
     * Elimina todas las apariciones de un determinado valor dentro del arreglo de enteros
     * @param valor El valor que se va eliminar
     */
    public void eliminarEntero( int valor )
    {
    	int [] arreglo_menos_entero;
    	int cantidad =0;
    	for (int i=0; i < getCantidadEnteros(); i++)
    		if (arregloEnteros[i] == valor) {
    			cantidad+=1;
    		}
    	arreglo_menos_entero= new int[getCantidadEnteros()-cantidad];
    	int posicion=0;
    	for (int i=0; i < getCantidadEnteros(); i++) {
    		if (arregloEnteros[i]!= valor) {
    			arreglo_menos_entero[posicion]= arregloEnteros[i];
        		posicion +=1;
    		}
    	}
    	arregloEnteros= arreglo_menos_entero;
    }

    /**
     * Elimina todas las apariciones de un determinado valor dentro del arreglo de cadenas
     * @param cadena La cadena que se va eliminar
     */
    public void eliminarCadena( String cadena )
    {
    	String [] arreglo_menos_cadena;
    	int cantidad=0;
    	for (int i=0; i < getCantidadCadenas(); i++)
    		if (arregloCadenas[i] == cadena) {
    			cantidad+=1;
    		}
    	arreglo_menos_cadena= new String[getCantidadCadenas()-cantidad];
    	int posicion=0;
    	for (int i=0; i < getCantidadCadenas(); i++) {
    		if (arregloCadenas[i]== cadena) {
    			arreglo_menos_cadena[posicion]= arregloCadenas[i];
    			posicion+=1;
    		}
    	}
    	arregloCadenas= arreglo_menos_cadena;
    }

    /**
     * Inserta un nuevo entero en el arreglo de enteros.
     * 
     * @param entero El nuevo valor que debe agregarse
     * @param posicion La posición donde debe quedar el nuevo valor en el arreglo aumentado. Si la posición es menor a 0, se inserta el valor en la primera posición. Si la
     *        posición es mayor que el tamaño del arreglo, se inserta el valor en la última posición.
     */
    public void insertarEntero( int entero, int posicion )
    {
    	if(posicion >= arregloEnteros.length) {
    		agregarEntero(entero);
    	}
    	else{
    		if (posicion<0) {
    			posicion=0;
    		}
    		int [] nuevo_arreglo= new int[getCantidadEnteros()+1];
    		for (int i=0; i<getCantidadEnteros()+1;i++) {
    			if (i<posicion) {
    				nuevo_arreglo[i]= arregloEnteros[i];
    			}
    			else if(i== posicion){
    				nuevo_arreglo[i]= entero;
    			}
    			else {
    				nuevo_arreglo[i]= arregloEnteros[i-1];
    			}
    		}
    		arregloEnteros= nuevo_arreglo;
    	}
    }

    /**
     * Elimina un valor del arreglo de enteros dada su posición.
     * @param posicion La posición donde está el elemento que debe ser eliminado. Si el parámetro posicion no corresponde a ninguna posición del arreglo de enteros, el método
     *        no debe hacer nada.
     */
    public void eliminarEnteroPorPosicion( int posicion )
    {
    	int [] nuevo_arreglo;
    	if (0 <= posicion & posicion< arregloEnteros.length) {
    		nuevo_arreglo= new int [getCantidadEnteros()-1];
    		for (int i=0; i < getCantidadEnteros()-1; i++)
    			if (i < posicion) {
    				nuevo_arreglo[i]= arregloEnteros[i];
    			}
    			else {
    				nuevo_arreglo[i]= arregloEnteros[i+1];
    			}
    		arregloEnteros= nuevo_arreglo;
    	}
    }

    /**
     * Reinicia el arreglo de enteros con los valores contenidos en el arreglo del parámetro 'valores' truncados.
     * 
     * Es decir que si el valor fuera 3.67, en el nuevo arreglo de enteros debería quedar el entero 3.
     * @param valores Un arreglo de valores decimales.
     */
    public void reiniciarArregloEnteros( double[] valores )
    {
    	arregloEnteros= new int[valores.length];
    	for (int i=0; i< valores.length; i++) {
    		arregloEnteros[i]= (int) valores[i];
    	}
    }

    /**
     * Reinicia el arreglo de cadenas con las representaciones como Strings de los objetos contenidos en el arreglo del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Un arreglo de objetos
     */
    public void reiniciarArregloCadenas( Object[] objetos )
    {
    	arregloCadenas= new String[objetos.length];
    	for (int i=0; i< objetos.length; i++) {
    		arregloCadenas[i]= objetos[i].toString();
    	}
    }

    /**
     * Modifica el arreglo de enteros para que todos los valores sean positivos.
     * 
     * Es decir que si en una posición había un valor negativo, después de ejecutar el método debe quedar el mismo valor muliplicado por -1.
     */
    public void volverPositivos( )
    {
    	for (int i=0; i<getCantidadEnteros(); i++) {
    		if(arregloEnteros[i] < 0) {
    			arregloEnteros[i]= arregloEnteros[i]*-1;
    		}
    	}
    }

    /**
     * Modifica el arreglo de enteros para que todos los valores queden organizados de menor a mayor.
     */
    public void organizarEnteros( )
    {
    	Arrays.sort(arregloEnteros);
    }

    /**
     * Modifica el arreglo de cadenas para que todos los valores queden organizados lexicográficamente.
     */
    public void organizarCadenas( )
    {
    	Arrays.sort(arregloCadenas);
    }

    /**
     * Cuenta cuántas veces aparece el valor recibido por parámetro en el arreglo de enteros
     * @param valor El valor buscado
     * @return La cantidad de veces que aparece el valor
     */
    public int contarApariciones( int valor )
    {
        int cantidad_valor;
        cantidad_valor=0;
        for (int i=0; i<getCantidadEnteros(); i++) {
        	if (arregloEnteros[i] == valor) {
        		cantidad_valor +=1;
        	}
        }
        return cantidad_valor;
    }

    /**
     * Cuenta cuántas veces aparece la cadena recibida por parámetro en el arreglo de cadenas.
     * 
     * La búsqueda no debe diferenciar entre mayúsculas y minúsculas.
     * @param cadena La cadena buscada
     * @return La cantidad de veces que aparece la cadena
     */
    public int contarApariciones( String cadena )
    {
    	int cantidad_valor;
        cantidad_valor=0;
        
        for (int i=0; i<getCantidadCadenas(); i++) {
        	if (arregloCadenas[i].toLowerCase().equalsIgnoreCase(cadena.toLowerCase())) {
        		cantidad_valor +=1;
        	}
        }
        return cantidad_valor;
    }

    /**
     * Busca en qué posiciones del arreglo de enteros se encuentra el valor que se recibe en el parámetro
     * @param valor El valor que se debe buscar
     * @return Un arreglo con los números de las posiciones del arreglo de enteros en las que se encuentra el valor buscado. Si el valor no se encuentra, el arreglo retornado
     *         es de tamaño 0.
     */
    public int[] buscarEntero( int valor )
    {
    	int[] nuevo_arreglo;
        int cantidad;
        cantidad= contarApariciones(valor);
        nuevo_arreglo= new int[0];
        if (cantidad > 0) {
        	nuevo_arreglo= new int[cantidad];
        	cantidad=0;
        	for (int i=0; i<getCantidadEnteros(); i++) {
        		if(arregloEnteros[i]== valor) {
        			nuevo_arreglo[cantidad]= i;
        			cantidad +=1;
        		}
        	}
        }
    	return nuevo_arreglo;
    }

    /**
     * Calcula cuál es el rango de los enteros (el valor mínimo y el máximo).
     * @return Un arreglo con dos posiciones: en la primera posición, debe estar el valor mínimo en el arreglo de enteros; en la segunda posición, debe estar el valor máximo
     *         en el arreglo de enteros. Si el arreglo está vacío, debe retornar un arreglo vacío.
     */
    public int[] calcularRangoEnteros( )
    {
       int [] nuevo_arreglo;
       if (getCantidadEnteros() > 0) {
    	   nuevo_arreglo= new int[2];
    	   nuevo_arreglo[0]= arregloEnteros[0];
    	   nuevo_arreglo[1]= arregloEnteros[0];
    	   for(int i=0; i<getCantidadEnteros(); i++) {
    		   if (arregloEnteros[i]< nuevo_arreglo[0]) {
    			   nuevo_arreglo[0]= arregloEnteros[i];
    		   }
    		   if (arregloEnteros[i]> nuevo_arreglo[1]) {
    			   nuevo_arreglo[1]= arregloEnteros[i];
    		   }
    	   }
    	   return nuevo_arreglo;
       }
       return new int[]{};
    }

    /**
     * Calcula un histograma de los valores del arreglo de enteros y lo devuelve como un mapa donde las llaves son los valores del arreglo y los valores son la cantidad de
     * veces que aparece cada uno en el arreglo de enteros.
     * @return Un mapa con el histograma de valores.
     */
    public HashMap<Integer, Integer> calcularHistograma( )
    {
        HashMap<Integer, Integer> mapa;
        mapa= new HashMap<Integer, Integer>();
        for (int i=0; i< getCantidadEnteros(); i++) {
        	mapa.put(arregloEnteros[i], contarApariciones(arregloEnteros[i]));
        }
        return mapa;
    }

    /**
     * Cuenta cuántos valores dentro del arreglo de enteros están repetidos.
     * @return La cantidad de enteos diferentes que aparecen más de una vez
     */
    public int contarEnterosRepetidos( )
    {
        int repetidos= 0;
        HashMap<Integer, Integer> mapa;
        mapa= new HashMap<Integer, Integer>();
        for (int i=0; i<getCantidadEnteros(); i++) {
        	int entero= arregloEnteros[i];
        	int posicion = 0;
        	boolean encontrado= true;
        	while (encontrado && posicion <getCantidadEnteros()) {
        		if (entero == arregloEnteros[posicion] && posicion != i
        				&& !mapa.containsKey(entero)) {
        			repetidos +=1;
        			encontrado= false;
        			mapa.put(entero, 1);
        		}
        		else {
        			posicion += 1;
        		}
        	}
        	
        }
        
        return repetidos;
    }

    /**
     * Compara el arreglo de enteros con otro arreglo de enteros y verifica si son iguales, es decir que contienen los mismos elementos exactamente en el mismo orden.
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si los arreglos son idénticos y false de lo contrario
     */
    public boolean compararArregloEnteros( int[] otroArreglo )
    {
    	boolean iguales= false;
    	if (getCantidadEnteros()== otroArreglo.length) {
    		int i=0;
    		boolean ahora= true;
    		while (i<getCantidadEnteros() & ahora) {
    			if (arregloEnteros[i]== otroArreglo[i]) {
    				i += 1;
    				iguales= true;
    			}
    			else {
    				ahora= false;
    			}
    		}
    	}
        return iguales;
    }

    /**
     * Compara el arreglo de enteros con otro arreglo de enteros y verifica que tengan los mismos elementos, aunque podría ser en otro orden.
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si los elementos en los dos arreglos son los mismos
     */
    public boolean mismosEnteros( int[] otroArreglo )
    {
        boolean iguales=false;
        
        if (getCantidadEnteros()== otroArreglo.length && otroArreglo.length!=0 ) {
        	for (int i=0; i< otroArreglo.length; i++) {
        		int numero= arregloEnteros[i];
        		int posicion =0;
        		boolean aparece= true;
        		while(posicion< otroArreglo.length & aparece) {
        			if (numero== otroArreglo[posicion]) {
        				iguales= true;
        				aparece= false;
        			}
        			else {
        				iguales=false;
        				posicion +=1;
        			}
        		}
        		
        	}
        }
        else if(getCantidadEnteros()== otroArreglo.length) {
        	iguales=true;
        }
    	return iguales;
    }

    /**
     * Cambia los elementos del arreglo de enteros por una nueva serie de valores generada de forma aleatoria.
     * 
     * Para generar los valores se debe partir de una distribución uniforme usando Math.random().
     * 
     * Los números en el arreglo deben quedar entre el valor mínimo y el máximo.
     * @param cantidad La cantidad de elementos que debe haber en el arreglo
     * @param minimo El valor mínimo para los números generados
     * @param maximo El valor máximo para los números generados
     */
    public void generarEnteros( int cantidad, int minimo, int maximo )
    {
    	arregloEnteros= new int[cantidad];
    	for (int i=0; i<cantidad; i++) {
    		arregloEnteros[i]= (int)(Math.random()*(maximo-minimo+1)) +minimo;
    	}
    }


}
