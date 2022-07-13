package datosImpl;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import datos.PacienteDao;
import entidad.Medico;
import entidad.Paciente;

public class PacienteDaoImpl implements PacienteDao{
	
	private Conexion cn;

	public PacienteDaoImpl()
	{
		
	}
	
	
	@Override
	public List<Paciente> obtenerTodos() {
		cn = new Conexion();
		cn.Open();
		 List<Paciente> list = new ArrayList<Paciente>();
		 try
		 {
			 ResultSet rs= cn.query("Select dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado from pacientes WHERE estado = 1");
			 while(rs.next())
			 {
				 Paciente paciente = new Paciente();
				 paciente.setDni(rs.getString("dni"));
				 paciente.setNombre(rs.getString("nombre"));
				 paciente.setApellido(rs.getString("apellido"));
				 paciente.setSexo(rs.getString("sexo"));
				 paciente.setNacionalidad(rs.getString("nacionalidad"));
				 paciente.setFechaNac(rs.getDate("fechaNac"));
				 paciente.setDireccion(rs.getString("direccion"));
				 paciente.setLocalidad(rs.getString("localidad"));
				 paciente.setProvincia(rs.getString("provincia"));
				 paciente.setTelefono(rs.getString("telefono"));
				 paciente.setCelular(rs.getString("celular"));
				 paciente.setCorreo(rs.getString("correo"));
				 paciente.setEstado(rs.getInt("estado"));
				 
				 	
				 list.add(paciente);
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
	public Paciente obtenerUno(String dni) {
		cn = new Conexion();
		cn.Open();
		
		
		
		Paciente paciente = new Paciente();
		try
		 {
			 ResultSet rs= cn.query("Select dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado from pacientes where dni = " + dni);
			 rs.next();
			 
			 paciente.setDni(rs.getString("dni"));
			 paciente.setNombre(rs.getString("nombre"));
			 paciente.setApellido(rs.getString("apellido"));
			 paciente.setSexo(rs.getString("sexo"));
			 paciente.setNacionalidad(rs.getString("nacionalidad"));
			 paciente.setFechaNac(rs.getDate("fechaNac"));
			 paciente.setDireccion(rs.getString("direccion"));
			 paciente.setLocalidad(rs.getString("localidad"));
			 paciente.setProvincia(rs.getString("provincia"));
			 paciente.setTelefono(rs.getString("telefono"));
			 paciente.setCelular(rs.getString("celular"));
			 paciente.setCorreo(rs.getString("correo"));
			 paciente.setEstado(rs.getInt("estado"));
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		return paciente;
	}

	@Override
	public boolean insertar(Paciente paciente) {
				
			boolean estado=true;

			cn = new Conexion();
			cn.Open();	

			//IMPORTANTISIMO PARA INSERTAR FECHA
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");		
			String fechaComoCadena = formato.format(paciente.getFechaNac());
			
			String query = "INSERT INTO Pacientes (dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, correo, estado) VALUES ('"+paciente.getDni()+"','"+paciente.getNombre()+"','"+paciente.getApellido()+"', '"+paciente.getSexo()+"', '"+paciente.getNacionalidad()+"', '"+fechaComoCadena+"', '"+paciente.getDireccion()+"', '"+paciente.getLocalidad()+"', '"+paciente.getProvincia()+"', '"+paciente.getTelefono()+"',  '"+paciente.getCorreo()+"', '"+paciente.isEstado()+"')";
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
	public boolean editar(Paciente paciente) {
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	
		
		//IMPORTANTISIMO PARA INSERTAR FECHA
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");		
		String fechaComoCadena = formato.format(paciente.getFechaNac());

		String query = "UPDATE pacientes SET dni='"+paciente.getDni()+"', nombre='"+paciente.getNombre()+"', apellido='"+paciente.getApellido()+"', sexo = '"+paciente.getSexo()+"', nacionalidad = '"+paciente.getNacionalidad()+"', fechaNac='"+fechaComoCadena+"', direccion='"+paciente.getDireccion()+"', localidad = '"+paciente.getLocalidad()+"', provincia = '"+paciente.getProvincia()+"', telefono='"+paciente.getTelefono()+"', celular = '"+paciente.getCelular()+"', correo='"+paciente.getCorreo()+"', estado = '"+paciente.isEstado()+"' WHERE dni='"+paciente.getDni()+"'";
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
		String query = "UPDATE Pacientes SET estado=0 WHERE dni= '"+dni+"'";
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
	
	//BUSQUEDA DINAMICA POR APELLIDO, NOMBRE O APELLIDO
	public List<Paciente> obtenerBusqueda(String parametro){
		cn = new Conexion();
		cn.Open();
		 List<Paciente> list = new ArrayList<Paciente>();
		 try
		 {
			 ResultSet rs= cn.query("Select dni, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, telefono, celular, correo, estado from pacientes WHERE estado = 1 AND dni LIKE '%"+parametro+"%' OR nombre LIKE '%"+parametro+"%' OR apellido LIKE '%"+parametro+"%'");
			 while(rs.next())
			 {
				 Paciente paciente = new Paciente();
				 paciente.setDni(rs.getString("dni"));
				 paciente.setNombre(rs.getString("nombre"));
				 paciente.setApellido(rs.getString("apellido"));
				 paciente.setSexo(rs.getString("sexo"));
				 paciente.setNacionalidad(rs.getString("nacionalidad"));
				 paciente.setFechaNac(rs.getDate("fechaNac"));
				 paciente.setDireccion(rs.getString("direccion"));
				 paciente.setLocalidad(rs.getString("localidad"));
				 paciente.setProvincia(rs.getString("provincia"));
				 paciente.setTelefono(rs.getString("telefono"));
				 paciente.setCelular(rs.getString("celular"));
				 paciente.setCorreo(rs.getString("correo"));
				 paciente.setEstado(rs.getInt("estado"));
				 
				 	
				 list.add(paciente);
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

