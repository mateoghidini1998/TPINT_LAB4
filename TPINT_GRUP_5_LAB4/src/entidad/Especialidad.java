package entidad;

public class Especialidad {

	private int ID;
	private String descripcion;


	public Especialidad() {
		super();
		
	}
	public Especialidad(int iD, String descripcion) {
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
	public void setDescripción(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Especialidad [ID=" + ID + ", descripción=" + descripcion + "]";
	}
}
