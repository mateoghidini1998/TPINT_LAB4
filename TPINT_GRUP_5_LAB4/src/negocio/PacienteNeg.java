package negocio;

import java.util.ArrayList;
import java.util.List;

import entidad.Paciente;

public interface PacienteNeg {

	public ArrayList<Paciente> listarPacientes();
	public Paciente obtenerUno(String dni);
	public boolean insertar(Paciente paciente);
	public boolean editar(Paciente paciente);
	public boolean borrar(String dni);
	
	public List<Paciente> obtenerBusqueda(String parametro);

}
