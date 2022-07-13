<%@page import="entidad.Usuario"%>
<%@page import="excepciones.UsuarioNoLoggeadoException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<style type="text/css">
	<jsp:include page="css\StyleSheetLogin.css"></jsp:include>
</style>
<title>Home</title>
</head>
<body>
	<% if (request.getSession().getAttribute("usuario") == null) { 
		request.getRequestDispatcher("Login.jsp").forward(request, response);
		throw new UsuarioNoLoggeadoException();
	} 
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
	%>
	<jsp:include page="Menu.jsp"></jsp:include>
	<h1> ¡Bienvenido/a, <%=user.getNombre_usuario()  %>!</h1>
	<h4><footer class="blockquote-footer" style="color: #a1cdc1;"> Permisos de usuario: <%=user.getTipo_usuario().getDescripcion() %></footer></h4>
	<div class="d-flex flex-row justify-content-center">
		<img class="col-8 mt-5" src="images/utn.svg" alt="logo de la utn" width="300" height="200">
	</div>
</body>
</html>