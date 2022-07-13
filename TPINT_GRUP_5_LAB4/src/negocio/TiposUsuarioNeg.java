package negocio;

import java.util.ArrayList;

import entidad.TiposUsuario;
import entidad.Usuario;

public interface TiposUsuarioNeg {

	public ArrayList<TiposUsuario> listarTiposUsuarios();
	public TiposUsuario obtenerUno(int id);
	public boolean insertar(TiposUsuario tiposUsuario);
	//public boolean editar(TiposUsuario tiposUsuario);
	//public boolean borrar(int id);
	//public TiposUsuario buscarTiposUsuario(String nombreUsuario, String contrasena);
	
}
