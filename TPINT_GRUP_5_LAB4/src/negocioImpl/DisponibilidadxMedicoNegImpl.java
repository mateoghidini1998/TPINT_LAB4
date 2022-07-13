package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import datos.DisponibilidadxMedicoDao;
import datosImpl.DisponibilidadxMedicoDaoImpl;
import entidad.DisponibilidadxMedico;
import entidad.Paciente;
import negocio.DisponibilidadxMedicoNeg;


public class DisponibilidadxMedicoNegImpl implements DisponibilidadxMedicoNeg{
	
	private DisponibilidadxMedicoDao dxmedicoDao = new DisponibilidadxMedicoDaoImpl();

	//Se puede recibir por constructor
		public DisponibilidadxMedicoNegImpl (DisponibilidadxMedicoDao dxmedicoDao)
		{
			this.dxmedicoDao= dxmedicoDao;
		}
		
		public DisponibilidadxMedicoNegImpl ()
		{
		}
		
		

		@Override
		public DisponibilidadxMedico obtenerUno(int id) {
			return dxmedicoDao.obtenerUno(id);
		}

		@Override
		public boolean insertar(DisponibilidadxMedico dxmedico) {
			return dxmedicoDao.insertar(dxmedico);
		}
		
		

		@Override
		public boolean borrar(String dni) {
			
			return dxmedicoDao.borrar(dni);
		}

		@Override
		public List<DisponibilidadxMedico> obtenerTodos() {
			return (ArrayList<DisponibilidadxMedico>) dxmedicoDao.obtenerTodos();
		}

		@Override
		public List<DisponibilidadxMedico> obtenerDiasxMedico(String dnimedico) {
			return (ArrayList<DisponibilidadxMedico>) dxmedicoDao.obtenerDiasxMedico(dnimedico);
		}
		
		public boolean verificar(DisponibilidadxMedico consulta) {
			return dxmedicoDao.verificar(consulta);
		}

		
	
}
