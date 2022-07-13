package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import datos.PacienteDao;
import datosImpl.PacienteDaoImpl;
import entidad.Paciente;
import negocio.PacienteNeg;

public class PacienteNegImpl implements PacienteNeg{

	private PacienteDao pacienteDao = new PacienteDaoImpl();
	
	//Se puede recibir por constructor
	public PacienteNegImpl(PacienteDao pacienteDao)
	{
		this.pacienteDao = pacienteDao;
	}
	
	public PacienteNegImpl()
	{
	}
	
	@Override
	public ArrayList<Paciente> listarPacientes() {
		return (ArrayList<Paciente>) pacienteDao.obtenerTodos();
	}

	@Override
	public Paciente obtenerUno(String dni) {
		return pacienteDao.obtenerUno(dni);
	}

	@Override
	public boolean insertar(Paciente paciente) {
		return pacienteDao.insertar(paciente);
	}

	@Override
	public boolean editar(Paciente paciente) {
		return pacienteDao.editar(paciente);
	}

	@Override
	public boolean borrar(String dni) {
		
		return pacienteDao.borrar(dni);
	}
	
	public List<Paciente> obtenerBusqueda(String parametro){
		return (ArrayList<Paciente>) pacienteDao.obtenerBusqueda(parametro);
	}

	

	
	
}