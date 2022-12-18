package dam2.e1.model;

import java.io.File;

public class RutasArchivo {
	
	private File archivo;
	
	public RutasArchivo() {

	}

	public File getArchivo() {
		return archivo;
	}

	public void setRuta(String ruta) {
		File rutaArchivo = new File(ruta);
		this.archivo = rutaArchivo;
	}

	
}
