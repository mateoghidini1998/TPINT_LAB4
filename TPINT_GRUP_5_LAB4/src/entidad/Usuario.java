package entidad;


public class Usuario {
private int ID;
private String nombre_usuario;
private String contrasena;
private TiposUsuario tipo_usuario;
private Medico DNI_medico;
private int estado;
private String dni; 
	
	public String getDni() {
	return dni;
}
public void setDni(String dni) {
	this.dni = dni;
}
	public int isEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Usuario(int iD, String nombre_usuario, String contrasena, TiposUsuario tipo_usuario,Medico DNI_medico, int estado, String dni) {
		super();
		ID = iD;
		this.nombre_usuario = nombre_usuario;
		this.contrasena = contrasena;
		this.DNI_medico = DNI_medico;
		this.tipo_usuario = tipo_usuario;
		this.estado = estado;
		this.dni = dni;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public TiposUsuario getTipo_usuario() {
		return tipo_usuario;
	}
	public void setTipo_usuario(TiposUsuario tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}
	
	public Medico getDNI_medico() {
		return DNI_medico;
	}
	public void setDNI_medico(Medico DNI_medico) {
		this.DNI_medico = DNI_medico;
	}
	
	@Override
	public String toString() {
		return "Usuario [ID=" + ID + ", nombre_usuario=" + nombre_usuario + ", contrasena=" + contrasena + ", DNI_medico=" + DNI_medico + ", tipo_usuario="
				+ tipo_usuario.getDescripcion() + "]";
	}
	
	public boolean existe()  {
		return ID != 0; 
	}

}
