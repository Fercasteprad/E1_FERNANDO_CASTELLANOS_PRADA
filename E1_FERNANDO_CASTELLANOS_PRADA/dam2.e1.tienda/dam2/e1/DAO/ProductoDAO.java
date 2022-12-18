package dam2.e1.DAO;

import java.io.File;
import java.util.ArrayList;

import dam2.e1.model.Producto;

import dam2.e1.service.Operaciones;


public class ProductoDAO {
	
	private ArrayList<Producto> productos;
		
	public ProductoDAO() {

	}

	public ArrayList<Producto> getProductos(){
		return productos;
	}
			
	public void setProductos(File ruta) {
		
		//Devuelve un ArrayList de los productos que esten escritas en el txt
		
		ArrayList<String> textoPlano = Operaciones.leerFichero(ruta); //Devuelve los atributos planos
		ArrayList<Producto> listaProductos = Operaciones.listarProductos(textoPlano); //Convierte los atributos en productos
	
		this.productos = listaProductos;
		
	}

}
