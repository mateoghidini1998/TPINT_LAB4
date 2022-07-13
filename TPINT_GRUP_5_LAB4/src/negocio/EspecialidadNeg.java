package negocio;

import java.util.ArrayList;

import entidad.Especialidad;

public interface EspecialidadNeg {
	public ArrayList<Especialidad> listarEspecialidades();
	public Especialidad obtenerUno(int id );
	public boolean insertar(Especialidad especialidad);
	public boolean editar(Especialidad especialidad);
	public boolean borrar(int id);

}
