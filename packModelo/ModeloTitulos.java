package packModelo;

import java.io.*;
import java.net.URL;
import java.util.*;

import packVista.VentanaInicio;

public class ModeloTitulos {

	private static ModeloTitulos miModeloTitulos= new ModeloTitulos();
	private HashMap<Integer, String> titulos= new HashMap<Integer, String>();
	
	//metodos
	
	public static ModeloTitulos getModeloTitulos() {
		return miModeloTitulos;
	}
	
	public void cargaTitles() {
		BufferedReader br= null;
		String separador= ";";


		try {
			URL path = ModeloValoracion.class.getClassLoader().getResource("./packArchivos/movie-titles.csv");
			br = new BufferedReader(new FileReader(path.getPath()));
			String linea = br.readLine();
			while(linea!=null) {
				String [] campos = linea.split(separador);
				System.out.println(Arrays.toString(campos));
				titulos.put(Integer.parseInt(campos[0]), campos[1]);
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
		for (Integer idPelicula: titulos.keySet()) // Por cada usuario
		{
            String idPeliculaS = idPelicula.toString();
	        // System.out.println(usuarioS + " " + peliculaS + " " + valoracion); // Si queremos verlo por consola
	        VentanaInicio.getVentanaInicio().unirTexto(idPeliculaS + " " + titulos.get(idPelicula) + "\n");
		}
	}
	
}
