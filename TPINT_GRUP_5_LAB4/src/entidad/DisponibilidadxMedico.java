package entidad;


public class DisponibilidadxMedico {

	private int ID;
	private Medico DNI_medico;
	private int dia;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Medico getDNI_medico() {
		return DNI_medico;
	}
	public void setDNI_medico(Medico dNI_medico) {
		DNI_medico = dNI_medico;
	}
	public DisponibilidadxMedico() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public DisponibilidadxMedico(int iD, Medico dNI_medico, int dia) {
		super();
		ID = iD;
		DNI_medico = dNI_medico;
		this.dia = dia;
	}
	

	
	
	
	
	
}
