package datos;

import java.util.List;

import entidad.Especialidad;


public interface EspecialidadDao {
	public List<Especialidad> obtenerTodos();
	public Especialidad obtenerUno(int id );
	public boolean insertar(Especialidad especialidad);
	public boolean editar(Especialidad especialidad);
	public boolean borrar(int id);

}
