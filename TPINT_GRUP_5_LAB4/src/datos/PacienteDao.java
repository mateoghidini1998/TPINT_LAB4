package datos;
import java.util.List;

import entidad.Paciente;

public interface PacienteDao{

	public List<Paciente> obtenerTodos();
	public Paciente obtenerUno(String dni);
	public boolean insertar(Paciente paciente);
	public boolean editar(Paciente paciente);
	public boolean borrar(String dni);
	
	//PARA BUSQUEDA DINAMICA
	public List<Paciente> obtenerBusqueda(String parametro);
	
	
}
