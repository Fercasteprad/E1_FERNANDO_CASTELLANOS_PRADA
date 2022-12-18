package dam2.e1.service;

import java.io.File;
import java.util.ArrayList;

import dam2.e1.DAO.ProductoDAO;
import dam2.e1.model.Producto;

public class ProductoService {
	
	public static ArrayList<Producto> devolverListaProductos (String ruta){
		
		File archivo = Rutaservice.devolverArchivo(ruta);
		ProductoDAO arrayProductos = new ProductoDAO();
		arrayProductos.setProductos(archivo);
		ArrayList<Producto> listaProductos = arrayProductos.getProductos();
		for (int i = 0;i<listaProductos.size();i++) {
			if(listaProductos.get(i).getStock() == 0) {
				listaProductos.remove(i);
			}
		}
		
		return listaProductos;
		
		
	}
	
	public static ArrayList<Producto> devolverListaProductosConStock0 (String ruta){
		
		File archivo = Rutaservice.devolverArchivo(ruta);
		ProductoDAO arrayProductos = new ProductoDAO();
		arrayProductos.setProductos(archivo);
		ArrayList<Producto> listaProductos = arrayProductos.getProductos();		
		return listaProductos;
		
		
	}
	
	public static Producto devuelveProductoId (int id , String Archivo) {
		
		ArrayList<Producto> listaProductos = devolverListaProductos(Archivo);
		Producto producto = new Producto();
		for (int i = 0; i<listaProductos.size();i++) {
			
			if (listaProductos.get(i).getId() == id) {
				producto = listaProductos.get(i);
			}
			
		}
		
		return producto;
		
	}

}
