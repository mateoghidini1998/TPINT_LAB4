package datosImpl;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import datos.TurnoDao;
import entidad.Turno;
import entidad.Estado;
import entidad.Medico;
import entidad.Paciente;
import entidad.Especialidad;
/*import java.sql.Time;
import java.sql.Date;*/

public class TurnoDaoImpl implements TurnoDao{
	
	private Conexion cn;

	public TurnoDaoImpl()
	{
		
	}
	


	@Override
	public Turno obtenerUno(int id) {
		cn = new Conexion();
		cn.Open();
		Turno turno = new Turno();
		try
		 {
			ResultSet rs= cn.query("Select T.id, T.dni_paciente, P.nombre, P.apellido, P.sexo, P.nacionalidad, P.fechaNac, P.direccion, P.localidad, P.provincia, P.telefono, P.celular, P.correo, P.estado, T.dni_medico, M.nombre, M.apellido, T.id_estado, E.descripcion, T.ID_especialidad, ES.descripcion, T.fecha, T.hora, T.observacion from Turnos T, Pacientes P, Medicos M, Estados E, Especialidades ES WHERE T.dni_paciente = P.dni AND T.dni_medico = M.dni AND T.id_estado = E.ID AND ES.ID = T.ID_Especialidad AND T.id = "+id);
			 rs.next();
			 		
			 turno.setId(rs.getInt("T.id"));			 
			 turno.setFecha(rs.getDate("T.fecha"));
			 turno.setHora(rs.getDate("T.hora"));
			 
			 Paciente paciente = new Paciente();
			 paciente.setDni(rs.getString("T.dni_paciente"));
			 paciente.setNombre(rs.getString("P.nombre"));
			 paciente.setApellido(rs.getString("P.apellido"));
			 paciente.setSexo(rs.getString("P.sexo"));
			 paciente.setNacionalidad(rs.getString("P.nacionalidad"));
			 paciente.setFechaNac(rs.getDate("P.fechaNac"));
			 paciente.setDireccion(rs.getString("P.direccion"));
			 paciente.setProvincia(rs.getString("P.provincia"));
			 paciente.setTelefono(rs.getString("P.telefono"));
			 paciente.setCelular(rs.getString("P.celular"));
			 paciente.setCorreo(rs.getString("P.correo"));
			 paciente.setEstado(rs.getInt("P.estado"));
			 
			 
			 Medico medico = new Medico();
			 medico.setDni(rs.getString("T.dni_medico"));
			 medico.setNombre(rs.getString("M.nombre"));
			 medico.setApellido(rs.getString("M.apellido"));
			 
			 Estado estado = new Estado();
			 estado.setID(rs.getInt("T.id_estado"));
			 estado.setDescripcion(rs.getString("E.descripcion"));
			 
			 Especialidad especialidad = new Especialidad();
			 especialidad.setID(rs.getInt("T.ID_especialidad"));
			 especialidad.setDescripción(rs.getString("ES.descripcion"));
			 
			 turno.setObservacion(rs.getString("T.observacion"));
			 
			 
			 turno.setDNI_paciente(paciente);
			 turno.setDNI_medico(medico);
			 turno.setID_estado(estado);
			 turno.setID_especialidad(especialidad);
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		return turno;
	}

	@Override
	public boolean insertar(Turno turno) {
		
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	
		
		//IMPORTANTISIMO PARA INSERTAR FECHA
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");		
		String fechaComoCadena = formato.format(turno.getFecha());
				
		//IMPORTANTISIMO PARA INSERTAR HORA
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");	
		String horaComoCadena = formatoHora.format(turno.getHora());
		System.out.println(horaComoCadena);

		String query = "INSERT INTO TURNOS (dni_paciente, dni_medico, id_estado, id_especialidad, fecha, hora, observacion) VALUES ('"+turno.getDNI_paciente().getDni()+"','"+turno.getDNI_medico().getDni()+"','"+turno.getID_estado().getID()+"', '"+turno.getID_especialidad().getID()+"', '"+fechaComoCadena+"', '"+horaComoCadena+"', '"+turno.getObservacion()+"')";
		System.out.println(query);
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
	public boolean editar(Turno turno) {
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "UPDATE TURNOS SET dni_paciente = '"+turno.getDNI_paciente()+"' , dni_medico = '"+turno.getDNI_medico()+"' , id_estado = '"+turno.getID_estado()+"' , id_especialidad = '"+turno.getID_especialidad()+"', fecha = '2022-07-04', hora = '2022-07-04 11:45:30', observacion = '"+turno.getObservacion()+"' WHERE id='"+turno.getId()+"'";
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
	
	
	public boolean agregarObservacion(Turno turno) {
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "UPDATE TURNOS SET observacion = '"+turno.getObservacion()+"' WHERE id='"+turno.getId()+"'";
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
	
	//METODO ESTADO LIBRE
	public boolean cambiarEstadoLibre(int id){
		boolean estado=true;
		cn = new Conexion();
		cn.Open();		 
		String query = "UPDATE TURNOS SET id_estado = 1 WHERE id="+id;
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
	

	//METODO ESTADO OCUPADO
	public boolean cambiarEstadoOcupado(int id){
		boolean estado=true;
		cn = new Conexion();
		cn.Open();		 
		String query = "UPDATE TURNOS SET id_estado = 2 WHERE id="+id;
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
	

	//METODO ESTADO AUSENTE
	public boolean cambiarEstadoAusente(int id){
		boolean estado=true;
		cn = new Conexion();
		cn.Open();		 
		String query = "UPDATE TURNOS SET id_estado = 3 WHERE id="+id;
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
	

	//METODO ESTADO PRESENTE
	public boolean cambiarEstadoPresente(int id){
		boolean estado=true;
		cn = new Conexion();
		cn.Open();		 
		String query = "UPDATE TURNOS SET id_estado = 4 WHERE id="+id;
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
	public List<Turno> obtenerTodos() {
		cn = new Conexion();
		cn.Open();
		 List<Turno> list = new ArrayList<Turno>();
		 try
		 {
			 ResultSet rs= cn.query("Select T.id, T.dni_paciente, P.nombre, P.apellido, T.dni_medico, M.nombre, M.apellido, T.id_estado, E.descripcion, T.ID_especialidad, ES.descripcion, T.fecha, T.hora, T.observacion from Turnos T, Pacientes P, Medicos M, Estados E, Especialidades ES WHERE T.dni_paciente = P.dni AND T.dni_medico = M.dni AND T.id_estado = E.ID AND ES.ID = T.ID_Especialidad");
			 while(rs.next())
			 {
				 Turno turno = new Turno();
				 turno.setId(rs.getInt("T.id"));			 
				 turno.setFecha(rs.getDate("T.fecha"));
				 turno.setHora(rs.getTime("T.hora"));
				 
				 Paciente paciente = new Paciente();
				 paciente.setDni(rs.getString("T.dni_paciente"));
				 paciente.setNombre(rs.getString("P.nombre"));
				 paciente.setApellido(rs.getString("P.apellido"));
				 
				 Medico medico = new Medico();
				 medico.setDni(rs.getString("T.dni_medico"));
				 medico.setNombre(rs.getString("M.nombre"));
				 medico.setApellido(rs.getString("M.apellido"));
				 
				 Estado estado = new Estado();
				 estado.setID(rs.getInt("T.id_estado"));
				 estado.setDescripcion(rs.getString("E.descripcion"));
				 
				 Especialidad especialidad = new Especialidad();
				 especialidad.setID(rs.getInt("T.ID_especialidad"));
				 especialidad.setDescripción(rs.getString("ES.descripcion"));
				 
				 turno.setObservacion(rs.getString("T.observacion"));
				 
				 
				 turno.setDNI_paciente(paciente);
				 turno.setDNI_medico(medico);
				 turno.setID_estado(estado);
				 turno.setID_especialidad(especialidad);
				 
	 
				 				 			 	
				 list.add(turno);
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
	public List<Turno> obtenerPorDniMedico(String dniMedico) {
		cn = new Conexion();
		cn.Open();
		 List<Turno> list = new ArrayList<Turno>();
		 try
		 {
			 ResultSet rs= cn.query("Select T.id, T.dni_paciente, P.nombre, P.apellido, P.sexo, P.nacionalidad, P.fechaNac, P.direccion, P.localidad, P.provincia, P.telefono, P.celular, P.correo, P.estado, T.dni_medico, M.nombre, M.apellido, T.id_estado, E.descripcion, T.ID_especialidad, ES.descripcion, T.fecha, T.hora, T.observacion from Turnos T inner join Pacientes P on T.dni_paciente = P.dni inner join Medicos M on T.dni_medico = M.dni inner join Estados E on T.id_estado = E.ID inner join Especialidades ES on ES.ID = T.ID_Especialidad where T.dni_medico = '"+ dniMedico+"'");
			 while(rs.next())
			 {
				 Turno turno = new Turno();
				 turno.setId(rs.getInt("T.id"));			 
				 turno.setFecha(rs.getDate("T.fecha"));
				 turno.setHora(rs.getTime("T.hora"));
				 
				 Paciente paciente = new Paciente();
				 paciente.setDni(rs.getString("T.dni_paciente"));
				 paciente.setNombre(rs.getString("P.nombre"));
				 paciente.setApellido(rs.getString("P.apellido"));
				 paciente.setSexo(rs.getString("P.sexo"));
				 paciente.setNacionalidad(rs.getString("P.nacionalidad"));
				 paciente.setFechaNac(rs.getDate("P.fechaNac"));
				 paciente.setDireccion(rs.getString("P.direccion"));
				 paciente.setProvincia(rs.getString("P.provincia"));
				 paciente.setTelefono(rs.getString("P.telefono"));
				 paciente.setCelular(rs.getString("P.celular"));
				 paciente.setCorreo(rs.getString("P.correo"));
				 paciente.setEstado(rs.getInt("P.estado"));
				 
				 Medico medico = new Medico();
				 medico.setDni(rs.getString("T.dni_medico"));
				 medico.setNombre(rs.getString("M.nombre"));
				 medico.setApellido(rs.getString("M.apellido"));
				 
				 Estado estado = new Estado();
				 estado.setID(rs.getInt("T.id_estado"));
				 estado.setDescripcion(rs.getString("E.descripcion"));
				 
				 Especialidad especialidad = new Especialidad();
				 especialidad.setID(rs.getInt("T.ID_especialidad"));
				 especialidad.setDescripción(rs.getString("ES.descripcion"));
				 
				 turno.setObservacion(rs.getString("T.observacion"));
				 
				 
				 turno.setDNI_paciente(paciente);
				 turno.setDNI_medico(medico);
				 turno.setID_estado(estado);
				 turno.setID_especialidad(especialidad);
				 
	 
				 				 			 	
				 list.add(turno);
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
	
	public List<Turno> obtenerBusqueda(String parametro) {
		cn = new Conexion();
		cn.Open();
		 List<Turno> list = new ArrayList<Turno>();
		 try
		 {
			 ResultSet rs= cn.query("Select T.id, T.dni_paciente, P.nombre, P.apellido, T.dni_medico, M.nombre, M.apellido, T.id_estado, E.descripcion, T.ID_especialidad, ES.descripcion, T.fecha, T.hora, T.observacion from Turnos T, Pacientes P, Medicos M, Estados E, Especialidades ES WHERE T.dni_paciente = P.dni AND T.dni_medico = M.dni AND T.id_estado = E.ID AND ES.ID = T.ID_Especialidad and T.dni_paciente LIKE '"+parametro+"'");
			 while(rs.next())
			 {
				 Turno turno = new Turno();
				 turno.setId(rs.getInt("T.id"));			 
				 turno.setFecha(rs.getDate("T.fecha"));
				 turno.setHora(rs.getTime("T.hora"));
				 
				 Paciente paciente = new Paciente();
				 paciente.setDni(rs.getString("T.dni_paciente"));
				 paciente.setNombre(rs.getString("P.nombre"));
				 paciente.setApellido(rs.getString("P.apellido"));
				 
				 Medico medico = new Medico();
				 medico.setDni(rs.getString("T.dni_medico"));
				 medico.setNombre(rs.getString("M.nombre"));
				 medico.setApellido(rs.getString("M.apellido"));
				 
				 Estado estado = new Estado();
				 estado.setID(rs.getInt("T.id_estado"));
				 estado.setDescripcion(rs.getString("E.descripcion"));
				 
				 Especialidad especialidad = new Especialidad();
				 especialidad.setID(rs.getInt("T.ID_especialidad"));
				 especialidad.setDescripción(rs.getString("ES.descripcion"));
				 
				 turno.setObservacion(rs.getString("T.observacion"));
				 
				 
				 turno.setDNI_paciente(paciente);
				 turno.setDNI_medico(medico);
				 turno.setID_estado(estado);
				 turno.setID_especialidad(especialidad);
				 
	 
				 				 			 	
				 list.add(turno);
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
	
	public List<Turno> obtenerBusquedaxEstado(String parametro) {
		cn = new Conexion();
		cn.Open();
		 List<Turno> list = new ArrayList<Turno>();
		 try
		 {
			 ResultSet rs= cn.query("Select T.id, T.dni_paciente, P.nombre, P.apellido, T.dni_medico, M.nombre, M.apellido, T.id_estado, E.descripcion, T.ID_especialidad, ES.descripcion, T.fecha, T.hora, T.observacion from Turnos T, Pacientes P, Medicos M, Estados E, Especialidades ES WHERE T.dni_paciente = P.dni AND T.dni_medico = M.dni AND T.id_estado = E.ID AND ES.ID = T.ID_Especialidad and E.descripcion LIKE '"+parametro+"'");
			 while(rs.next())
			 {
				 Turno turno = new Turno();
				 turno.setId(rs.getInt("T.id"));			 
				 turno.setFecha(rs.getDate("T.fecha"));
				 turno.setHora(rs.getTime("T.hora"));
				 
				 Paciente paciente = new Paciente();
				 paciente.setDni(rs.getString("T.dni_paciente"));
				 paciente.setNombre(rs.getString("P.nombre"));
				 paciente.setApellido(rs.getString("P.apellido"));
				 
				 Medico medico = new Medico();
				 medico.setDni(rs.getString("T.dni_medico"));
				 medico.setNombre(rs.getString("M.nombre"));
				 medico.setApellido(rs.getString("M.apellido"));
				 
				 Estado estado = new Estado();
				 estado.setID(rs.getInt("T.id_estado"));
				 estado.setDescripcion(rs.getString("E.descripcion"));
				 
				 Especialidad especialidad = new Especialidad();
				 especialidad.setID(rs.getInt("T.ID_especialidad"));
				 especialidad.setDescripción(rs.getString("ES.descripcion"));
				 
				 turno.setObservacion(rs.getString("T.observacion"));
				 
				 
				 turno.setDNI_paciente(paciente);
				 turno.setDNI_medico(medico);
				 turno.setID_estado(estado);
				 turno.setID_especialidad(especialidad);
				 
	 
				 				 			 	
				 list.add(turno);
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



	
	
	public boolean consultaTurnoxFechayHoraDNIMed(Turno comparacion) {

	
		cn = new Conexion();
		cn.Open();	
		
		Turno turno = new Turno();
		
		//IMPORTANTISIMO PARA COMPARAR FECHA
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");		
		String fechaComoCadena = formato.format(comparacion.getFecha());
				
		//IMPORTANTISIMO PARA COMPARAR HORA
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");	
		String horaComoCadena = formatoHora.format(comparacion.getHora());
		System.out.println(horaComoCadena);

		try
		 {
			ResultSet rs= cn.query("Select T.id, T.dni_paciente, P.nombre, P.apellido, T.dni_medico, M.nombre, M.apellido, T.id_estado, E.descripcion, T.ID_especialidad, ES.descripcion, T.fecha, T.hora, T.observacion from Turnos T, Pacientes P, Medicos M, Estados E, Especialidades ES WHERE T.dni_paciente = P.dni AND T.dni_medico = M.dni AND T.id_estado = E.ID AND ES.ID = T.ID_Especialidad AND T.fecha = '"+fechaComoCadena+"' AND T.hora = '"+horaComoCadena+"' AND T.dni_medico = '"+comparacion.getDNI_medico().getDni()+"'");
			 rs.next();
			 		
			 turno.setId(rs.getInt("T.id"));			 
			 turno.setFecha(rs.getDate("T.fecha"));
			 turno.setHora(rs.getDate("T.hora"));
			 
			 Paciente paciente = new Paciente();
			 paciente.setDni(rs.getString("T.dni_paciente"));
			 paciente.setNombre(rs.getString("P.nombre"));
			 paciente.setApellido(rs.getString("P.apellido"));
			 
			 Medico medico = new Medico();
			 medico.setDni(rs.getString("T.dni_medico"));
			 medico.setNombre(rs.getString("M.nombre"));
			 medico.setApellido(rs.getString("M.apellido"));
			 
			 
			 
			 turno.setDNI_medico(medico);	
			 
				
			 //EL MEDICO YA TIENE UN TURNO EN ESA FECHA Y HORARIO		
			return true;
			
			 
		 }
		 catch(Exception e)
		 {
			 //EL MEDICO NO TIENE UN TURNO EN ESA FECHA Y HORARIO		
			return false;
		 }
		 finally
		 {
			 cn.close();
		 }
	
		
	}



	
	



	
	
	
	
	
}