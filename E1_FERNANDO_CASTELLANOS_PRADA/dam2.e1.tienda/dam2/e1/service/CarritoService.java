package dam2.e1.service;

import java.util.ArrayList;

import dam2.e1.DAO.CarritoDAO;
import dam2.e1.model.Producto;

public class CarritoService {
	
	public static ArrayList<Producto> listarCarrito (){
		
		return CarritoDAO.getCarrito();
		
	}
	
	public static void anadirProducto(Producto producto) {
		CarritoDAO.anadirProducto(producto);
	}
	
	public static void vaciarCarrito() {
		CarritoDAO.vaciarCarrito();
	}

}
