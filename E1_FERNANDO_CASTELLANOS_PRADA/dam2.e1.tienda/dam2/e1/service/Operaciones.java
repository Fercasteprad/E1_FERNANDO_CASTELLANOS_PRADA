package dam2.e1.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import dam2.e1.model.Pedido;
import dam2.e1.model.Producto;
import dam2.e1.model.Usuario;

public class Operaciones {
	
	//Devuelve los atributos que haya en un fichero
	public static ArrayList<String> leerFichero(File fichero) {

		ArrayList<String> atributos = new ArrayList<String>();

		try {

			FileReader leoArchivo = new FileReader(fichero);
			@SuppressWarnings("resource")
			BufferedReader loLeo = new BufferedReader(leoArchivo);
			String comparador = loLeo.readLine();

			while (comparador != null) {

				int contador = 0;

				for (int i = 0; i < comparador.length(); i++) {

					String comparadorDosPuntos = comparador.substring(i,i+1);
					String palabra;

					if (comparadorDosPuntos.equals(":") && contador == 0) {
						
						palabra = comparador.substring(0, i);
						atributos.add(palabra);
						contador = i + 1;

					}

					else if (comparadorDosPuntos.equals(":") && contador > 0) {

						palabra = comparador.substring(contador, i);
						atributos.add(palabra);
						contador = i + 1;

					}

				}
				
				comparador = loLeo.readLine();

			}
			
			loLeo.close();
			leoArchivo.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return atributos;

	}
	
	public static ArrayList<Producto> listarProductos (ArrayList<String> textoPlano){
		
		ArrayList<Producto> productos = new ArrayList<Producto>();
		int id = -1;
		String nombre = "";
		String descripcion ="";
		int precio = -1;
		int stock = -1;
		
		int contador = 0;
		
		for (int i = 0; i<textoPlano.size();i++) {
			
			if (contador == 0) {
				id = stringEntero(textoPlano.get(i));
				contador++;
			}
			
			else if (contador == 1) {
				nombre = textoPlano.get(i);
				contador++;
			}
			
			else if (contador == 2) {
				descripcion = textoPlano.get(i);
				contador++;
			}
			else if (contador == 3) {
				precio = stringEntero(textoPlano.get(i));
				contador++;
			}
			
			else if (contador == 4) {
				stock = stringEntero(textoPlano.get(i));
				contador = 0;
				Producto nuevoProducto = new Producto (id,nombre,descripcion,precio,stock);
				productos.add(nuevoProducto);
			}
			
		}
		
		return productos;
		
	}
	
	public static int stringEntero (String texto) {
		int entero = Integer.parseInt(texto);
		return entero;
	}
	
	public static ArrayList<Usuario> listarUsuarios (ArrayList<String> textoPlano){
		
		ArrayList<Usuario> Usuarios = new ArrayList<Usuario>();
		int id = -1;
		String email = "";
		String pass = "";
		String nombre = "";
		String apellidos = "";
		int telefono = -1;
		
		int contador = 0;
		
		for (int i = 0; i<textoPlano.size();i++) {
			
			if (contador == 0) {
				id = stringEntero(textoPlano.get(i));
				contador++;
			}
			
			else if (contador == 1) {
				email = textoPlano.get(i);
				contador++;
			}
			
			else if (contador == 2) {
				pass = textoPlano.get(i);
				contador++;
			}
			else if (contador == 3) {
				nombre = textoPlano.get(i);
				contador++;
			}
			
			else if (contador == 4) {
				apellidos = textoPlano.get(i);
				contador++;
			}
			
			else if (contador == 5) {
				telefono = stringEntero(textoPlano.get(i));
				contador = 0;
				Usuario nuevoUsuario = new Usuario (id,email,pass,nombre,apellidos,telefono);
				Usuarios.add(nuevoUsuario);
			}
			
		}
		
		return Usuarios;
		
	}
	
	public static ArrayList<Producto> stockPostPeticion (String fichero) {
		ArrayList<Producto> listaProductos = ProductoService.devolverListaProductos(fichero);
		ArrayList<Producto> listaCarrito = CarritoService.listarCarrito();
		for (int i = 0; i < listaProductos.size();i++) {
			Producto comparadorProducto = listaProductos.get(i);
			for (int j = 0; j < listaCarrito.size();j++) {
				Producto comparadorCarrito = listaCarrito.get(j);
				if (comparadorProducto.getId() == comparadorCarrito.getId()) {
					int nuevoStock = comparadorProducto.getStock() - comparadorCarrito.getStock();
					listaProductos.get(i).setStock(nuevoStock);
				}
			}
		}
		return listaProductos;
	}
	
	public static ArrayList<Producto> stockPostPeticionTXT (String fichero) {
		ArrayList<Producto> listaProductos = ProductoService.devolverListaProductosConStock0(fichero);
		ArrayList<Producto> listaCarrito = CarritoService.listarCarrito();
		for (int i = 0; i < listaProductos.size();i++) {
			Producto comparadorProducto = listaProductos.get(i);
			for (int j = 0; j < listaCarrito.size();j++) {
				Producto comparadorCarrito = listaCarrito.get(j);
				if (comparadorProducto.getId() == comparadorCarrito.getId()) {
					int nuevoStock = comparadorProducto.getStock() - comparadorCarrito.getStock();
					listaProductos.get(i).setStock(nuevoStock);
				}
			}
		}
		return listaProductos;
	}
	
	public static void crearPDF (Pedido pedido , String ruta) {
				
		PdfWriter writer = null;
		Document documento = new Document(PageSize.A4, 20, 20, 70, 50);

		try {
			
			writer = PdfWriter.getInstance(documento, new FileOutputStream(ruta+"/ficheros/pedido_"+pedido.getUsuario().getNombre()+".pdf"));
			documento.open();
			Paragraph productos = new Paragraph();
			productos.add("FACTURA COMERCIAL\n");
			productos.add("Nombre: "+pedido.getUsuario().getNombre()+" "+pedido.getUsuario().getApellidos()+"\n"); //Le ponemos el nombre del usuario y sus apellidos del pedido
			productos.add("Correo: "+pedido.getUsuario().getEmail()+"\n");
			productos.add("Telefono: "+pedido.getUsuario().getTelefono()+"\n\n\n\n");
			
			for (int i = 0; i<pedido.getProductos().size();i++) {
				Producto escribirProducto = pedido.getProductos().get(i);
				productos.add(escribirProducto.getNombre()+"\t x"+escribirProducto.getStock()+"\t precio unitario = "+escribirProducto.getPrecio()/escribirProducto.getStock()+"\t precio total = "+escribirProducto.getPrecio()+"\n");	
			}
			
			int total = 0;			
			for (int i = 0; i<pedido.getProductos().size();i++) {
				Producto escribirProducto = pedido.getProductos().get(i);
				total = total + escribirProducto.getPrecio();
			}
			
			productos.add("Total = "+total);
			documento.close();
			writer.close();
						
			
		} catch (Exception e) {
			e.getMessage();
		}
		
	}
	
	
	public static void sobreescribirStock (ArrayList<Producto> productos, String ruta) {
		
		File newStock = new File(ruta+"/ficheros/auxiliar.txt"); //Creamos un archivo temporal
		File usuarios = new File(ruta+"/ficheros/productos.txt");  //Llamamos a nuestro documento
		
		try {
			
			FileWriter sobreStock = new FileWriter(newStock);
			BufferedWriter escribirStock = new BufferedWriter(sobreStock);
			int contador = 0;
				for (int i = 0;i<productos.size();i++) {
					Producto comparadorP = productos.get(i);
					String comparadorString = escribirProductoTXT(comparadorP);
					if (contador == 0) {
						contador++;
						escribirStock.write(comparadorString);
					}
					else {
						escribirStock.newLine();
						escribirStock.write(comparadorString);
					}
						
				}
				
			escribirStock.close();
			sobreStock.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Error al escribir el archivo");
		} catch (IOException e) {
			System.out.println("Error al escribir el archivo");
		}
		
		usuarios.delete(); //Se elimina el archivo records
		File nuevoNombre = new File(ruta+"/ficheros/productos.txt"); 
		newStock.renameTo(nuevoNombre); //Se renombra el archivo auxiliar
		
	}
	
	public static String escribirProductoTXT (Producto producto) {
		
		String textoPlano = producto.getId()+":"+producto.getNombre()+":"+producto.getDescripcion()+":"+producto.getPrecio()+":"+producto.getStock()+":";
		return textoPlano;
		
	}
	
	public static String escribirUsuarioTXT (Usuario usuario) {
		
		String textoPlano = usuario.getId()+":"+usuario.getEmail()+":"+usuario.getPass()+":"+usuario.getNombre()+":"+usuario.getApellidos()+":"+usuario.getTelefono()+":";
		return textoPlano;
		
	}
	
	public static void anadirUsuario (String ruta, Usuario usuario) {
		
		File usuarios = new File(ruta+"/ficheros/usuarios.txt");  //Llamamos a nuestro documento
		
		try {
			
			FileWriter sobreStock = new FileWriter(usuarios,true);
			BufferedWriter escribirStock = new BufferedWriter(sobreStock);
			String escribirLinea = escribirUsuarioTXT(usuario);
			escribirStock.newLine();
			escribirStock.write(escribirLinea);
			escribirStock.close();
			sobreStock.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Error al escribir el archivo");
		} catch (IOException e) {
			System.out.println("Error");
		}
		
		
	}

}
