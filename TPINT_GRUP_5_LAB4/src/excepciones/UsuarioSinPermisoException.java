package excepciones;

public class UsuarioSinPermisoException extends RuntimeException {

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "ACCESO RESTRINGIDO: el usuario loggeado no tiene permisos para ver este contenido";
	}

}
