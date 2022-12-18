package dam2.e1.DAO;

import dam2.e1.model.Pedido;

public class PedidoDAO {
	
	private static Pedido pedido;

	public static Pedido getPedido() {
		return pedido;
	}

	public static void setPedido(Pedido pedido) {
		PedidoDAO.pedido = pedido;
	}
	
	public static void vaciarPedido() {
		Pedido vacio = new Pedido();
		PedidoDAO.pedido = vacio;
	}

}
