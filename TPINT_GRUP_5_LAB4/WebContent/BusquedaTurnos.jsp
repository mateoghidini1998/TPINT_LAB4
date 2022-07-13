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
<title>Busqueda Turnos</title>
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
	 	if (request.getParameter("buscarBTLista") == null) {
		 	request.getRequestDispatcher("ServletTurnos?Param=listBT").forward(request, response);
		 }
		List<Turno> listaT = new ArrayList<Turno>();
		if (request.getAttribute("listaTurno") != null) {
			listaT = (List<Turno>) request.getAttribute("listaTurno");
		}
	%>
	<div class="table-title">
	<h3>Turnos</h3>
	</div>
	
	
	<form method="post" action="ServletTurnos">

	 <label>Buscar por DNI Paciente: </label>
	          <input type="text" class="form-control" name="txtBuscar">
	        
	        
	        <div class="col-12">
	        	<input type="submit" class="btn btn-success" value="Buscar" name="btnBuscar">
	        </div>
	        
	        <div class="form-group">
	          <label>Buscar por Estado: </label>
	          <select class="form-control col-8" name="comboEstado" >
	         
	            <option value="1">LIBRE</option>
	            <option value="2">OCUPADO</option>
	            <option value="3">AUSENTE</option>
	            <option value="4">PRESENTE</option>
	          </select>
	        </div>
	        
	         <div class="col-12">
	        	<input type="submit" class="btn btn-success" value="Buscar" name="btnBuscarEstado">
	        </div>
	 
	
	<table class="table-fill">
	<thead>
	<tr>
	<th class="text-left">ID</th>
	<th class="text-left">Paciente</th>
	<th class="text-left">Médico Asignado</th>
	<th class="text-left">Especialidad</th>
	<th class="text-left">Fecha</th>
	<th class="text-left">Horario</th>
	<th class="text-left">Estado</th>
	<th class="text-left">Observación</th>
	

	</tr>
	</thead>
	<tbody class="table-hover">
	
	<tr>
				<%
			for (Turno a : listaT) {
		%>
			
			<tr>
			
				<td><%= a.getId() %><input type="hidden" name="idTurno" value="<%=a.getId()%>" ></td>
				<td><%= a.getDNI_paciente().getApellido()+", "+ a.getDNI_paciente().getNombre() %> <input type="hidden" name="dniPaciente" value="<%= a.getDNI_paciente().getDni()%>" ></td>
				<td><%=a.getDNI_medico().getApellido() +", "+ a.getDNI_medico().getNombre()%></td>
				<td><%=a.getID_especialidad().getDescripcion()%></td>
				<td><%=a.getFecha() %></td>
				<td><%=a.getHora() %></td>
				<td><%=a.getID_estado().getDescripcion()%></td>
				<td><%=a.getObservacion()%></td>
	
				
			</tr>

			<%
			}
		%>
	</tbody>
	</table>
	<br>
	</form>
	

</body>
</html>