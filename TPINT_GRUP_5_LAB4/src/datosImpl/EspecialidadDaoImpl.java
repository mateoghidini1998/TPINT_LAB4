package datosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.EspecialidadDao;
import datos.MedicoDao;
import entidad.Especialidad;
import entidad.Medico;

public class EspecialidadDaoImpl  implements EspecialidadDao{
	private Conexion cn;

	public EspecialidadDaoImpl()
	{
		
	}
	
	
	@Override
	public List<Especialidad> obtenerTodos() {
		cn = new Conexion();
		cn.Open();
		 List<Especialidad> list = new ArrayList<Especialidad>();
		 try
		 {
			 ResultSet rs= cn.query("Select ID,descripcion from  Especialidades ");
			 while(rs.next())
			 {
				 Especialidad especialidad = new Especialidad();
				 
				 especialidad.setID(rs.getInt("ID"));
				 especialidad.setDescripción(rs.getString("descripcion"));	
				 list.add(especialidad);
			 }
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		 return list;
	}


	@Override
	public Especialidad obtenerUno(int id) {
		cn = new Conexion();
		cn.Open();
		Especialidad especialidad = new Especialidad();
		try
		 {
			 ResultSet rs= cn.query("Select id, descripcion from especialidades where id = " + id);
			 rs.next();
			 
			 especialidad.setID(rs.getInt("id"));
			 especialidad.setDescripción(rs.getString("descripcion"));
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		return especialidad;
	}


	@Override
	public boolean insertar(Especialidad especialidad) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean editar(Especialidad especialidad) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean borrar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	
	}


	
	



