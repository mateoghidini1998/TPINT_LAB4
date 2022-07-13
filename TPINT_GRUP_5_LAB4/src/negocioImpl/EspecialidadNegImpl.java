package negocioImpl;

import java.util.ArrayList;
import datos.EspecialidadDao;
import datosImpl.EspecialidadDaoImpl;
import entidad.Especialidad;
import negocio.EspecialidadNeg;


public class EspecialidadNegImpl implements EspecialidadNeg{
	
	private EspecialidadDao especialidadDao = new EspecialidadDaoImpl();

	//Se puede recibir por constructor
		public EspecialidadNegImpl (EspecialidadDao especialidadDao)
		{
			this.especialidadDao= especialidadDao;
		}
		
		public EspecialidadNegImpl ()
		{
		}
		
		@Override
		public ArrayList<Especialidad> listarEspecialidades() {
			return (ArrayList<Especialidad>) especialidadDao.obtenerTodos();
		}

		@Override
		public Especialidad obtenerUno(int id) {
			return especialidadDao.obtenerUno(id);
		}

		@Override
		public boolean insertar(Especialidad especialidad) {
			return especialidadDao.insertar(especialidad);
		}
		@Override
		public boolean editar(Especialidad especialidad) {
			return especialidadDao.editar(especialidad);
		}

		@Override
		public boolean borrar(int id) {
			
			return especialidadDao.borrar(id);
		}

		
	
}
