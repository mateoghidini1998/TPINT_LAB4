package negocio;

import java.util.ArrayList;

import entidad.Usuario;


public interface UsuarioNeg {

	public ArrayList<Usuario> listarUsuarios();
	public Usuario obtenerUno(String dni);
	public boolean insertar(Usuario usuario);
	public boolean editar(Usuario usuario);
	public boolean borrar(int id);
	public Usuario buscarUsuario(String nombreUsuario, String contrasena);
}
