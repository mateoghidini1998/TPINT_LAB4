package presentacion.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Especialidad;
import entidad.Medico;
import entidad.Paciente;
import negocio.EspecialidadNeg;

import negocio.MedicoNeg;
import negocioImpl.EspecialidadNegImpl;
import negocioImpl.MedicoNegImpl;

import entidad.DisponibilidadxMedico;
import negocio.DisponibilidadxMedicoNeg;
import negocioImpl.DisponibilidadxMedicoNegImpl;


@WebServlet("/ServletMedicos")
public class ServletMedicos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EspecialidadNeg negEsp = new EspecialidadNegImpl(); 
	MedicoNeg negMed = new MedicoNegImpl();
	DisponibilidadxMedicoNeg negDxMed = new DisponibilidadxMedicoNegImpl();
	
    public ServletMedicos() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Param")!=null)
		{
			String opcion = request.getParameter("Param").toString();
			
			switch (opcion) {
			case "previoInsert":
			{

				//Se quiere insertar entonces cargo la lista de categorias
				request.setAttribute("listaEsp", negEsp.listarEspecialidades());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/FormularioMedico.jsp");
				dispatcher.forward(request, response);

				break;
			}
			case "list":
			{
				request.setAttribute("listaMed", negMed.listarMedicos());	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarMedicos.jsp?buscarLista=1");
				dispatcher.forward(request, response);
				break;
			}
			case "listaBM":
			{
				request.setAttribute("listaMed", negMed.listarMedicos());	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/BusquedaMedicos.jsp?buscarBMLista=1");
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
			 Medico prueba = new Medico();
		    	prueba = negMed.obtenerUno(request.getParameter("txtDNI"));
		    	
		    	//PARA NO INGRESAR CLAVES REPETIDAS
		    	if(prueba.getDni() == null) {
		    		
		    	Medico x = new Medico();
		    		    	
			
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
					
					Especialidad especialidad = new Especialidad();
					especialidad.setID(Integer.parseInt(request.getParameter("comboEspecialidad")));
					x.setID_especialidad(especialidad);
				
					
				
					
					
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
					
					estado = negMed.insertar(x);
					
					//LOGICA PARA INSERTAR DISPONIBILIDADES POR MEDICO
					if(estado) {
						String dias[] = request.getParameterValues("dia");
						if(dias != null) {
							for(int i=0; i<dias.length; i++) {
								
								DisponibilidadxMedico dxmedico = new DisponibilidadxMedico();
								Medico medDia = new Medico();
								medDia.setDni(request.getParameter("txtDNI"));
								dxmedico.setDNI_medico(medDia);
								
								//SE OBTIENEN LOS NUMEROS DEL 0 AL 6, SIENDO 0 DOMINGO Y 6 SABADO
								switch (dias[i]) {
								case "0": dxmedico.setDia(0);											  										  
									break;
								case "1": dxmedico.setDia(1);							
									break;
								case "2": dxmedico.setDia(2);	
									break;
								case "3": dxmedico.setDia(3);	
									break;
								case "4": dxmedico.setDia(4);	
									break;
								case "5": dxmedico.setDia(5);	
									break;
								case "6": dxmedico.setDia(6);	
									break;
								default: dxmedico.setDia(-1); //SIN DISPONIBILIDAD NINGUN DIA
									break;
								}
								
								negDxMed.insertar(dxmedico);
								System.out.println("dia agregado");
								
							}
						}
					}
					
					
					request.setAttribute("estadoMedico","Medico agregado con exito");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/FormularioMedico.jsp");
					dispatcher.forward(request, response);
		    	}
		    	else {
		    				    				    		
		    		boolean estado=false;
					request.setAttribute("estadoMedico", "DNI repetido, no se pudo cargar");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/FormularioMedico.jsp");
					dispatcher.forward(request, response);
		    	}
					
		    }
		 
		 
		 //PARA LIMPIAR CAMPOS
		 if(request.getParameter("btnLimpiarCampos")!=null) 
			{	
			    request.setAttribute("listaEsp", negEsp.listarEspecialidades());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/FormularioMedico.jsp");
				dispatcher.forward(request, response);
			}
				
		 //PARA ELIMINAR
		 if(request.getParameter("btnEliminar")!=null) 
			{
				String dni = request.getParameter("dniMedico");
				negMed.borrar(dni);
				
				ArrayList<Medico> lista = negMed.listarMedicos();
				request.setAttribute("listaMed", lista);
				
				response.sendRedirect("ListarMedicos.jsp");
				/*RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarMedicos.jsp");
				dispatcher.forward(request, response);*/
			}
		 
		//PARA MODIFICAR
			if(request.getParameter("btnEditar")!=null) 
			{
				String dni = request.getParameter("dniMedico");					
				Medico p = new Medico();
				
				p = negMed.obtenerUno(dni);			
				request.setAttribute("dniMed", p);	
				request.setAttribute("listaEsp", negEsp.listarEspecialidades());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/EditarMedico.jsp");
				dispatcher.forward(request, response);
						
			}
			
			if(request.getParameter("btnModificar")!=null)
		    {
				Medico x = new Medico();
		    	
				
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
				
				Especialidad especialidad = new Especialidad();
				especialidad.setID(Integer.parseInt(request.getParameter("comboEspecialidad")));
				x.setID_especialidad(especialidad);
			
				
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
				estado = negMed.editar(x); //EDITO AL MEDICO
				
				//LOGICA PARA INSERTAR DISPONIBILIDADES POR MEDICO
				if(estado) {
					//ELIMINA TODAS LAS DISPONIBILIDADES DEL DIA
					negDxMed.borrar(request.getParameter("txtDNI"));
					
					//INSERTA TODAS LAS DISPONIBILIDADES NUEVAS
					String dias[] = request.getParameterValues("dia");
					if(dias != null) {
						for(int i=0; i<dias.length; i++) {
							
							DisponibilidadxMedico dxmedico = new DisponibilidadxMedico();
							Medico medDia = new Medico();
							medDia.setDni(request.getParameter("txtDNI"));
							dxmedico.setDNI_medico(medDia);
							
							//SE OBTIENEN LOS NUMEROS DEL 0 AL 6, SIENDO 0 DOMINGO Y 6 SABADO
							switch (dias[i]) {
							case "0": dxmedico.setDia(0);											  										  
								break;
							case "1": dxmedico.setDia(1);							
								break;
							case "2": dxmedico.setDia(2);	
								break;
							case "3": dxmedico.setDia(3);	
								break;
							case "4": dxmedico.setDia(4);	
								break;
							case "5": dxmedico.setDia(5);	
								break;
							case "6": dxmedico.setDia(6);	
								break;
							default: dxmedico.setDia(-1); //SIN DISPONIBILIDAD NINGUN DIA
								break;
							}
							
							negDxMed.insertar(dxmedico);
							System.out.println("dia agregado");
							
						}
					}
				}
				
				request.setAttribute("estadoMedico", "Médico DNI "+ x.getDni() + " - " + x.getApellido()+", " + x.getNombre()+" modificado exitosamente");
				request.setAttribute("listaMed", negMed.listarMedicos());	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarMedicos.jsp?buscarLista=1");			
				dispatcher.forward(request, response);
					
		    }
	

	//BUSQUEDA DINAMICA
	 if(request.getParameter("btnBuscar")!=null)
	 {
	    	String parametro = request.getParameter("txtBuscar");	    				
	    	request.setAttribute("listaMed", negMed.obtenerBusqueda(parametro));	
			RequestDispatcher dispatcher = request.getRequestDispatcher("/BusquedaMedicos.jsp?buscarBMLista=1");
			dispatcher.forward(request, response);
				
	 }

	}
}
