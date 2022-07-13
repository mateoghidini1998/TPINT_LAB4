package datos;
import java.util.List;
import entidad.Usuario;

public interface UsuarioDao{

	public List<Usuario> obtenerTodos();
	
	public boolean insertar(Usuario usuario);
	public boolean editar(Usuario paciente);
	public boolean borrar(int id);
	public Usuario buscarUsuario(String nombreUsuario, String contrasena);
	public Usuario obtenerUno(String dni);
	
}

