package entidad;

public class Estado {
	
private int ID;
private String Descripcion;


public Estado() {
	super();
	
}
public Estado(int iD, String descripción) {
	super();
	ID = iD;
	this.Descripcion = descripción;
}
public int getID() {
	return ID;
}
public void setID(int iD) {
	ID = iD;
}
public String getDescripcion() {
	return Descripcion;
}
public void setDescripcion(String descripción) {
	this.Descripcion = descripción;
}

@Override
public String toString() {
	return "Estado [ID=" + ID + ", Descripcion=" + Descripcion + "]";
}

}
