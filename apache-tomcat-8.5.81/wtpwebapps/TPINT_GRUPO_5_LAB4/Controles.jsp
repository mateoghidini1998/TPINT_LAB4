<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title> Agregar usuarios</title>
</head>
<body>

<div class="encabezado"></div>


<div class="parteIzq">

		<div class="menu">
		<ul>
			<li> <a href="Home.jsp"> <span class="fa fa-home"></span> Controles HTML5</a> 
			
					<ul>
						<li><a href="Controles.jsp">Controles</a></li>
					 	<li><a href="Validaciones.jsp">Validaciones</a></li>
					    <li><a href="Propiedades.jsp">Propiedades</a></li>
					</ul>
			
			</li>
		</ul>
		</div>

</div>
<div class="parteDer" >

<h2> Controles HTML5</h2>

<form method="post" action="ServletHTML">

	  <p>  Color:  <input type="color" name="Color"></input>
           Fecha: <input type="date" name="Fecha"></input>
      <p>  Fecha hora: <input type="datetime-local" name="FechaHoraLocal"></input>
           Hora: <input type="time" name="Hora"></input></p>
      <p>  Archivos planos:  <input type="file" name="File"></input></p>
      <p>  Email: <input type="email" name="Email"></input>
      	   Mes:  <input type="month" name="Mes"></input></p>
      <p>  Numeros: <input type="number" name="Numero"></input>
           Rango de numeros:  <input type="range" min="0" max="10" name="Rango"> </input></p>
      <p>  Busqueda: <input type="search" name="Busqueda"></input>
           Telefono: <input type="tel" name="Telefono"></input></p>
      <p>  Direccion URL: <input type="url" name="Url"></input>
           Semana: <input type="week" name="Semana"></input></p>
      <p>  Reset: <input type="reset"></input></p>
      <p>  Boton envio de formulario: <input type="submit" name="btnEnviarControles" value="Enviar"></input></br></p>
</form>


<% if(request.getAttribute("ControlesSelecciones")!=null) {%>
		<%=request.getAttribute("ControlesSelecciones") %>
	<%}%>

 </div>

</body>
</html>