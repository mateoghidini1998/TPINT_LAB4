package datos;
import java.util.List;
import entidad.Medico;
import entidad.Paciente;

public interface MedicoDao{

	public List<Medico> obtenerTodos();
	public Medico obtenerUno(String dni);
	public boolean insertar(Medico medico);
	public boolean editar(Medico medico);
	public boolean borrar(String dni);
	
	//PARA OBTENER MEDICOS POR ESPECIALIDAD
	public List<Medico> obtenerxEspecialidad(int id);
	
	//PARA BUSQUEDA DINAMICA
	public List<Medico> obtenerBusqueda(String parametro);
}
