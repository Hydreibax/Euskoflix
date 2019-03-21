package packModelo;

import java.io.*;
import java.net.URL;
import java.util.*;

import packVista.VentanaInicio;


public class ModeloEtiquetas {
	
	
	private static ModeloEtiquetas miModeloEtiquetas = new ModeloEtiquetas();
	private HashMap<Integer, HashMap<String,Integer>> tags = new HashMap<Integer, HashMap<String,Integer>>();

	
	//metodos
	
	public static ModeloEtiquetas getModeloEtiquetas() {
		return miModeloEtiquetas;
	}
	

	public void cargarTags() {
		BufferedReader br= null;
		String separador= ";";


		try {
			URL path = ModeloValoracion.class.getClassLoader().getResource("./packArchivos/movie-tags.csv");
			br = new BufferedReader(new FileReader(path.getPath()));
			String linea = br.readLine();
			while(linea!=null) {
				HashMap<String,Integer> hash = new HashMap<String,Integer>();
				String [] campos = linea.split(separador);
				if (!tags.containsKey(Integer.parseInt(campos[0]))) {
					tags.put(Integer.parseInt(campos[0]), hash );
				}
				System.out.println(Arrays.toString(campos));
				//el if de abajo: Si ya tenemos la etiqueta que sume 1, si no se le da el valor 1 (el primero)
				if(tags.get(Integer.parseInt(campos[0])).containsKey(campos[1])) {
					tags.get(Integer.parseInt(campos[0])).put(campos[1], tags.get(Integer.parseInt(campos[0])).get(campos[1])+1);
				}
				else {
					tags.get(Integer.parseInt(campos[0])).put(campos[1], 1);
				}
				linea= br.readLine();
			}
			if (br!=null) {
				br.close();
			}
			
		} catch(Exception e) {
			System.out.println("Error");	
		}
	}

	
	public void imprimirDatos()
	{
		for (Integer pelicula: tags.keySet()) 
		{
            String peliculaS = pelicula.toString();
            for(String etiqueta: tags.get(pelicula).keySet()) 
            {
            	String etiquetaS = etiqueta.toString();
	            String cuantos = tags.get(pelicula).get(etiqueta).toString();
	            System.out.println(peliculaS + " " + etiquetaS + " " + cuantos);
	            VentanaInicio.getVentanaInicio().unirTexto(peliculaS + " " + etiquetaS + " " + cuantos + "\n");
            }
            
		}
	}
}
