package negocio;

import java.util.ArrayList;
import java.util.List;

import entidad.DisponibilidadxMedico;

public interface DisponibilidadxMedicoNeg {
	public List<DisponibilidadxMedico> obtenerTodos();
	public DisponibilidadxMedico obtenerUno(int id );
	
	//BUSCA LOS DIAS QUE TRABAJA UN MEDICO, BUSCANDO POR DNI
	public List<DisponibilidadxMedico> obtenerDiasxMedico(String dnimedico);
	
	//VERIFICA SI EL MEDICO PUEDE O NO TRABAJAR EN ESE DIA DE LA SEMANA
	public boolean verificar(DisponibilidadxMedico consulta);
	
	//ESTOS DOS SE VAN A USAR PARA EDITAR LOS PACIENTES
	public boolean insertar(DisponibilidadxMedico dxmedico);
	public boolean borrar(String dni); //ES BAJA FISICA


}
