package packModelo;

import java.io.*;
import java.net.URL;
import java.util.*;

import packVista.VentanaInicio;

public class ModeloValoracion {
	
	//atributos
	
	
	
	private HashMap<Integer, HashMap<Integer,Double>> valoraciones = new HashMap<Integer, HashMap<Integer,Double>>();
	private static ModeloValoracion miModeloValoracion = new ModeloValoracion();
	
	
	//metodos
	
	public static ModeloValoracion getModeloValoracion()
	{
		return miModeloValoracion;
	}
	
	public void cargaRatings(){
		
		BufferedReader br= null;
		String separador= ",";
		try {
			URL path = ModeloValoracion.class.getClassLoader().getResource("./packArchivos/movie-ratings.csv");
			br = new BufferedReader(new FileReader(path.getPath()));
			String linea = br.readLine();
			String keyAntigua = "";
			while(linea!=null) {
				HashMap<Integer,Double> hash = new HashMap<Integer,Double>();
				String [] campos = linea.split(separador);
				if (!campos[0].equals(keyAntigua))
				{
					valoraciones.put(Integer.parseInt(campos[0]), hash );
				}				
				System.out.println(Arrays.toString(campos));
				valoraciones.get(Integer.parseInt(campos[0])).put(Integer.parseInt(campos[1]), Double.parseDouble(campos[2]));
				keyAntigua = campos[0];
				linea= br.readLine();
			}
			if (br!=null) {
				br.close();
			}
			
		} catch(Exception e) {
			System.out.println("Error");	
		}
	// hashratings.get(pUsuario).put(pPelicula, pValoracion);
	//.get(clave) te da el valor de la clave. Ejemplo: tenemos <1,3>, si hacemos .get(1) nos devuelve 3
	//.put(clave, valor) introduce un valor al map. Ejemplo: .put(2,65); -> .get(2) = 65 el dato ya esta introducido.
	// Comandos hashmap:  https://jarroba.com/map-en-java-con-ejemplos/ 
	}
	
	public void imprimirDatos()
	{
		for (Integer usuario: valoraciones.keySet()) // Por cada usuario
		{
            String usuarioS = usuario.toString();
            for(Integer pelicula: valoraciones.get(usuario).keySet()) // Por cada pelicula del usuario
            {
            	String peliculaS = pelicula.toString();
	            String valoracion = valoraciones.get(usuario).get(pelicula).toString();
	            // System.out.println(usuarioS + " " + peliculaS + " " + valoracion); // Si queremos verlo por consola
	            VentanaInicio.getVentanaInicio().unirTexto(usuarioS + " " + peliculaS + " " + valoracion + "\n");
            }
		}
	}
}
