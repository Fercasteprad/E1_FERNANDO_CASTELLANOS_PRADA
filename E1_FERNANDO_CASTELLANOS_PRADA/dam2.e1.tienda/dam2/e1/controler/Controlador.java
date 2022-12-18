package dam2.e1.controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dam2.e1.model.Pedido;
import dam2.e1.model.Producto;
import dam2.e1.model.Usuario;
import dam2.e1.service.CarritoService;
import dam2.e1.service.Operaciones;
import dam2.e1.service.PedidoService;
import dam2.e1.service.ProductoService;
import dam2.e1.service.UsuarioService;

/**
 * Servlet implementation class Controlador
 */
@WebServlet("")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controlador() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	
 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String rutaTomcat = request.getServletContext().getRealPath("");
		String rutaProductos = rutaTomcat+"/ficheros/productos.txt";
		
		
		String idString = request.getParameter("id");
		int idInt = 0;
		if (idString != null) {
			idInt = Operaciones.stringEntero(idString);
		}
		
		String irCarrito = request.getParameter("carrito");
		String irLogin = request.getParameter("login");
		
		if (irCarrito != null) {
			
			//request.setAttribute("carrito", CarritoService.listarCarrito());
			request.setAttribute("pedido", PedidoService.devolverPedido());
			request.getRequestDispatcher("/vistas/carrito.jsp").forward(request, response);
			
		}
		
		else if (irLogin != null) {
			
			request.getRequestDispatcher("/vistas/login.jsp").forward(request, response);
			
		}
		
		else if (idInt > 0) {
			
			Producto paraAnadir = ProductoService.devuelveProductoId(idInt, rutaProductos);
			CarritoService.anadirProducto(paraAnadir);
			if (PedidoService.devolverPedido() == null) {
				Pedido guardar = new Pedido(CarritoService.listarCarrito());
				PedidoService.CrearPedido(guardar);
			}
			else {

				PedidoService.guardarProductos(CarritoService.listarCarrito());

			}
			
			request.setAttribute("pedido", PedidoService.devolverPedido());
			request.setAttribute("productos", Operaciones.stockPostPeticion(rutaProductos));
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}
		
		else {
			
			request.setAttribute("pedido", PedidoService.devolverPedido());
			request.setAttribute("productos", ProductoService.devolverListaProductos(rutaProductos));
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}
			

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String rutaTomcat = request.getServletContext().getRealPath("");
		String rutaProductos = rutaTomcat+"/ficheros/productos.txt";
		String rutaUsuarios = rutaTomcat+"/ficheros/usuarios.txt";
		String pass1 = request.getParameter("pass1");
		String pass2 = request.getParameter("pass2");
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		String comprar = request.getParameter("compra");
		
		if (nombre != null && pass != null) {
			
			Usuario user = UsuarioService.devolverUsuario(rutaUsuarios, nombre, pass);
			
			if (PedidoService.devolverPedido() == null && user.getId() != 0) { //Si el pedido esta vacio y el usuario y contraseña es de un usuario se crea el pedido
				Pedido guardar = new Pedido(user);
				PedidoService.CrearPedido(guardar);
				request.setAttribute("pedido", PedidoService.devolverPedido());
				request.setAttribute("productos", ProductoService.devolverListaProductos(rutaProductos));
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			}
			else if (user.getId() == 0) { //si la contraseña y usuario no pertenece a ningun usuario, le mandamos a la vista de login con el mensaje de error
				String error = "El usuario y/o contraseña son incorrectos";
				request.setAttribute("error", error);
				request.getRequestDispatcher("/vistas/login.jsp").forward(request, response);
			}
			else {
				PedidoService.guardarUsuario(user);
				request.setAttribute("pedido", PedidoService.devolverPedido());
				request.setAttribute("productos", Operaciones.stockPostPeticion(rutaProductos));
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
		}
		
		else if (pass1 != null && pass2 != null) {
			
			String valor_nombre = request.getParameter("nombreForm");
			String valor_apellido = request.getParameter("apellidosForm");
			String valor_email = request.getParameter("emailForm");
			String valor_telefono = request.getParameter("telefonoForm");
			
			if (pass1.equals(pass2)) {

				int telefono = -1;
				if (valor_telefono != null) {
					telefono = Integer.parseInt(valor_telefono);
				}
				if (UsuarioService.existeMail(rutaUsuarios, valor_email)) {
					request.setAttribute("nombre", valor_nombre);
					request.setAttribute("apellidos", valor_apellido);
					request.setAttribute("email", valor_email);
					request.setAttribute("telefono", valor_telefono);
					request.setAttribute("error", "El correo eléctronico ya existe");
					request.getRequestDispatcher("/vistas/altausuario.jsp").forward(request, response);
				}
				else {
					Usuario anadir = new Usuario (UsuarioService.ultimoId(rutaUsuarios),valor_email,pass1,valor_nombre,valor_apellido,telefono);
					Operaciones.anadirUsuario(rutaTomcat, anadir);
					request.setAttribute("productos", Operaciones.stockPostPeticion(rutaProductos));
					request.getRequestDispatcher("index.jsp").forward(request, response);
					
				}
			}
			else {
				request.setAttribute("nombre", valor_nombre);
				request.setAttribute("apellidos", valor_apellido);
				request.setAttribute("email", valor_email);
				request.setAttribute("telefono", valor_telefono);
				request.setAttribute("error", "Las contraseñas no coinciden");
				request.getRequestDispatcher("/vistas/altausuario.jsp").forward(request, response);
			}
			
			
		}
		
		else if (comprar != null) {
			if (PedidoService.devolverPedido().getUsuario() == null) {
				request.getRequestDispatcher("/vistas/login.jsp").forward(request, response);
			}
			else {
				//TODO 1- imprimirPDF 2-ActualizarStock 3-vaciarPedido 4-vaciarCarrito
				Operaciones.crearPDF(PedidoService.devolverPedido(), rutaTomcat);
				Operaciones.sobreescribirStock(Operaciones.stockPostPeticionTXT(rutaProductos), rutaTomcat);
				PedidoService.vaciarPedido();
				CarritoService.vaciarCarrito();
				request.setAttribute("productos", ProductoService.devolverListaProductos(rutaProductos));
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
				
		else if (CarritoService.listarCarrito().size()>0) {
			request.setAttribute("productos", Operaciones.stockPostPeticion(rutaProductos));
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else {
			request.setAttribute("productos", ProductoService.devolverListaProductos(rutaProductos));
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
	}

}
