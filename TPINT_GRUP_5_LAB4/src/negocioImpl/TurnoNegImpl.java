package negocioImpl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import datos.TurnoDao;
import datosImpl.PacienteDaoImpl;
import datosImpl.TurnoDaoImpl;
import entidad.Paciente;
import entidad.Turno;
import negocio.TurnoNeg;


public class TurnoNegImpl implements TurnoNeg {
	
private TurnoDao turnoDao = new TurnoDaoImpl();
	
	//Se puede recibir por constructor
	public TurnoNegImpl(TurnoDao turnoDao)
	{
		this.turnoDao = turnoDao;
	}
	
	public TurnoNegImpl()
	{
	}
	
	@Override
	public ArrayList<Turno> listarTurnos() {
		return (ArrayList<Turno>) turnoDao.obtenerTodos();
	}

	@Override
	public Turno obtenerUno(int id) {
		return turnoDao.obtenerUno(id);
	}

	@Override
	public boolean insertar(Turno turno) {
		return turnoDao.insertar(turno);
	}

	@Override
	public boolean editar(Turno turno) {
		return turnoDao.editar(turno);
	}
	
	public boolean agregarObservacion(Turno turno) {
		return turnoDao.agregarObservacion(turno);
	}

	@Override
	public boolean cambiarEstadoAusente(int id) {
		
		return turnoDao.cambiarEstadoAusente(id);
	}
	
	@Override
	public boolean cambiarEstadoLibre(int id) {
		
		return turnoDao.cambiarEstadoLibre(id);
	}

	
	@Override
	public boolean cambiarEstadoOcupado(int id) {
		
		return turnoDao.cambiarEstadoOcupado(id);
	}

	
	@Override
	public boolean cambiarEstadoPresente(int id) {
		
		return turnoDao.cambiarEstadoPresente(id);
	}

	@Override
	public List<Turno> obtenerPorDniMedico(String dniMedico) {
		return turnoDao.obtenerPorDniMedico(dniMedico);
	}
	
	//BUSQUEDA DINAMICA
	public List<Turno> obtenerBusqueda(String parametro){
		return (ArrayList<Turno>) turnoDao.obtenerBusqueda(parametro);
	}
	
	public List<Turno> obtenerBusquedaxEstado(String parametro){
		return (ArrayList<Turno>) turnoDao.obtenerBusquedaxEstado(parametro);
	}
	
	//PARA CONSULTA DE TURNOS 
		public boolean consultaTurnoxFechayHoraDNIMed(Turno comparacion) {
			return turnoDao.consultaTurnoxFechayHoraDNIMed(comparacion);
		}
		



}
