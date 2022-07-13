<%@page import="excepciones.UsuarioSinPermisoException"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Especialidad"%>
<%@page import="entidad.Medico"%>
<%@page import="entidad.Paciente"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="excepciones.UsuarioNoLoggeadoException"%>
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
	
	
	<%
		List<Especialidad> listaE = new ArrayList<Especialidad>();
		if (request.getAttribute("listaEsp") != null) {
			listaE = (List<Especialidad>)request.getAttribute("listaEsp");
		}
	%>
	
	<%
		List<Medico> listaM = new ArrayList<Medico>();
		if (request.getAttribute("listaMed") != null) {
			listaM = (List<Medico>)request.getAttribute("listaMed");
		}
	%>
	
	<%
		List<Paciente> listaP = new ArrayList<Paciente>();
		if (request.getAttribute("listaPac") != null) {
			listaP = (List<Paciente>)request.getAttribute("listaPac");
		}
	%>
	
		<%
		SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        Date dateObj = calendar.getTime();
        String formattedDate = dtf.format(dateObj);
      
		%>
	
	
	
     
	
	
	
	<jsp:include page="Menu.jsp"></jsp:include>

	<form method="post" action="ServletTurnos">
	 		<!-- ESPECIALIDAD  -->
	        <div class="form-group">
	          <label>Seleccione una Especialidad: </label>	          
	          <select class="form-control col-8" name="comboEspecialidad" >
	          	<!-- Esto debería leerlo desde una DB -->
	            		<%
							for (Especialidad e : listaE) {								
						%>
						<option value="<%=e.getID()%>"><%=e.getDescripcion() %></option>
						<%
							}
						%>
	          </select>
	          
	          <input type="submit" class="btn btn-success" value="Buscar" name="btnBuscarMedicos">
	        </div>
	       </form>
	        
	           <%
				if (request.getAttribute("espSeleccionada") != null) {
				%>
		
				<%="Especialidad Seleccionada: " + request.getAttribute("espSeleccionada")%>
		
				<%
				}
				%>
				
 		<form onSubmit="confirmarAgregar()" method="post" action="ServletTurnos">
	             
	             	<!-- ESPECIALIDAD TOMADA DEL FORM ANTERIOR -->
	             <%
	             int idEsp = 0;	
				if (request.getAttribute("idEspecialidad") != null) {
					idEsp = (int)request.getAttribute("idEspecialidad");
				}
				%>
		
				<input type="hidden" name="seleccionEspecialidad" value="<%=idEsp%>">
	              
	        
	        <!-- MEDICOS  -->
	        <div class="form-group">
	          <label>Seleccione un Médico: </label>
	          <select class="form-control col-8" name="comboMedico" >
	          	<!-- Esto debería leerlo desde una DB -->
	            		<%
							for (Medico m : listaM) {
						%>
						<option value="<%=m.getDni()%>"><%=m.getApellido() + ", " + m.getNombre() %></option>
						<%
							}
						%>
	          </select>
	        </div>
	        
	         <!-- PACIENTES  -->
	        <div class="form-group">
	          <label>Seleccione un Paciente: </label>
	          <select class="form-control col-8" name="comboPaciente" >
	      
	            		<%
							for (Paciente p : listaP) {
						%>
						<option value="<%=p.getDni()%>"><%=p.getApellido() + ", " + p.getNombre() %></option>
						<%
							}
						%>
	          </select>
	        </div>
	        
	        
	         <!-- FECHA  -->
	         <div class="form-group">
	          <label>Fecha: </label>
	          <input type="date" class="form-control col-8" value="<%=formattedDate%>" min="<%=formattedDate%>" name="txtFecha" required >
	        </div>
	        
	        <!-- HORA  -->	      
	        <div class="form-group">
	          <label>Hora: </label>
	          <select class="form-control col-8" name="comboHora" >
	                  		
						<option value="07:00">07:00</option>
						<option value="08:00">08:00</option>
						<option value="09:00">09:00</option>
						<option value="10:00">10:00</option>
						<option value="11:00">11:00</option>
						<option value="12:00">12:00</option>
						<option value="13:00">13:00</option>
						<option value="14:00">14:00</option>
						<option value="15:00">15:00</option>
						<option value="16:00">16:00</option>
						<option value="17:00">17:00</option>
					
	          </select>
	        </div>
	        	        	        
	        
	         <!-- OBSERVACIONES  -->
	         <div class="form-group">
	          <label>Observaciones: </label>
	          <input type="text" style="WIDTH: 228px; HEIGHT: 98px" class="form-control" name="txtObservacion" required>
	        </div>
	           
	       <div class="col-12">
	        	<input type="submit" class="btn btn-success" value="Aceptar" name="btnAceptar">
	        	
	        </div>
	      </form>
	      
	      
	           
		 <%if (request.getAttribute("estadoTurno") != null) { %>
		
		<%=request.getAttribute("estadoTurno")%>
		
		<%
			}
		%>
		
	  <script>
		  function confirmarAgregar(event){
					
					var result = confirm("Esta seguro que desea agregar este Turno?");
					
							if (result === false) {
							    event.preventDefault();
							    
							  }
					
				}
	  </script>

</body>
</html>