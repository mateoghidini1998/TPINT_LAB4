package excepciones;

public class UsuarioNoLoggeadoException extends RuntimeException {

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "ACCESO RESTRINGIDO: el usuario no se encuentra loggeado";
	}

}
