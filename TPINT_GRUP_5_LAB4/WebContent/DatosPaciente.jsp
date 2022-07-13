<%@page import="excepciones.UsuarioSinPermisoException"%>
<%@page import="entidad.Usuario"%>
<%@page import="excepciones.UsuarioNoLoggeadoException"%>
<%@page import="entidad.Paciente" %>
<%@page import="datosImpl.PacienteDaoImpl" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar paciente</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<style type="text/css">
	<jsp:include page="css\StyleSheetMain.css"></jsp:include>
</style>
<script src="https://kit.fontawesome.com/dc241c5998.js" crossorigin="anonymous"></script>
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
	 
	 List<Paciente> listaM = new ArrayList<Paciente>();
		if (request.getAttribute("listaPac") != null) {
			listaM = (List<Paciente>)request.getAttribute("listaPac");
		}
		
		
	//PARA MOSTRAR DATOS
	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");		
	String fechaComoCadena = "";
	
	Paciente p = new Paciente();
	if (request.getAttribute("dniPac") != null) {
	p = (Paciente)request.getAttribute("dniPac");
	
	fechaComoCadena = formato.format(p.getFechaNac());
	
	}
	
	 
	%>
	
	<jsp:include page="Menu.jsp"></jsp:include>
	<h1 class="h1 mb-5" >Datos de Paciente</h1>
	 
	    
      
	          <br>
	          <label>Paciente: <%=p.getApellido()%>, <%=p.getNombre()%></label><br>
	          <label>DNI: <%=p.getDni()%></label><br>
	          <label>Correo electrónico: <%=p.getCorreo()%></label><br>            	      
	          <label>Teléfono: <%=p.getTelefono()%></label><br>
	          <label>Celular: <%=p.getCelular()%></label><br>	     	   
	          <label>Fecha de nacimiento:  <%=fechaComoCadena%></label><br>
	          <label>Nacionalidad: <%=p.getNacionalidad()%></label><br>   
	          <label>Localidad: <%=p.getLocalidad()%></label><br>	        
	          <label>Provincia: <%=p.getProvincia()%></label><br>    
	          <label>Dirección: <%=p.getDireccion()%></label><br>    
	          <label>Sexo:  <%=p.getSexo()%></label><br>
      
	           	
	     
	

</body>
</html>