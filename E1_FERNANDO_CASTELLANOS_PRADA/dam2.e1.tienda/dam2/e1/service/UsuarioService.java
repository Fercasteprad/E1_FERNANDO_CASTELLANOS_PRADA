package dam2.e1.service;

import java.io.File;

import dam2.e1.DAO.UsuarioDAO;
import dam2.e1.model.Usuario;

public class UsuarioService {
	
	public static Usuario devolverUsuario (String ruta, String email, String pass) {
		
		File archivo = Rutaservice.devolverArchivo(ruta);
		UsuarioDAO arrayUsuarios = new UsuarioDAO();
		arrayUsuarios.setUsuarios(archivo);
		Usuario devolver = arrayUsuarios.devolverUsuario(email, pass);
		return devolver;
		
	}
	
	public static boolean existeMail (String ruta, String email) {
		
		File archivo = Rutaservice.devolverArchivo(ruta);
		UsuarioDAO arrayUsuarios = new UsuarioDAO();
		arrayUsuarios.setUsuarios(archivo);
		boolean devolver = arrayUsuarios.existeMail(email);
		return devolver;
		
	}
	
	public static int ultimoId (String ruta) {
		File archivo = Rutaservice.devolverArchivo(ruta);
		UsuarioDAO arrayUsuarios = new UsuarioDAO();
		arrayUsuarios.setUsuarios(archivo);
		int devolver = arrayUsuarios.ultimoId();
		return devolver;
	}

}
