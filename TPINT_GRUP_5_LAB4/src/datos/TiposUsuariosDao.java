package datos;

import java.util.List;

import entidad.TiposUsuario;

public interface TiposUsuariosDao {
	public List<TiposUsuario> obtenerTodos();
	public TiposUsuario obtenerUno(int id);
	public boolean insertar(TiposUsuario tipoUsuario);
	public boolean editar(TiposUsuario tipoUsuario);
	public boolean borrar(int id);
}
