package datosImpl;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import datos.DisponibilidadxMedicoDao;
import entidad.DisponibilidadxMedico;
import entidad.Medico;
import entidad.Paciente;

public class DisponibilidadxMedicoDaoImpl  implements DisponibilidadxMedicoDao{
	private Conexion cn;

	public DisponibilidadxMedicoDaoImpl()
	{
		
	}
	
	
	@Override
	public List<DisponibilidadxMedico> obtenerTodos() {
		cn = new Conexion();
		cn.Open();
		 List<DisponibilidadxMedico> list = new ArrayList<DisponibilidadxMedico>();
		 try
		 {
			 ResultSet rs= cn.query("Select ID, dni_medico, dia from DisponibilidadxMedico ");
			 while(rs.next())
			 {
				 DisponibilidadxMedico dxmedico = new DisponibilidadxMedico();
				 
				 dxmedico.setID(rs.getInt("ID"));
				 
				 Medico medico = new Medico();
				 medico.setDni(rs.getString("dni_medico"));
				 dxmedico.setDNI_medico(medico);	
				 
				 dxmedico.setDia(Integer.parseInt(rs.getString("dia")));
				 
				 list.add(dxmedico);
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
	public DisponibilidadxMedico obtenerUno(int id) {
		cn = new Conexion();
		cn.Open();
	
		DisponibilidadxMedico dxmedico = new DisponibilidadxMedico();
		try
		 {
			 ResultSet rs= cn.query("Select ID, dni_medico, dia from DisponibilidadxMedico where ID = " + id);
			 rs.next();
			 
			 dxmedico.setID(rs.getInt("ID"));
			 
			 Medico medico = new Medico();
			 medico.setDni(rs.getString("dni_medico"));
			 dxmedico.setDNI_medico(medico);	
			 
			 dxmedico.setDia(Integer.parseInt(rs.getString("dia")));
			
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		return dxmedico;
	}



	@Override
	public boolean insertar(DisponibilidadxMedico dxmedico) {
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	
	
		
		String query = "INSERT INTO DisponibilidadxMedico (DNI_medico, dia) VALUES ('"+dxmedico.getDNI_medico().getDni()+"',"+dxmedico.getDia()+")";
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
	public boolean borrar(String dni) {
		boolean estado=true;
		cn = new Conexion();
		cn.Open();		 
		String query = "DELETE FROM DisponibilidadxMedico WHERE dni_medico ='"+dni+"'";
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
	public List<DisponibilidadxMedico> obtenerDiasxMedico(String dnimedico) {
		cn = new Conexion();
		cn.Open();
		 List<DisponibilidadxMedico> list = new ArrayList<DisponibilidadxMedico>();
		 try
		 {
			 ResultSet rs= cn.query("Select ID, dni_medico, dia from DisponibilidadxMedico WHERE DNI_MEDICO = '"+dnimedico+"'");
			 while(rs.next())
			 {				
				 DisponibilidadxMedico dxmedico = new DisponibilidadxMedico();
				 dxmedico.setID(rs.getInt("ID"));
				 
				 Medico medico = new Medico();
				 medico.setDni(rs.getString("dni_medico"));
				 dxmedico.setDNI_medico(medico);	
				 
				 dxmedico.setDia(Integer.parseInt(rs.getString("dia")));
				 
				 list.add(dxmedico);
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
	
	public boolean verificar(DisponibilidadxMedico consulta) {
			
		cn = new Conexion();
		cn.Open();
	
		DisponibilidadxMedico dxmedico = new DisponibilidadxMedico();
		try
		 {
			 ResultSet rs= cn.query("Select ID, dni_medico, dia from DisponibilidadxMedico where dni_medico = '" + consulta.getDNI_medico().getDni() + "' AND dia = "+consulta.getDia());
			 rs.next();
			 
			 dxmedico.setID(rs.getInt("ID"));
			 
			 Medico medico = new Medico();
			 medico.setDni(rs.getString("dni_medico"));
			 dxmedico.setDNI_medico(medico);	
			 
			 dxmedico.setDia(Integer.parseInt(rs.getString("dia")));
			
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		
		if(consulta.getDia()==dxmedico.getDia()) {
			
			return true; //PUEDE ATENDER ESE DIA			
		}
		else {
			return false; //NO PUEDE ATENDER ESE DIA
		}
		
	
	
	}





	
	}


	
	



