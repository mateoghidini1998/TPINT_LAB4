package entidad;

public class EspecialidadesxMedico {
 
	private int ID;
	private Medico Dni_medico;
	private Especialidad ID_especialidad;
	
		
	public EspecialidadesxMedico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EspecialidadesxMedico(int iD, Medico dni_medico, Especialidad iD_especialidad) {
		super();
		ID = iD;
		Dni_medico = dni_medico;
		ID_especialidad = iD_especialidad;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public Medico getDni_medico() {
		return Dni_medico;
	}
	
	public void setDni_medico(Medico dni_medico) {
		Dni_medico = dni_medico;
	}
	
	public Especialidad getID_especialidad() {
		return ID_especialidad;
	}
	
	public void setID_especialidad(Especialidad iD_especialidad) {
		ID_especialidad = iD_especialidad;
	}
	
	@Override
	public String toString() {
		return "EspecialidadesxMedico [ID=" + ID + ", Dni_medico=" + Dni_medico.getDni() + ", ID_especialidad=" + ID_especialidad.getDescripcion()
		+ "]";
	}
	
	
}
