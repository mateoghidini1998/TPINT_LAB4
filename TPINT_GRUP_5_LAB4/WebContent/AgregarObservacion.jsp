<%@page import="excepciones.UsuarioSinPermisoException"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Especialidad"%>
<%@page import="entidad.Medico"%>
<%@page import="entidad.Paciente"%>
<%@page import="entidad.Turno"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="excepciones.UsuarioNoLoggeadoException"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar Turno</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css\StyleSheetMain.css"></jsp:include>
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
	
	<%
	
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");		
			SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
			String fechaComoCadena = "";
			String horaComoCadena = "";
			Turno turno = new Turno();
			
			if (request.getAttribute("datosTurno") != null) {
			turno = (Turno)request.getAttribute("datosTurno");
			//IMPORTANTISIMO PARA FECHA
			fechaComoCadena = formato.format(turno.getFecha());						
			//IMPORTANTISIMO PARA HORA
			horaComoCadena = formatoHora.format(turno.getHora());
			
			}
	%>
	

	
	<jsp:include page="Menu.jsp"></jsp:include>

	 	
 		<form method="post" action="ServletTurnos">
	 		<label>ID:  <%=turno.getId() %> </label>  
	 	
	 	
 		
 		<input type="hidden" name="idTurno" value="<%=turno.getId()%>">
	         <!-- OBSERVACIONES  -->
	         <div class="form-group">
	          <label>Observaciones: </label>
	          <input type="text" style="WIDTH: 228px; HEIGHT: 98px" class="form-control" name="txtObservacion" required>
	        </div>
	           
	       <div class="col-12">
	        	<input type="submit" class="btn btn-success" value="Agregar Observación" name="btnAgregarObservacion">
	        
	        </div>
	      </form>
	      
	     
 <%
	if (request.getAttribute("estadoObservacionTurno") != null) {
	%>
	Turno PRESENTE y Observación agregada con exito
	<%
		}
	%>
	  

</body>
</html>