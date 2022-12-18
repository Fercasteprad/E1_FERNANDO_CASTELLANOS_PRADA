package dam2.e1.DAO;

import java.io.File;
import java.util.ArrayList;

import dam2.e1.model.Usuario;
import dam2.e1.service.Operaciones;

public class UsuarioDAO {
	
	private ArrayList<Usuario> usuarios;
	
	public UsuarioDAO() {
		
	}
	
	public ArrayList<Usuario> getUsuarios(){
		return usuarios;
	}
			
	public void setUsuarios(File ruta) {
		
		//Devuelve un ArrayList de los usuarios que esten escritos en el txt
		
		ArrayList<String> textoPlano = Operaciones.leerFichero(ruta); //Devuelve los atributos planos
		ArrayList<Usuario> listaUsuarios = Operaciones.listarUsuarios(textoPlano); //Convierte los atributos en usuarios
	
		this.usuarios = listaUsuarios;
		
	}
	
	public Usuario devolverUsuario (String email, String pass) {
		
		Usuario auxiliar = new Usuario();
		for (int i = 0; i<usuarios.size(); i++) {
			Usuario comparador = usuarios.get(i);
			if (comparador.getEmail().equalsIgnoreCase(email) && comparador.getPass().equals(pass)) {
				auxiliar = comparador;
			}
			
		}
		return auxiliar;
	}
	
	public boolean existeMail (String email) {
		
		boolean existe = false;
		for (int i = 0; i<usuarios.size();i++) {
			
			if (usuarios.get(i).getEmail().equals(email)) {
				existe = true;
			}
			
		}
		return existe;
	}
	
	public int ultimoId() {
				
		int id = usuarios.get(usuarios.size()-1).getId()+1;

		return id;
	}

}
