<%@page import="excepciones.UsuarioSinPermisoException"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Medico"%>
<%@page import="entidad.TiposUsuario"%>
<%@page import="excepciones.UsuarioNoLoggeadoException"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrar Usuario</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


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
	%>
	
	<%
		List<TiposUsuario> listaTu = new ArrayList<TiposUsuario>();
		if (request.getAttribute("listaTipoU") != null) {
			listaTu = (List<TiposUsuario>)request.getAttribute("listaTipoU");
		}
	%>
	
	<%
		List<Medico> listaM = new ArrayList<Medico>();
		if (request.getAttribute("listaMed") != null) {
			listaM = (List<Medico>)request.getAttribute("listaMed");
		}
	%>
	
	<jsp:include page="Menu.jsp"></jsp:include>
	<div class="container">
	  <div class="row">
	    <div class="col-12">
	     <form onSubmit="Validaciones(event)" method="post" action="ServletUsuarios">
			<h1 class="h1 mb-5" >Ingresar nuevo Usuario</h1>
	      
	        <div class="form-group">
	          <label>Nombre de Usuario: </label>
	          <input type="text" class="form-control" name="txtNombre" title="Ingrese su nombre" required />
	        </div>
	        <div class="form-group">
	          <label>Contraseña: </label>
	          <input type="password" class="form-control password" name="txtPassword" required>
	        </div>
	        <div class="form-group">
	          <label>Repetir Contraseña: </label>
	          <input type="password" class="form-control confirmPassword"  name="txtConfirmarPassword" required>
	        </div>
	         <div class="form-group">
	          <label>Médico: </label>
	          <select class="form-control" name="comboMedicos" >
	          	<!-- Esto debería leerlo desde una DB -->
	          	
	          			<option selected value="">Seleccionar...</option>
	            		<%
							for (Medico m : listaM) {
						%>
						<option value="<%=m.getDni()%>"><%=m.getApellido() + ", " + m.getNombre() %></option>
						<%
							}
						%>
	          </select>
	        </div>
	        
	        
	        <div class="form-group">
	          <label>Correo electrónico: </label>
	          <input type="email" class="form-control" name="txtCorreo" required>
	        </div>
	        
	        
	         <div class="form-group">
	          <label>Tipo de Usuario: </label>
	          <select class="form-control " name="comboTipoUsuario" >
	          	<!-- Esto debería leerlo desde una DB -->
	          			<option value="" selected>Seleccionar...</option>
	            		<%
							for (TiposUsuario tu : listaTu) {
						%>
						<option value="<%=tu.getID()%>"><%=tu.getDescripcion()%></option>
						<%
							}
						%>
	          </select>
	        </div>
	        
	        
	        
	        <div class="col-12">
	        	<input onclick="" type="submit" class="btn btn-success" value="Aceptar" name="btnAceptar">
	        	<button type="submit" class="btn btn-outline-info">Limpiar campos</button>
	        </div>
	      </form>
	    <%
		if (request.getAttribute("estadoUsuario") != null) {
		%>
		<%=request.getAttribute("estadoUsuario")%>
		<%
			}
		%>
	    </div>
	  </div>
	</div>
	
	<script>
	
		function confirmarAlta(event) {
	
			  var result = confirm("Esta seguro que desea crear este usuario?");
			  if (result === false) {
			    event.preventDefault();
			    
			  }			  
			  
			  			  
			}
	
			function verificarPassword() {
			  var pass = document.querySelector(".password").value;
			  var confirmPass = document.querySelector(".confirmPassword").value;
			  
	
			  if (pass != confirmPass) {
			    alert("Las contraseñas no coinciden");
			    pass = "";
			    confirmPass = "";
			    return false
			  }
			  return true;
	
			}
	
			function Validaciones(event) {
				  if (verificarPassword() === true) {
					  confirmarAlta(event);
				  } else {
				    event.preventDefault();
				 }
			}
			
	</script>

	<script></script>

</body>
</html>
</html>