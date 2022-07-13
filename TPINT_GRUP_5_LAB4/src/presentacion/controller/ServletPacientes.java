
package presentacion.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datosImpl.PacienteDaoImpl;
import entidad.Especialidad;
import entidad.Medico;
import entidad.Paciente;
import negocio.PacienteNeg;
import negocioImpl.PacienteNegImpl;


@WebServlet("/ServletPacientes")
public class ServletPacientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PacienteNeg negPac = new PacienteNegImpl();
  
    
    public ServletPacientes() {
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
				
				break;
			}
			case "list":
			{
				request.setAttribute("listaPac", negPac.listarPacientes());	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarPacientes.jsp?buscarLista=1");
				dispatcher.forward(request, response);
				break;
			}
			case "listaBP":
			{
				request.setAttribute("listaPac", negPac.listarPacientes());	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/BusquedaPacientes.jsp?buscarBPLista=1");
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
		 if(request.getParameter("btnAceptar")!=null)
		    {
		    	Paciente prueba = new Paciente();
		    	prueba = negPac.obtenerUno(request.getParameter("txtDNI"));
		    	
		    	//PARA NO INGRESAR CLAVES REPETIDAS
		    	if(prueba.getDni() == null) {
		    		
		    	Paciente x = new Paciente();
					x.setNombre(request.getParameter("txtNombre"));
					x.setApellido(request.getParameter("txtApellido"));
					x.setDni(request.getParameter("txtDNI"));
					x.setTelefono(request.getParameter("txtTelefono"));
					x.setCelular(request.getParameter("txtCelular"));
					x.setCorreo(request.getParameter("txtCorreo"));
					x.setDireccion(request.getParameter("txtDireccion"));
					x.setNacionalidad(request.getParameter("comboNacionalidad"));
					x.setLocalidad(request.getParameter("comboLocalidad"));
					x.setProvincia(request.getParameter("comboProvincia"));
					
					int sexo = Integer.parseInt(request.getParameter("comboSexo"));
					switch (sexo) {
					case 1:
						x.setSexo("Masculino");	
						break;
					case 2:
						x.setSexo("Femenino");	
						break;
					case 3:
						x.setSexo("Otro");	
						break;
					default:
						break;
					}
					
				
					
					//IMPORTANTISIMO PARA FECHA					
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
					Date fecha;
					try {
						fecha = formato.parse(request.getParameter("txtFechaNac"));
						x.setFechaNac(fecha);						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
					
				
					
					x.setEstado(1);
					boolean estado=true;				
					estado = negPac.insertar(x);
					request.setAttribute("estadoPaciente","Paciente agregado con exito");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/FormularioPaciente.jsp");
					dispatcher.forward(request, response);
		    	}
		    	else {
		    				    				    		
		    		boolean estado=false;
					request.setAttribute("estadoPaciente", "DNI repetido, no se pudo cargar");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/FormularioPaciente.jsp");
					dispatcher.forward(request, response);
		    	}
					
		    }
		 
		 
		//PARA LIMPIAR CAMPOS
		 if(request.getParameter("btnLimpiarCampos")!=null) 
			{	
			 response.sendRedirect("FormularioPaciente.jsp");
			}
		
		//PARA ELIMINAR
		if(request.getParameter("btnEliminar")!=null) 
		{
			String dni = request.getParameter("dniPaciente");
			
			boolean estado = true;
			estado = negPac.borrar(dni);
			request.setAttribute("listaPac", negPac.listarPacientes());	
			response.sendRedirect("ListarPacientes.jsp");
			/*RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarPacientes.jsp");
			dispatcher.forward(request, response);*/
		}
		
		
		//PARA MODIFICAR
		if(request.getParameter("btnEditar")!=null) 
		{
			String dni = request.getParameter("dniPaciente");					
			Paciente p = new Paciente();
			
			p = negPac.obtenerUno(dni);			
			request.setAttribute("dniPac", p);	
			RequestDispatcher dispatcher = request.getRequestDispatcher("/EditarPaciente.jsp");
			dispatcher.forward(request, response);
					
		}
		
		//EDITAR PACIENTE
		if(request.getParameter("btnModificar")!=null)
	    {
	    	Paciente x = new Paciente();
	
		    	x.setDni(request.getParameter("TxtDNI"));
				x.setNombre(request.getParameter("TxtNombre"));
				x.setApellido(request.getParameter("TxtApellido"));
				x.setTelefono(request.getParameter("TxtTelefono"));
				x.setCelular(request.getParameter("TxtCelular"));
				x.setCorreo(request.getParameter("TxtCorreo"));
				x.setDireccion(request.getParameter("TxtDireccion"));
				x.setNacionalidad(request.getParameter("comboNacionalidad"));
				x.setLocalidad(request.getParameter("comboLocalidad"));
				x.setProvincia(request.getParameter("comboProvincia"));
				
				int sexo = Integer.parseInt(request.getParameter("ComboSexo"));
				switch (sexo) {
				case 1:
					x.setSexo("Masculino");	
					break;
				case 2:
					x.setSexo("Femenino");	
					break;
				case 3:
					x.setSexo("Otro");	
					break;
				default:
					break;
				}
				
			
				
				//IMPORTANTISIMO PARA FECHA					
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
				Date fecha;
				try {
					fecha = formato.parse(request.getParameter("TxtFechaNac"));
					x.setFechaNac(fecha);						
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				
			
				
				x.setEstado(1);
				boolean estado=true;
				estado = negPac.editar(x);
				request.setAttribute("estadoPaciente", "Paciente DNI "+ x.getDni() + " - " + x.getApellido()+", " + x.getNombre()+" modificado exitosamente");
				request.setAttribute("listaPac", negPac.listarPacientes());	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarPacientes.jsp?buscarLista=1");			
				dispatcher.forward(request, response);
				
				
	    }
		
		//BUSQUEDA DINAMICA
		 if(request.getParameter("btnBuscar")!=null)
		 {
		    	String parametro = request.getParameter("txtBuscar");	    				
		    	request.setAttribute("listaPac", negPac.obtenerBusqueda(parametro));	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/BusquedaPacientes.jsp?buscarBPLista=1");
				dispatcher.forward(request, response);
					
		 }
		
		
		
		}
		
	}
	
