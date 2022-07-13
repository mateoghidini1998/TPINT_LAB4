package datosImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	final String host = "jdbc:mysql://localhost:3306/";
	final String user = "root";
	final String pass = "";
	final String dbName = "bdTurnos";
	final String driver = "com.mysql.jdbc.Driver";
	final String sslParam = "?useSSL=false";

	protected Connection connection;
	
	public Connection Open()
	{
		try
		{
			Class.forName(driver);
			this.connection = DriverManager.getConnection(host+ dbName+ sslParam, user, pass);
			System.out.println("CONEXION: "+ this.connection);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return this.connection;
	}
	
	public ResultSet query(String query)
	{
		Statement st;
		ResultSet rs=null;
		try
		{
			st= connection.createStatement();
			rs= st.executeQuery(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean execute(String query)
	{
		Statement st;
		boolean save = true;
		try {
			st = connection.createStatement();
		    st.executeUpdate(query);
		}
		catch(SQLException e)
		{
			save = false;
			e.printStackTrace();
		}
		return save;
	}
	
	public boolean close()
	{
		boolean ok=true;
		try {
			connection.close();
		}
		catch(Exception e)
		{
			ok= false;
			e.printStackTrace();
		}
		return ok;
	}
	
}
