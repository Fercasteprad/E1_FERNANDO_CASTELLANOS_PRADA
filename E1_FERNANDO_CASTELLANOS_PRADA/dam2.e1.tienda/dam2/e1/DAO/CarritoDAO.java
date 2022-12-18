package dam2.e1.DAO;

import java.util.ArrayList;

import dam2.e1.model.Producto;

public class CarritoDAO {
	
	private static ArrayList<Producto> carrito = crearCarrito();
	
	public static ArrayList<Producto> getCarrito(){
		return carrito;
	}
	
	public static ArrayList<Producto> crearCarrito() {
		
		ArrayList<Producto> carritoCompra = new ArrayList<Producto>();
		return carritoCompra;
		
	}
	
	public static void anadirProducto (Producto producto){
		
		boolean existe = false;
		for (int i = 0;i<carrito.size();i++) {
			int nuevoStock = carrito.get(i).getStock()+1;
			if (producto.getId() == carrito.get(i).getId()) {
				carrito.get(i).setStock(nuevoStock);
				existe = true;
			}
			
		}
		
		if (!existe) {
			producto.setStock(1);
			carrito.add(producto);
		}
		
	}
	
	public static void vaciarCarrito() {
		carrito.clear();
	}

}
