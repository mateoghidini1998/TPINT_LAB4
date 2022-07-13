package entidad;

public class TiposUsuario {
	private int ID;
	private String descripcion;


	public TiposUsuario() {
		super();
		
	}
	public TiposUsuario(int iD, String descripcion) {
		super();
		ID = iD;
		this.descripcion = descripcion;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Especialidad [ID=" + ID + ", descripción=" + descripcion + "]";
	}
}
