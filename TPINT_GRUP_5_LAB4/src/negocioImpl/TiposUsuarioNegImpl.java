package negocioImpl;

import java.util.ArrayList;


import datos.TiposUsuariosDao;
import datosImpl.TiposUsuarioImpl;
import entidad.Especialidad;
import entidad.TiposUsuario;
import negocio.TiposUsuarioNeg;

public class TiposUsuarioNegImpl implements TiposUsuarioNeg {
	
	private TiposUsuariosDao tiposUsuariosDao = new TiposUsuarioImpl();
	

	public TiposUsuarioNegImpl (TiposUsuariosDao tiposUsuariosDao)
	{
		this.tiposUsuariosDao= tiposUsuariosDao;
	}
	
	public TiposUsuarioNegImpl ()
	{
	}
	
	@Override
	public ArrayList<TiposUsuario> listarTiposUsuarios() {
		return (ArrayList<TiposUsuario>) tiposUsuariosDao.obtenerTodos();
	}

	@Override
	public TiposUsuario obtenerUno(int id) {
		return tiposUsuariosDao.obtenerUno(id);
	}

	@Override
	public boolean insertar(TiposUsuario tiposUsuarios) {
		return tiposUsuariosDao.insertar(tiposUsuarios);
	}
	
	
}
