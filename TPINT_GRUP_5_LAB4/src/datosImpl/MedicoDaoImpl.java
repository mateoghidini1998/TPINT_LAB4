package datosImpl;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import datos.MedicoDao;
import entidad.Especialidad;
import entidad.Medico;
import entidad.Paciente;

public class MedicoDaoImpl implements MedicoDao{
	
	private Conexion cn;

	public MedicoDaoImpl()
	{
		
	}
	
	
	@Override
	public List<Medico> obtenerTodos() {
		cn = new Conexion();
		cn.Open();
		 List<Medico> list = new ArrayList<Medico>();
		 try
		 {
			 ResultSet rs= cn.query("Select dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, e.id, e.descripcion, estado from Medicos inner join Especialidades as e on id_especialidad=e.ID WHERE estado = 1");
			 while(rs.next())
			 {
				 Medico medico = new Medico();
				 medico.setDni(rs.getString("dni"));
				 medico.setNombre(rs.getString("nombre"));
				 medico.setApellido(rs.getString("apellido"));
				 medico.setSexo(rs.getString("sexo"));
				 medico.setNacionalidad(rs.getString("nacionalidad"));
				 medico.setFechaNac(rs.getDate("fechaNac"));
				 medico.setDireccion(rs.getString("direccion"));
				 medico.setLocalidad(rs.getString("localidad"));
				 medico.setProvincia(rs.getString("provincia"));
				 medico.setTelefono(rs.getString("telefono"));
				 medico.setCelular(rs.getString("celular"));
				 medico.setCorreo(rs.getString("correo"));
				 medico.setEstado(rs.getInt("estado"));
				 
				 Especialidad esp = new Especialidad();
				 esp.setID(rs.getInt("e.id"));
				 esp.setDescripción(rs.getString("e.descripcion"));
		 
				 medico.setID_especialidad(esp);	
				 list.add(medico);
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
	public Medico obtenerUno(String dni) {
		cn = new Conexion();
		cn.Open();
		Medico medico = new Medico();
		try
		 {
			 ResultSet rs= cn.query("Select dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, e.id, e.descripcion, estado from Medicos inner join Especialidades as e on id_especialidad=e.ID where dni = " + dni);
			 rs.next();
			 
			 medico.setDni(rs.getString("dni"));
			 medico.setNombre(rs.getString("nombre"));
			 medico.setApellido(rs.getString("apellido"));
			 medico.setSexo(rs.getString("sexo"));
			 medico.setNacionalidad(rs.getString("nacionalidad"));
			 medico.setFechaNac(rs.getDate("fechaNac"));
			 medico.setDireccion(rs.getString("direccion"));
			 medico.setLocalidad(rs.getString("localidad"));
			 medico.setProvincia(rs.getString("provincia"));
			 medico.setTelefono(rs.getString("telefono"));
			 medico.setCelular(rs.getString("celular"));
			 medico.setCorreo(rs.getString("correo"));
			 Especialidad esp = new Especialidad();
			 esp.setID(rs.getInt("e.id"));
			 esp.setDescripción(rs.getString("e.descripcion"));
	 
			 medico.setID_especialidad(esp);	
			 medico.setEstado(rs.getInt("estado"));
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		return medico;
	}

	
	@Override
	public boolean insertar(Medico medico) {
		
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	
		
		
		//IMPORTANTISIMO PARA INSERTAR FECHA
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");		
		String fechaComoCadena = formato.format(medico.getFechaNac());
		

		String query = "INSERT INTO Medicos (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado, id_especialidad) VALUES ('"+medico.getDni()+"','"+medico.getNombre()+"','"+medico.getApellido()+"', '"+medico.getSexo()+"', '"+medico.getNacionalidad()+"', '"+fechaComoCadena+"', '"+medico.getDireccion()+"', '"+medico.getLocalidad()+"', '"+medico.getProvincia()+"', '"+medico.getTelefono()+"', '"+medico.getCelular()+"', '"+medico.getCorreo()+"', '"+medico.isEstado()+"', '"+medico.getID_especialidad().getID()+"')";
		System.out.println(query);
		try
		 {
			estado=cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("LLEGO ACA");
		}
		finally
		{
			cn.close();
		}
		return estado;
	}

	@Override
	public boolean editar(Medico medico) {
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	
		
		//IMPORTANTISIMO PARA INSERTAR FECHA
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");		
				String fechaComoCadena = formato.format(medico.getFechaNac());

		String query = "UPDATE Medicos SET dni='"+medico.getDni()+"', nombre='"+medico.getNombre()+"', apellido='"+medico.getApellido()+"', sexo = '"+medico.getSexo()+"', nacionalidad = '"+medico.getNacionalidad()+"', fechaNac='"+fechaComoCadena+"', direccion='"+medico.getDireccion()+"', localidad = '"+medico.getLocalidad()+"', provincia = '"+medico.getProvincia()+"', telefono='"+medico.getTelefono()+"', celular = '"+medico.getCelular()+"', correo='"+medico.getCorreo()+"', estado = '"+medico.isEstado()+"', id_especialidad = '"+medico.getID_especialidad().getID()+"' WHERE dni='"+medico.getDni()+"'";
		try
		 {
			estado=cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return estado;
	}
	
	@Override
	public boolean borrar(String dni) {
		boolean estado=true;
		cn = new Conexion();
		cn.Open();		 
		String query = "UPDATE Medicos SET estado=0 WHERE dni="+"'"+dni+"'";
		try
		 {
			estado=cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return estado;
	}
	
	
	public List<Medico> obtenerBusqueda(String parametro){
	
	cn = new Conexion();
	cn.Open();
	 List<Medico> list = new ArrayList<Medico>();
	 try
	 {
		 ResultSet rs= cn.query("Select dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, e.id, e.descripcion, estado from Medicos inner join Especialidades as e on id_especialidad=e.ID WHERE estado = 1 AND dni LIKE '%"+parametro+"%' OR nombre LIKE '%"+parametro+"%' OR apellido LIKE '%"+parametro+"%' OR e.descripcion LIKE '%"+parametro+"%'");
		 while(rs.next())
		 {
			 Medico medico = new Medico();
			 medico.setDni(rs.getString("dni"));
			 medico.setNombre(rs.getString("nombre"));
			 medico.setApellido(rs.getString("apellido"));
			 medico.setSexo(rs.getString("sexo"));
			 medico.setNacionalidad(rs.getString("nacionalidad"));
			 medico.setFechaNac(rs.getDate("fechaNac"));
			 medico.setDireccion(rs.getString("direccion"));
			 medico.setLocalidad(rs.getString("localidad"));
			 medico.setProvincia(rs.getString("provincia"));
			 medico.setTelefono(rs.getString("telefono"));
			 medico.setCelular(rs.getString("celular"));
			 medico.setCorreo(rs.getString("correo"));
			 medico.setEstado(rs.getInt("estado"));
			 
			 Especialidad esp = new Especialidad();
			 esp.setID(rs.getInt("e.id"));
			 esp.setDescripción(rs.getString("e.descripcion"));
	 
			 medico.setID_especialidad(esp);	
			 list.add(medico);
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
	
	public List<Medico> obtenerxEspecialidad(int id){
		
		cn = new Conexion();
		cn.Open();
		 List<Medico> list = new ArrayList<Medico>();
		 try
		 {
			 ResultSet rs= cn.query("Select dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, e.id, e.descripcion, estado from Medicos inner join Especialidades as e on id_especialidad=e.ID WHERE estado = 1 AND id_especialidad = "+id+" ");
			 while(rs.next())
			 {
				 Medico medico = new Medico();
				 medico.setDni(rs.getString("dni"));
				 medico.setNombre(rs.getString("nombre"));
				 medico.setApellido(rs.getString("apellido"));
				 medico.setSexo(rs.getString("sexo"));
				 medico.setNacionalidad(rs.getString("nacionalidad"));
				 medico.setFechaNac(rs.getDate("fechaNac"));
				 medico.setDireccion(rs.getString("direccion"));
				 medico.setLocalidad(rs.getString("localidad"));
				 medico.setProvincia(rs.getString("provincia"));
				 medico.setTelefono(rs.getString("telefono"));
				 medico.setCelular(rs.getString("celular"));
				 medico.setCorreo(rs.getString("correo"));
				 medico.setEstado(rs.getInt("estado"));
				 
				 Especialidad esp = new Especialidad();
				 esp.setID(rs.getInt("e.id"));
				 esp.setDescripción(rs.getString("e.descripcion"));
		 
				 medico.setID_especialidad(esp);	
				 list.add(medico);
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
		
}
