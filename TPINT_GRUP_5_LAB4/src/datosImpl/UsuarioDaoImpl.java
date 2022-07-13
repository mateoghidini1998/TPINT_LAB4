package datosImpl;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import datos.UsuarioDao;
import entidad.TiposUsuario;
import entidad.Usuario;

public class UsuarioDaoImpl implements UsuarioDao{
	
	private Conexion cn;

	public UsuarioDaoImpl()
	{
		
	}
	
	
	@Override
	public List<Usuario> obtenerTodos() {
		cn = new Conexion();
		cn.Open();
		 List<Usuario> list = new ArrayList<Usuario>();
		 try
		 {
			 ResultSet rs= cn.query("Select u.id, u.nombre_usuario, u.contrasena, tp.id, tp.descripcion, u.estado, u.dni from usuarios as u inner join tiposusuario as tp where tp.id = u.tipo_usuario");
			 while(rs.next())
			 {
				 Usuario usuario = new Usuario();
				 usuario.setID(rs.getInt("u.id"));
				 usuario.setNombre_usuario(rs.getString("u.nombre_usuario"));
				 usuario.setContrasena(rs.getString("u.contrasena"));
				 
				 TiposUsuario tipo = new TiposUsuario();
				 tipo.setID(rs.getInt("tp.id"));
				 tipo.setDescripcion(rs.getString("tp.descripcion"));
				 
				 usuario.setTipo_usuario(tipo);
				 usuario.setEstado(rs.getInt("u.estado"));
				 usuario.setDni(rs.getString("u.dni"));
			 	
				 list.add(usuario);
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
	public Usuario obtenerUno(String dni) {
		cn = new Conexion();
		cn.Open();
		Usuario usuario = new Usuario();
		try
		 {
			ResultSet rs= cn.query("Select u.id, u.nombre_usuario, u.contrasena, tp.id, tp.descripcion, u.estado, u.dni from usuarios as u inner join tiposusuario as tp where tp.id = u.tipo_usuario and u.dni = "+dni);
			 rs.next();
			 
				
				 usuario.setID(rs.getInt("u.id"));
				 usuario.setNombre_usuario(rs.getString("u.nombre_usuario"));
				 usuario.setContrasena(rs.getString("u.contrasena"));
				 
				 TiposUsuario tipo = new TiposUsuario();
				 tipo.setID(rs.getInt("tp.id"));
				 tipo.setDescripcion(rs.getString("tp.descripcion"));
				 
				 usuario.setTipo_usuario(tipo);
				 usuario.setEstado(rs.getInt("u.estado"));
				 usuario.setDni(rs.getString("u.dni"));
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		return usuario;
	}

	@Override
	public boolean insertar(Usuario usuario) {
		
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "INSERT INTO usuarios (nombre_usuario, contrasena, tipo_usuario, estado, dni) VALUES ('"+usuario.getNombre_usuario()+"','"+usuario.getContrasena()+"','"+usuario.getTipo_usuario().getID()+"','"+usuario.isEstado()+"','"+usuario.getDni()+"')";
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
	public boolean editar(Usuario usuario) {
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "UPDATE usuarios SET nombre_usuario='"+usuario.getNombre_usuario()+"', contrasena='"+usuario.getContrasena()+"', tipo_usuario='"+usuario.getTipo_usuario().getID()+"', estado='"+usuario.isEstado()+"', dni='"+usuario.getDni()+"' WHERE id='"+usuario.getID()+"'";
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
	public boolean borrar(int id) {
		boolean estado=true;
		cn = new Conexion();
		cn.Open();		 
		String query = "UPDATE usuarios SET estado=0 WHERE id="+id;
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
	
	
	
	//METODO DE FACU
	public Usuario buscarUsuario(String nombreUsuario, String contrasena) {
		cn = new Conexion();
		cn.Open();
		Usuario usuario = new Usuario();
		try
		 {
			ResultSet rs= cn.query("Select u.id, u.nombre_usuario, u.contrasena, tp.id, tp.descripcion, u.estado, u.dni from usuarios as u inner join tiposusuario as tp on tp.id = u.tipo_usuario where u.nombre_usuario = '"+nombreUsuario+ "' and u.contrasena = '"+contrasena+"'");
			if (rs.next()) {
			 
				
				 usuario.setID(rs.getInt("u.id"));
				 usuario.setNombre_usuario(rs.getString("u.nombre_usuario"));
				 usuario.setContrasena(rs.getString("u.contrasena"));
				 
				 TiposUsuario tipo = new TiposUsuario();
				 tipo.setID(rs.getInt("tp.id"));
				 tipo.setDescripcion(rs.getString("tp.descripcion"));
				 
				 usuario.setTipo_usuario(tipo);
				 usuario.setEstado(rs.getInt("u.estado"));
				 usuario.setDni(rs.getString("u.dni"));
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
		return usuario;
	}
}