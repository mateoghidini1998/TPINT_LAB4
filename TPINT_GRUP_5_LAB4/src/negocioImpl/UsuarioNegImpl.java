package negocioImpl;

import java.util.ArrayList;

import datos.UsuarioDao;
import datosImpl.UsuarioDaoImpl;
import entidad.Usuario;
import negocio.UsuarioNeg;

public class UsuarioNegImpl implements UsuarioNeg{

	private UsuarioDao usuarioDao = new UsuarioDaoImpl();
	
	//Se puede recibir por constructor
	public UsuarioNegImpl(UsuarioDao usuarioDao)
	{
		this.usuarioDao = usuarioDao;
	}
	
	public UsuarioNegImpl()
	{
	}
	
	@Override
	public ArrayList<Usuario> listarUsuarios() {
		return (ArrayList<Usuario>) usuarioDao.obtenerTodos();
	}

	@Override
	public Usuario obtenerUno(String dni) {
		return usuarioDao.obtenerUno(dni);
	}

	@Override
	public boolean insertar(Usuario usuario) {
		return usuarioDao.insertar(usuario);
	}

	@Override
	public boolean editar(Usuario usuario) {
		return usuarioDao.editar(usuario);
	}

	@Override
	public boolean borrar(int id) {
		
		return usuarioDao.borrar(id);
	}
	
	@Override
	public Usuario buscarUsuario(String nombreUsuario, String contrasena) {
		return usuarioDao.buscarUsuario(nombreUsuario, contrasena);
	}

	
	
}