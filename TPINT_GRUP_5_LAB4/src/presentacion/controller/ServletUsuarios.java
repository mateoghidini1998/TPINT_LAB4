package presentacion.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Medico;
import entidad.TiposUsuario;
import entidad.Usuario;
import negocio.MedicoNeg;
import negocio.TiposUsuarioNeg;
import negocio.UsuarioNeg;
import negocioImpl.MedicoNegImpl;
import negocioImpl.TiposUsuarioNegImpl;
import negocioImpl.UsuarioNegImpl;

/**
 * Servlet implementation class ServletUsuarios
 */
@WebServlet("/ServletUsuarios")
public class ServletUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//EspecialidadNeg negEsp = new EspecialidadNegImpl(); 
	TiposUsuarioNeg negTu = new TiposUsuarioNegImpl();
	UsuarioNeg negU = new UsuarioNegImpl();
	MedicoNeg negMed = new MedicoNegImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Param")!=null)
		{
			String opcion = request.getParameter("Param").toString();
			
			switch (opcion) {
			case "previoInsert":
			{

				//Se quiere insertar entonces cargo la lista de categorias
				request.setAttribute("listaTipoU", negTu.listarTiposUsuarios());
				request.setAttribute("listaMed", negMed.listarMedicos());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/FormularioUsuarios.jsp");
				dispatcher.forward(request, response);

				break;
			}
			
			default:
				break;
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//CREACION DE USUARIO
		if(request.getParameter("btnAceptar")!=null)
	    {
	    	Usuario u = new Usuario();

	    	Medico consulta = new Medico();
	    		    	
	    		consulta = negMed.obtenerUno(request.getParameter("comboMedicos"));
	    		
	    		//System.out.println(consulta.getDni());
	    	
		    	u = negU.obtenerUno(request.getParameter("comboMedicos"));
		    	
	    		if(consulta.getDni()!=null) {
	    			
	    			if(u.getDni()==null) {
	    			//u.setDni(request.getParameter("txtDni"));
	    			
	    			u.setNombre_usuario(request.getParameter("txtNombre"));
	    			
	    				
	    			u.setContrasena(request.getParameter("txtPassword"));				
	    			
	    			u.setDni(request.getParameter("comboMedicos"));
	    		
	    			//u.setDni(request.getParameter("txtDni"));				
	    			//u.setCorreo(request.getParameter("txtCorreo"));				
	    			
	    			TiposUsuario tu = new TiposUsuario();
	    			tu.setID(Integer.parseInt(request.getParameter("comboTipoUsuario")));
	    			u.setTipo_usuario(tu);			
	    			
	    			
	    			
	    			u.setEstado(1);
	    			boolean estado=true;
	    			estado = negU.insertar(u);
	    			request.setAttribute("estadoUsuario", "El Usuario se agrego correctamente");
	    			RequestDispatcher dispatcher = request.getRequestDispatcher("/FormularioUsuarios.jsp");
	    			dispatcher.forward(request, response);
	    			}
	    			
	    			else {
	    				boolean estado=false;
						request.setAttribute("estadoUsuario", "Usuario repetido, no se pudo cargar");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/FormularioUsuarios.jsp");
						dispatcher.forward(request, response);
	    			}
	    			
	    		
	    		} else {

		    		boolean estado=false;
					request.setAttribute("estadoUsuario", "DNI NO EXISTE");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/FormularioUsuarios.jsp");
					dispatcher.forward(request, response);
		    	} 
	    		
				
	    }
	}

}
