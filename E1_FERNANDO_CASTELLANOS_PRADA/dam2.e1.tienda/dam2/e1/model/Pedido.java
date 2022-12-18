package dam2.e1.model;

import java.util.ArrayList;

public class Pedido {
	
	private Usuario usuario;
	private ArrayList <Producto> productos;
	
	
	
	public Pedido() {
	}
	
	public Pedido(Usuario usuario, ArrayList<Producto> productos) {
		this.usuario = usuario;
		this.productos = productos;
	}
	
	public Pedido(ArrayList<Producto> productos) {
		this.usuario = null;
		this.productos = productos;
	}
	
	public Pedido(Usuario usuario) {
		this.usuario = usuario;
		this.productos = null;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public ArrayList<Producto> getProductos() {
		return productos;
	}
	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}
	
	

}
