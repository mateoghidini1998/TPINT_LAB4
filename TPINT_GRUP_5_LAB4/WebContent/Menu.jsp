<%@ page import="entidad.Usuario" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Menu</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css\StyleSheetMain.css"></jsp:include>
</style>
<style type="text/css">
	<jsp:include page="css\StyleSheetMenu.css"></jsp:include>
</style>
<script src="https://kit.fontawesome.com/dc241c5998.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <a class="navbar-brand" href="Home.jsp"><img src="images/utn.svg" width="40" height="35"></a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item">
	        <a class="nav-link" href="Home.jsp"><i class="fa-solid fa-house"></i> Home</a>
	      </li>
	      <% Usuario user = (Usuario) request.getSession().getAttribute("usuario");
	      	if (user.getTipo_usuario().getID() == 1) {
	      %>
	      <li class="nav-item dropdown form-inline my-2 my-lg-0">
	        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          <i class="fa-solid fa-user"></i> Pacientes
	        </a>
	        <div class="dropdown-menu bg-dark" aria-labelledby="navbarDropdown">
	          <a class="dropdown-item text-light" href="FormularioPaciente.jsp">Dar de alta</a>
	          <a class="dropdown-item text-light" href="ServletPacientes?Param=list">Listar/Modificar/Eliminar</a>
	          <a class="dropdown-item text-light" href="ServletPacientes?Param=listaBP">Busqueda</a>
	        </div>
	      </li>
	      <li class="nav-item dropdown form-inline my-2 my-lg-0">
	        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          <i class="fa-solid fa-user-doctor"></i> Médicos
	        </a>
	        <div class="dropdown-menu bg-dark" aria-labelledby="navbarDropdown">
	          <a class="dropdown-item text-light" href="ServletMedicos?Param=previoInsert">Dar de alta</a>
	          <a class="dropdown-item text-light" href="ServletMedicos?Param=list">Listar/Modificar/Eliminar</a>
	          <a class="dropdown-item text-light" href="ServletMedicos?Param=listaBM">Busqueda</a>
	        </div>
	      </li>
	      <li class="nav-item dropdown form-inline my-2 my-lg-0">
	        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          <i class="fa-solid fa-calendar-check"></i> Turnos
	        </a>
	        <div class="dropdown-menu bg-dark" aria-labelledby="navbarDropdown">
	          <a class="dropdown-item text-light" href="ServletTurnos?Param=previoInsert">Asignar turno</a>
	          <a class="dropdown-item text-light" href="ServletTurnos?Param=list">Listar/Modificar/Eliminar</a>
	          <a class="dropdown-item text-light" href="ServletTurnos?Param=listBT">Busqueda</a>
	        </div>
	      </li>
	      <% } 
	      	if (user.getTipo_usuario().getID() == 2)
	      	{
	      %>
	      	<li class="nav-item">
	        	<a class="nav-link" href="ServletTurnosMedico?buscarLista=1"><i class="fa-solid fa-calendar-check"></i> Mis Turnos</a>
	      	</li>
	      <% } %>	
	      <li class="nav-item dropdown form-inline my-2 my-lg-0">
	        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          <i class="fa-solid fa-circle-user"></i> <%= ((Usuario)session.getAttribute("usuario")).getNombre_usuario() %>
	        </a>
	        <div class="dropdown-menu bg-dark" aria-labelledby="navbarDropdown">
	          <a class="dropdown-item text-light" href="ServletLogin?logout=1">Logout</a>
	          <%if (user.getTipo_usuario().getID()==1){ %>
	          <a class="dropdown-item text-light" href="ServletUsuarios?Param=previoInsert">Dar de alta</a>
	          <%} %>
	        </div>
	      </li>
	    </ul>
	  </div>
	</nav> 
</body>
</html>
