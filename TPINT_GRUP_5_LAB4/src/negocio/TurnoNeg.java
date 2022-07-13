package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entidad.Paciente;
import entidad.Turno;

public interface TurnoNeg {

	public ArrayList<Turno> listarTurnos();
	public List<Turno> obtenerPorDniMedico(String dniMedico);
	public Turno obtenerUno(int id);
	public boolean insertar(Turno turno);
	public boolean editar(Turno turno);
	public boolean agregarObservacion(Turno turno);
	public boolean cambiarEstadoAusente(int id);
	public boolean cambiarEstadoLibre(int id);
	public boolean cambiarEstadoOcupado(int id);
	public boolean cambiarEstadoPresente(int id);
	
	//PARA CONSULTA DE TURNOS
	public boolean consultaTurnoxFechayHoraDNIMed(Turno comparacion);
	
	public List<Turno> obtenerBusqueda(String parametro);
	public List<Turno> obtenerBusquedaxEstado(String parametro);
	
}
