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

import entidad.DisponibilidadxMedico;
import entidad.Especialidad;
import entidad.Medico;
import entidad.Paciente;
import entidad.Turno;
import entidad.Estado;

import negocio.TurnoNeg;
import negocioImpl.TurnoNegImpl;
import negocio.PacienteNeg;
import negocioImpl.PacienteNegImpl;
import negocio.DisponibilidadxMedicoNeg;
import negocio.EspecialidadNeg;
import negocio.MedicoNeg;
import negocioImpl.DisponibilidadxMedicoNegImpl;
import negocioImpl.EspecialidadNegImpl;
import negocioImpl.MedicoNegImpl;

/**
 * Servlet implementation class ServletTurnos
 */
@WebServlet("/ServletTurnos")
public class ServletTurnos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	private static final TurnoNeg negTur = new TurnoNegImpl();
	private static final EspecialidadNeg negEsp = new EspecialidadNegImpl(); 
    private static final MedicoNeg negMed = new MedicoNegImpl();
    private static final PacienteNeg negPac = new PacienteNegImpl();
    private static final DisponibilidadxMedicoNeg negDxMed = new DisponibilidadxMedicoNegImpl();
  

    public ServletTurnos() {
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
				request.setAttribute("listaEsp", negEsp.listarEspecialidades());
				request.setAttribute("listaPac", negPac.listarPacientes());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Turnos.jsp");
				dispatcher.forward(request, response);
				break;
			}
			case "list":
			{
				request.setAttribute("listaTurno", negTur.listarTurnos());	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarTurnos.jsp?buscarLista=1");
				dispatcher.forward(request, response);
				break;
			}
			case "listBT":
			{
				request.setAttribute("listaTurno", negTur.listarTurnos());	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/BusquedaTurnos.jsp?buscarBTLista=1");
				dispatcher.forward(request, response);
				break;
			}
			default:
				break;
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//PARA BUSCAR MEDICOS SEGUN ESPECIALIDAD
		Turno x = new Turno();

		
		if(request.getParameter("btnBuscarMedicos")!=null)
	    {	
			Especialidad especialidad = new Especialidad();
			especialidad = negEsp.obtenerUno(Integer.parseInt(request.getParameter("comboEspecialidad")));
			x.setID_especialidad(especialidad); //SETEO LA ESPECIALIDAD EN EL TURNO
				
			System.out.println(especialidad.getDescripcion());
			
			request.setAttribute("listaEsp", negEsp.listarEspecialidades());//VUELVO A CARGAR LAS ESPECIALIDADADES
			request.setAttribute("espSeleccionada", especialidad.getDescripcion());//CARGO LA DESCRIPCION DE LA ESPECIALIDAD
			request.setAttribute("listaMed", negMed.obtenerxEspecialidad(especialidad.getID()));//BUSCO EL LISTADO DE MEDICOS CON ESA ESPECIALIDAD
			request.setAttribute("listaPac", negPac.listarPacientes());
			request.setAttribute("idEspecialidad", especialidad.getID()); //ENVIO A LA PAGINA EL ID DE ESPECIALIDAD
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Turnos.jsp");//ENVIO TODO A TURNOS
			dispatcher.forward(request, response);
	    }
				
		//PARA AGREGAR TURNO
		if(request.getParameter("btnAceptar")!=null)
		    {		 
					Especialidad especialidad = new Especialidad();
					especialidad = negEsp.obtenerUno(Integer.parseInt(request.getParameter("seleccionEspecialidad")));
					x.setID_especialidad(especialidad); //SETEO LA ESPECIALIDAD EN EL TURNO
		    			
		    		Estado estado = new Estado();
		    		estado.setID(2); //CORRESPONDE A "OCUPADO"
		    		x.setID_estado(estado);//SETEO EL ESTADO DEL TURNO
		    									
					Medico medico = new Medico();
					medico.setDni(request.getParameter("comboMedico"));
					x.setDNI_medico(medico); //SETEO EL MEDICO SEGUN LA ESPECIALIDAD
					
					Paciente paciente = new Paciente();
					paciente.setDni(request.getParameter("comboPaciente"));
					x.setDNI_paciente(paciente); //SETEO EL PACIENTE
					
					x.setObservacion(request.getParameter("txtObservacion")); //SETEO LA OBSERVACION
		    															
					//IMPORTANTISIMO PARA FECHA					
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
					Date fecha = null;
					try {
						fecha = formato.parse(request.getParameter("txtFecha"));
						x.setFecha(fecha);//SETEO LA FECHA			 			
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					
					
					//LOGICA PARA VERIFICAR SI EL MEDICO PUEDE ATENDER EN ESE DIA
					int dia = fecha.getDay();
					DisponibilidadxMedico dxmedico = new DisponibilidadxMedico();
					dxmedico.setDNI_medico(medico);
					dxmedico.setDia(dia);
					boolean puede = negDxMed.verificar(dxmedico);
					
					if(puede) {
						System.out.println("EL MEDICO PUEDE ATENDER ESE DIA DE SEMANA");
					}
					else {
						String mensaje = "";
						mensaje = "El médico no puede atender ese día de la semana.";
						request.setAttribute("listaEsp", negEsp.listarEspecialidades());
						request.setAttribute("listaPac", negPac.listarPacientes());
						request.setAttribute("estadoTurno", mensaje);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/Turnos.jsp");
						dispatcher.forward(request, response);		
						response.sendRedirect("Turnos.jsp");
					}
								
					
					//IMPORTANTISIMO PARA HORA	
					SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm"); 
					Date hora;
					try {
						hora = formatoHora.parse(request.getParameter("comboHora"));
						x.setHora(hora);//SETEO LA HORA
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
								
					//VERIFICO SI EL MEDICO YA TIENE UN TURNO ASIGNADO EN ESA FECHA Y HORARIO
					boolean status = negTur.consultaTurnoxFechayHoraDNIMed(x);
					
					
					String mensaje = "";
					if(status){
						mensaje = "El médico ya tiene un turno asignado para la hora indicada. Seleccione otro horario.";
						request.setAttribute("listaEsp", negEsp.listarEspecialidades());
						request.setAttribute("listaPac", negPac.listarPacientes());
						request.setAttribute("estadoTurno", mensaje);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/Turnos.jsp");
						dispatcher.forward(request, response);
						response.sendRedirect("Turnos.jsp");
					}
					else {		
						
					negTur.insertar(x);
					mensaje = "Turno asignado con éxito.";
					request.setAttribute("listaEsp", negEsp.listarEspecialidades());
					request.setAttribute("listaPac", negPac.listarPacientes());
					request.setAttribute("estadoTurno", mensaje);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Turnos.jsp");
					dispatcher.forward(request, response);
					response.sendRedirect("Turnos.jsp");
					
					}
					
		    }
		 
		//BUSQUEDA POR DNI
		 if(request.getParameter("btnBuscar")!=null)
		 {
		    	String parametro = request.getParameter("txtBuscar");	    				
		    	request.setAttribute("listaTurno", negTur.obtenerBusqueda(parametro));	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/BusquedaTurnos.jsp?buscarBTLista=1");
				dispatcher.forward(request, response);
				
					
		 }
		 
	
		 
		//BUSQUEDA DINAMICA COMBO
		 if(request.getParameter("btnBuscarEstado")!=null)
		 {
		    	int idEstado = Integer.parseInt(request.getParameter("comboEstado"));
		    	String parametro = "";
		    	switch (idEstado) {
				case 1:
					parametro = "LIBRE";
					
					break;
					
				case 2:
					
					parametro = "OCUPADO";
					
					break;
					
				case 3:
					
					parametro = "AUSENTE";
					
					break;

				case 4:
					
					parametro = "PRESENTE";
				
					break;
				default:
					
					break;
				}
		    	
				request.setAttribute("listaTurno", negTur.obtenerBusquedaxEstado(parametro));	
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/BusquedaTurnos.jsp?buscarBTLista=1");
				dispatcher.forward(request, response);
				
					
		 }
		 
		 
		    
			if(request.getParameter("btnPresente")!=null) 
			{
				int id = Integer.parseInt(request.getParameter("idTurno"));								
				Turno turno = new Turno();
				turno = negTur.obtenerUno(id);
				
				boolean estado = negTur.cambiarEstadoPresente(id);		
					
				request.setAttribute("datosTurno", turno);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarObservacion.jsp");
				dispatcher.forward(request, response);					
			}
		 
		 	//PARA CAMBIAR A PRESENTE - ADMINISTRADOR
			if(request.getParameter("btnPresente")!=null) 
			{
				int id = Integer.parseInt(request.getParameter("idTurno"));								
				Turno turno = new Turno();
				turno = negTur.obtenerUno(id);
				
				boolean estado = negTur.cambiarEstadoPresente(id);		
					
				request.setAttribute("datosTurno", turno);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarObservacion.jsp");
				dispatcher.forward(request, response);					
			}
			
			//PARA CAMBIAR A AUSENTE - ADMINISTRADOR
			if(request.getParameter("btnAusente")!=null) 
			{
				int id = Integer.parseInt(request.getParameter("idTurno"));									
				boolean estado = negTur.cambiarEstadoAusente(id);							
				request.setAttribute("estadoTurno", "Turno cambiado como AUSENTE");	
				request.setAttribute("listaTurno", negTur.listarTurnos());	
				response.sendRedirect("ListarTurnos.jsp");
				/*RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarTurnos.jsp");	
				dispatcher.forward(request, response);	*/				
			}
			
			//PARA CAMBIAR A LIBRE - ADMINISTRADOR
			if(request.getParameter("btnLibre")!=null) 
			{
				int id = Integer.parseInt(request.getParameter("idTurno"));									
				boolean estado = negTur.cambiarEstadoLibre(id);							
				request.setAttribute("estadoTurno", "Turno cambiado como LIBRE");
				request.setAttribute("listaTurno", negTur.listarTurnos());	
				response.sendRedirect("ListarTurnos.jsp");
				/*RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarTurnos.jsp");	
				dispatcher.forward(request, response);	*/				
			}
			
	
			//DATOS DE PACIENTE - AMBOS USUARIOS
			if(request.getParameter("btnPaciente")!=null) 
			{
				String dni = request.getParameter("dniPaciente");	
				Paciente paciente = new Paciente();
				paciente = negPac.obtenerUno(dni);									
				request.setAttribute("dniPac", paciente);		
				RequestDispatcher dispatcher = request.getRequestDispatcher("/DatosPaciente.jsp");	
				dispatcher.forward(request, response);					
			}
			
			
			//PARA AGREGAR OBSERVACION - AMBOS USUARIOS
			if(request.getParameter("btnAgregarObservacion")!=null) 
			{
				int id = Integer.parseInt(request.getParameter("idTurno"));								
				Turno turno = new Turno();
				turno = negTur.obtenerUno(id);
				turno.setObservacion(request.getParameter("txtObservacion"));	
				
				boolean estado = negTur.agregarObservacion(turno);					
				request.setAttribute("estadoObservacionTurno", estado);			
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarObservacion.jsp");
				dispatcher.forward(request, response);					
			}
	}

}
