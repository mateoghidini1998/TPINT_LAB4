package entidad;

public class Estado {
	
private int ID;
private String Descripcion;


public Estado() {
	super();
	
}
public Estado(int iD, String descripci�n) {
	super();
	ID = iD;
	this.Descripcion = descripci�n;
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
public void setDescripcion(String descripci�n) {
	this.Descripcion = descripci�n;
}

@Override
public String toString() {
	return "Estado [ID=" + ID + ", Descripcion=" + Descripcion + "]";
}

}
