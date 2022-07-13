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

<h2>Propiedades de los Input</h2>

<form method="get" action="ServletHTML">
<p>
      Solo leer
      <input type="text" value="Hola" readonly="true" name="txtLeer"></input>
</p>

    <p>
          Texto con solo 4 caracteres
          <input type="text" maxlength="4"  name="txtCaracter"></input>
   </p>
    <p>
          Texto con value por defecto por si no completa el usuario
          <input type="text" value="Texto" name="txtValue"></input>
   </p>
    <p>
          Texto con marca de agura
          <input type="text" placeholder="Texto" name="txtMarcaAgua"></input>
    </p>
     <p>
          Numero maximo 10
          <input type="number" max="10" name="txtMaximo"></input>
   </p>
    <p>
          Numero minimo 0
          <input type="number" min="0" name="txtMinimo"></input>
   </p>
    <p>
          Numero con Step (incrementos)
          
          <input type="number" min="0" max="100" step="10" name="txtIncremento"></input>
   </p>
   <p>
          Area de texto
          <textarea name="area_texto" rows="8" cols="40" name="txtArea"></textarea>
    </p>
   <p>
       <input type="submit" value="Enviar" name="btnEnviarPropiedades">
    </p>


</form>
<% if(request.getAttribute("ControlesSelecciones")!=null) {%>
		<%=request.getAttribute("ControlesSelecciones") %>
	<%}%>
</div>

</body>
</html>