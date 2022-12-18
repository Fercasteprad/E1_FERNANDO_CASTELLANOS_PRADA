package dam2.e1.service;

import java.io.File;

import dam2.e1.model.RutasArchivo;

public class Rutaservice {
	
	public static File devolverArchivo (String ruta) {
		
		RutasArchivo archivo = new RutasArchivo();
		archivo.setRuta(ruta);
		File archivoDevolver = archivo.getArchivo();
		return archivoDevolver;
		
	}

}
