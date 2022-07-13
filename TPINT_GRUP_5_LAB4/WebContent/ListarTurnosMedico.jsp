<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Turno"%>
<%@page import="java.util.List"%>
<%@page import="excepciones.UsuarioSinPermisoException"%>
<%@page import="entidad.Usuario"%>
<%@page import="excepciones.UsuarioNoLoggeadoException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Turnos asociados</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css\StyleSheetMain.css"></jsp:include>
</style>
<style type="text/css">
	<jsp:include page="css\StyleSheetListarPacientes.css"></jsp:include>
</style>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
	<% if (request.getSession().getAttribute("usuario") == null) { 
		request.getRequestDispatcher("Login.jsp").forward(request, response);
		throw new UsuarioNoLoggeadoException();
	} 
	 Usuario user = (Usuario)request.getSession().getAttribute("usuario");
	 if (user.getTipo_usuario().getID() != 2) { 
			request.getRequestDispatcher("Home.jsp").forward(request, response);
			throw new UsuarioSinPermisoException();
	}
	 List<Turno> listaT = new ArrayList<Turno>();
	 if (request.getParameter("listaBuscada") == null) {
	 	request.getRequestDispatcher("ServletTurnosMedico?buscarLista=1").forward(request, response);
	 }
	 else {
		 if (request.getAttribute("listaTurnosMedico") != null){
     	listaT = (List<Turno>) request.getAttribute("listaTurnosMedico");
		 }
	 }
	 
	
	%>
	<jsp:include page="Menu.jsp"></jsp:include>
	
	
	<div class=" container tabla">
		<h3 class="mb-4">Tus turnos asignados</h3>
	 <table class="table">
		<thead>
		<tr>
			<th class="text-center">ID</th>
			<th class="text-center">Paciente</th>
			<th class="text-center">Fecha</th>
			<th class="text-center">Horario</th>
			<th class="text-center">Estado</th>
			<th class="text-center">Observación</th>
			<th class="text-center" COLSPAN="4">Acciones</th>
		</tr>
		</thead>
		<tbody>
			<%
				for (Turno a : listaT) {
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");		
					String fechaComoCadena = ""; 
				    fechaComoCadena = formato.format(a.getDNI_paciente().getFechaNac());
			%>
				
				<tr class="text-center">				
					<td><%= a.getId() %><input type="hidden" name="idTurno" value="<%=a.getId()%>" ></td>
					<td><%= a.getDNI_paciente().getApellido()+", "+ a.getDNI_paciente().getNombre() %><input type="hidden" name="dniPaciente" value="<%= a.getDNI_paciente().getDni()%>" ></td>
					<td><%=a.getFecha() %></td>
					<td><%=a.getHora() %></td>
					<td><%=a.getID_estado().getDescripcion()%></td>
					<td><%=a.getObservacion()%></td>
					<td class="d-flex justify-content-center" COLSPAN="2">   
						<button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#form-ficha-paciente">
	    							Ficha de paciente
	  					</button>     
	               </td>
	               <td class="d-flex justify-content-center" COLSPAN="2">   
						<button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#form-actualizar-turno">
	    							Actualizar turno
	  					</button>     
	               </td>
						<!-- <td> <input type="submit" name="btnPresenteM" value="PRESENTE" class="btn btn-warning"></td>
						<td> <input type="submit" name="btnAusenteM" value="AUSENTE" class="btn btn-danger"></td>
						 -->
					</form>
					
				</tr>
				<div class="modal fade" id="form-ficha-paciente" tabindex="-1" role="dialog" aria-hidden="true">
					  <div class="modal-dialog modal-dialog-centered" role="document">
					    <div class="modal-content">
					      <div class="modal-header border-bottom-0">
					        <h5 class="modal-title text-dark">Ficha de paciente</h5>
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">&times;</span>
					        </button>
					      </div>
	                      <div class="modal-body">
				          		<ul class="text-dark">
				          			<li>Nombre completo: <%= a.getDNI_paciente().getNombre()+" "+ a.getDNI_paciente().getApellido() %></li>
							        <li>DNI: <%= a.getDNI_paciente().getDni() %></li>
							        <li>Correo electrónico: <%= a.getDNI_paciente().getCorreo() %></li>
							        <li>Teléfono fijo: <%= a.getDNI_paciente().getTelefono() %></li>
							        <li>Celular: <%= a.getDNI_paciente().getCelular() %></li>
							        <li>Fecha de nacimiento: <%= fechaComoCadena %></li>
									<li>Nacionalidad: <%= a.getDNI_paciente().getNacionalidad() %></li>
							        <li>Localidad: <%= a.getDNI_paciente().getLocalidad() %></li>
									<li>Provincia: <%= a.getDNI_paciente().getProvincia() %></li>
							        <li>Dirección: <%= a.getDNI_paciente().getDireccion() %></li>
							        <li>Sexo: <%= a.getDNI_paciente().getSexo() %></li>
				          		</ul>
				          </div>
					    </div>
					  </div>
				</div>
				<div class="modal fade" id="form-actualizar-turno" tabindex="-1" role="dialog" aria-hidden="true">
					  <div class="modal-dialog modal-dialog-centered" role="document">
					    <div class="modal-content">
					      <div class="modal-header border-bottom-0">
					        <h5 class="modal-title text-dark">Actualizar turno</h5>
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">&times;</span>
					        </button>
					      </div>
	                      <div class="modal-body text-dark">
								<form method="post" action="ServletTurnosMedico">
									<div class="d-flex justify-content-around align-items-center mb-2">	
										<label>El paciente: </label>
										<div class="form-check d-flex align-items-center">
										  <input class="form-check-input" type="radio" name="asistencia" id="asistio" value="4">
										  <label class="form-check-label" for="asistio">
										    Asistió
										  </label>
										</div>
										<div class="form-check d-flex align-items-center">
										  <input class="form-check-input" type="radio" name="asistencia" id="noAsistio" value="3">
										  <label class="form-check-label" for="noAsistio">
										    No asistió
										  </label>
										</div>
									</div>
									 <div class="form-group">
									    <label for="observaciones">Observaciones: </label>
									    <textarea class="form-control" id="observaciones" name="observaciones" rows="3"><%= a.getObservacion() %></textarea>
									 </div>
									 <div class="d-flex justify-content-center">
									 	<input class="d-none" name="idTurno" value="<%= a.getId() %>">
									 	<button class="btn btn-success" type="submit" name="btnConfirmarCambios">Guardar cambios</button>
									 </div>	
								</form>
				          </div>
					    </div>
					  </div>
				</div>
			<%
				}
			%>
		</tbody>
		</table>
	</div>	
	  
   <% if(request.getParameter("cambioExitoso")!= null) { %>
   	<script type="text/javascript">
   		swal("¡Listo!", "El turno se ha modificado exitosamente", "success");
   	</script>
   <% } %>
	
	
</body>
</html>