package negocio;

import java.util.ArrayList;
import java.util.List;

import entidad.Medico;
import entidad.Paciente;

public interface MedicoNeg {

	public ArrayList<Medico> listarMedicos();
	public Medico obtenerUno(String dni);
	public boolean insertar(Medico medico);
	public boolean editar(Medico medico);
	public boolean borrar(String dni);
	
	//PARA OBTENER MEDICOS POR ESPECIALIDAD
	public List<Medico> obtenerxEspecialidad(int id);
	
	public List<Medico> obtenerBusqueda(String parametro);
}
