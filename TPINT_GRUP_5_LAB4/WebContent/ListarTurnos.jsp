<%@page import="excepciones.UsuarioSinPermisoException"%>
<%@page import="entidad.Usuario"%>
<%@page import="excepciones.UsuarioNoLoggeadoException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="entidad.Turno"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Turnos</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css\StyleSheetMain.css"></jsp:include>
</style>
<style type="text/css">
	<jsp:include page="css\StyleSheetListarPacientes.css"></jsp:include>
</style>
</head>
<body>
	<% if (request.getSession().getAttribute("usuario") == null) { 
		request.getRequestDispatcher("Login.jsp").forward(request, response);
		throw new UsuarioNoLoggeadoException();
	} 
	 Usuario user = (Usuario)request.getSession().getAttribute("usuario");
	 if (user.getTipo_usuario().getID() != 1) { 
			request.getRequestDispatcher("Home.jsp").forward(request, response);
			throw new UsuarioSinPermisoException();
	} 
	%>
	<jsp:include page="Menu.jsp"></jsp:include>
	<%
	 	if (request.getParameter("buscarLista") == null) {
		 	request.getRequestDispatcher("ServletTurnos?Param=list").forward(request, response);
		 }
		List<Turno> listaT = new ArrayList<Turno>();
		if (request.getAttribute("listaTurno") != null) {
			listaT = (List<Turno>) request.getAttribute("listaTurno");
		}
	%>
	<div class="container tabla">
	
	<div class="row">
	
	<div class="col-12">
	
		<h3>Turnos</h3>
		
		
	
		
		<table class="table-fill">
		<thead>
		<tr>
		<th class="text-center">ID</th>
		<th class="text-center">Paciente</th>
		<th class="text-center">Médico Asignado</th>
		<th class="text-center">Especialidad</th>
		<th class="text-center">Fecha</th>
		<th class="text-center">Horario</th>
		<th class="text-center">Estado</th>
		<th class="text-center">Observación</th>
		
	
		</tr>
		</thead>
		<tbody class="table-hover">
		
		<tr>
					<%
				for (Turno a : listaT) {
			%>
				
				<tr>
				<form method="post" action="ServletTurnos">
					<td><%= a.getId() %><input type="hidden" name="idTurno" value="<%=a.getId()%>" ></td>
					<td><%= a.getDNI_paciente().getApellido()+", "+ a.getDNI_paciente().getNombre() %> <input type="hidden" name="dniPaciente" value="<%= a.getDNI_paciente().getDni()%>" ></td>
					<td><%=a.getDNI_medico().getApellido() +", "+ a.getDNI_medico().getNombre()%></td>
					<td><%=a.getID_especialidad().getDescripcion()%></td>
					<td><%=a.getFecha() %></td>
					<td><%=a.getHora() %></td>
					<td><%=a.getID_estado().getDescripcion()%></td>
					<td><%=a.getObservacion()%></td>
					
					<td> <input type="submit" name="btnPaciente" value="PACIENTE" class="btn btn-primary"></td>
					<td> <input type="submit" name="btnPresente" value="PRESENTE" class="btn btn-success"></td>
					<td> <input type="submit" name="btnAusente" value="AUSENTE" class="btn btn-danger"></td>
					<td> <input type="submit" name="btnLibre" value="LIBRE" class="btn btn-warning"></td>
				</form>
					
				</tr>
	
				<%
				}
			%>
		</tbody>
		</table>
	</div>
	</div>
	</div>
	<br>
	

	
	           
		 <%if (request.getAttribute("estadoTurno") != null) { %>
		
		<%=request.getAttribute("estadoTurno")%>
		
		<%
			}
		%>
	

</body>
</html>