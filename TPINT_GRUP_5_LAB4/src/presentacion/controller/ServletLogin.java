package presentacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Usuario;
import negocioImpl.UsuarioNegImpl;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("logout") != null) {
			request.getSession().removeAttribute("usuario");
		} 
		request.getRequestDispatcher("Login.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    String username = request.getParameter("username");  
	    String userpass = request.getParameter("userpass");
	    
	    UsuarioNegImpl u = new UsuarioNegImpl();
	    Usuario user = u.buscarUsuario(username, userpass);
	    if (user.existe()) {
	    	RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
	        HttpSession session = request.getSession();
	        session.setAttribute("usuario", user);
	        rd.forward(request,response);  
	    }
	    else { 
	    	request.setAttribute("errorLogin", 1);
	    	request.getRequestDispatcher("Login.jsp").forward(request, response); 
	    }
	}

}
