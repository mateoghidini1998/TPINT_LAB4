<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css/StyleSheet.css"></jsp:include>
</style>
<title>Insert title here</title>
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
<div class="parteDer"> 

<form method="get" action="ServletHTML">
  <h2>Validaciones realizadas con la palabra required</h2>

    <fieldset>
      <legend>Contacto</legend>
      <p>
        <label for="nombres">Nombre</label>
        <input id="nombres" type="text" placeholder="Ingrese sus nombres" required name="txtNombre">
      </p>
      <p>
        <label for="apellidos">Apellidos</label>
        <input id="apellidos" type="text" placeholder="Ingrese sus apellidos" required name="txtApellido">
      </p>
      <p>
        <label for="correo">Correo Electrónico</label>
        <input id="correo" type="email" placeholder="Ingrese correo electronico" required name="txtCorreo">
      </p>
      
      <p>
     Fecha<input type="text" required pattern="\d{1,2}/\d{1,2}/\d{4}" title="La fecha no es v&aacute;lida">
<br>
      </p>
      
      <p>
        <label for="edad">Edad</label>
        <input id="edad" type="number" placeholder="Ingrese su edad" required name="txtEdad">
      </p>
      <p>
        <input id="btnEnviar" type="submit" value="Enviar" required name="btnEnviarValidaciones">
      </p>
    </fieldset>

</form>


<% if(request.getAttribute("ControlesSelecciones")!=null) {%>
		<%=request.getAttribute("ControlesSelecciones") %>
	<%}%>

</div>


</body>
</html>