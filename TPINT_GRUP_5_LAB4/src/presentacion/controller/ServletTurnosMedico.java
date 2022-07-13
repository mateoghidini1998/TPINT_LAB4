package presentacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import entidad.Especialidad;
import entidad.Medico;
import entidad.Paciente;
import entidad.Turno;
import entidad.Estado;

import negocio.TurnoNeg;
import negocioImpl.TurnoNegImpl;
import negocio.PacienteNeg;
import negocioImpl.PacienteNegImpl;
import negocio.EspecialidadNeg;
import negocio.MedicoNeg;
import negocioImpl.EspecialidadNegImpl;
import negocioImpl.MedicoNegImpl;
/**
 * Servlet implementation class ServletTurnosMedico
 */
@WebServlet("/ServletTurnosMedico")
public class ServletTurnosMedico extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final TurnoNeg negTur = new TurnoNegImpl();
	private static final EspecialidadNeg negEsp = new EspecialidadNegImpl(); 
    private static final MedicoNeg negMed = new MedicoNegImpl();
    private static final PacienteNeg negPac = new PacienteNegImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTurnosMedico() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("buscarLista") != null) {
			Usuario usuarioMedico = (Usuario)request.getSession().getAttribute("usuario");
			request.setAttribute("listaTurnosMedico", negTur.obtenerPorDniMedico(usuarioMedico.getDni()));
		}
		request.getRequestDispatcher("ListarTurnosMedico.jsp?listaBuscada=1").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnConfirmarCambios") != null) {
			int idTurno = Integer.parseInt((request.getParameter("idTurno").toString()));
			int idEstadoTurno = request.getParameter("asistencia") != null ? Integer.parseInt((request.getParameter("asistencia").toString())) : 0;
			String observaciones = request.getParameter("observaciones").toString();
			switch (idEstadoTurno) {
				case 4:
					negTur.cambiarEstadoPresente(idTurno);
					break;
				case 3: 
					negTur.cambiarEstadoAusente(idTurno);
					break;
				default: 
					break;
			}
			Turno turno = new Turno();
			turno.setId(idTurno);
			turno.setObservacion(observaciones);
			negTur.agregarObservacion(turno);
		}
		response.sendRedirect("ListarTurnosMedico.jsp?cambioExitoso=1");
	}

}