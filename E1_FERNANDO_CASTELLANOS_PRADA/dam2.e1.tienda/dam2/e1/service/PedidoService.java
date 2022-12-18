package dam2.e1.service;

import java.util.ArrayList;

import dam2.e1.DAO.PedidoDAO;
import dam2.e1.model.Pedido;
import dam2.e1.model.Producto;
import dam2.e1.model.Usuario;

public class PedidoService {

	public static Pedido CrearPedido(Pedido pedido) {
		PedidoDAO.setPedido(pedido);
		return PedidoDAO.getPedido();

	}

	public static void vaciarPedido() {

		PedidoDAO.vaciarPedido();

	}

	public static Pedido devolverPedido() {

		return PedidoDAO.getPedido();

	}

	public static void guardarPedido(Pedido pedido) {
		PedidoDAO.setPedido(pedido);
	}
	
	public static void guardarUsuario(Usuario user) {
		Pedido guardado = devolverPedido();
		guardado.setUsuario(user);
		guardarPedido(guardado);
		
	}
	
	public static void guardarProductos(ArrayList<Producto> carrito) {
		Pedido guardado = devolverPedido();
		guardado.setProductos(carrito);
		guardarPedido(guardado);
		
	}

}
