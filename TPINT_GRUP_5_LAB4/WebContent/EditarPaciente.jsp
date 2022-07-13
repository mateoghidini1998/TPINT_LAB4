<%@page import="excepciones.UsuarioSinPermisoException"%>
<%@page import="entidad.Usuario"%>
<%@page import="excepciones.UsuarioNoLoggeadoException"%>
<%@page import="entidad.Paciente" %>
<%@page import="datosImpl.PacienteDaoImpl" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

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

	<% 
	if (request.getSession().getAttribute("usuario") == null) { 
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
		
		
	//PARA EDITAR
	Paciente p = new Paciente();
	if (request.getAttribute("dniPac") != null) {
	p = (Paciente)request.getAttribute("dniPac");
	}
	
	 
	%>
	
	<jsp:include page="Menu.jsp"></jsp:include>
	  <div class="container">
	  <div class="row">
	    <div class="col-12">
	    
	     <form method="post" action="ServletPacientes">
	      
			<h1 class="h1 mb-5" >Editar paciente</h1>
			<div class="form-row">	      
	          <div class="form-group col-md-4">
	          <label>DNI: </label>
	          <input type="text" class="form-control" name="TxtDNI" value="<%=p.getDni()%>" readonly>
	        </div>
	      
	        
	        <div class="form-group col-md-4">
	          <label>Nombre: </label>
	          <input type="text" onkeypress="return checkLetras(event)" class="form-control" name="TxtNombre" value="<%=p.getNombre()%>">
	        </div>
	        
	        <div class="form-group col-md-4">
	          <label>Apellido: </label>
	          <input type="text" onkeypress="return checkLetras(event)" class="form-control" name="TxtApellido" value="<%=p.getApellido()%>">
	        </div>
	        
	        <div class="form-group col-md-4">
	          <label>Correo electr�nico: </label>
	          <input type="email" class="form-control" name="TxtCorreo" value="<%=p.getCorreo()%>">
	        </div>
	        
	        
	        
	        <div class="form-group col-md-4">
	          <label>Tel�fono: </label>
	          <input type="text" onkeypress="return check(event)" class="form-control" name="TxtTelefono" value="<%=p.getTelefono()%>">
	        </div>
	        
	         <div class="form-group col-md-4">
	          <label>Celular: </label>
	          <input type="text" onkeypress="return check(event)"class="form-control" name="TxtCelular" value="<%=p.getCelular()%>">
	        </div>
	        
	        <div class="form-group col-md-4">
	          <label>Fecha de nacimiento: </label>
	          <input type="date" class="form-control " name="TxtFechaNac" value="<%=p.getFechaNac()%>">
	        </div>
	        
	          <div class="form-group col-md-4">
                    <label for="departamento" > Nacionalidad</label>
                    <select name="comboNacionalidad" id="departamento" class="form-control ">
                        <!-- cargaremos las etiquetas de option con javascript -->
                    </select>
                </div>

                <div class="form-group col-md-4">
                    <label for="provincia"> Provincias</label>
                    <select name="comboProvincia" id="provincia" class="form-control ">
                        <!-- cargaremos las etiquetas de option con javascript -->
                    </select>
                </div>

                <div class="form-group col-md-4">
                    <label for="distrito" > Localidades</label>
                    <select name="comboLocalidad" id="distrito" class="form-control ">
                        <!-- cargaremos las etiquetas de option con javascript -->
                    </select>
                </div>
	        
	      
	        <div class="form-group col-md-4">
	          <label>Direcci�n:</label>
	          <input type="text" class="form-control" name="TxtDireccion" value="<%=p.getDireccion()%>">
	        </div>
	        
	        <div class="form-group col-md-4">
	          <label>Sexo: </label>
	          <select class="form-control " name="ComboSexo" >
	          	<!-- Esto debería leerlo desde una DB -->
	            <option value="1">Masculino</option>
	            <option value="2">Femenino</option>
	            <option value="3">Otro</option>
	          </select>
	        </div>
	        
	        <div class="col-12">
	        
	        	<input type="submit" class="btn btn-success" value="Modificar" name="btnModificar">	        	
	        </div>
	        </div>
	      </form>
	      
	    </div>
	    
	    
	    
	    </div>
	  </div>
	
		<script type="text/javascript">
		 let $departemento = document.getElementById('departamento')
		 let $provincia = document.getElementById('provincia')
		 let $distrito = document.getElementById('distrito')

		 let departamentos = ['Argentina', 'Brasil']
		 let provincias = ['Bs As', 'Cordoba', 'San Pablo']
		 let distritos = ['San Miguel', 'San Nicolas', 'Tigre', 'Belgrano', 'Calamuchita', 'La falda', 'Gral Belgrano', 'Santa Marta', 'Loa Loa']

		 function mostrarLugares(arreglo, lugar) {
		     let elementos = '<option selected disables>--Seleccione--</option>'

		     for(let i = 0; i < arreglo.length; i++) {
		         elementos += '<option value="' + arreglo[i] +'">' + arreglo[i] +'</option>'
		     }

		     lugar.innerHTML = elementos
		 }

		 mostrarLugares(departamentos, $departemento)

		 function recortar(array, inicio, fin, lugar) {
		     let recortar = array.slice(inicio, fin)
		     mostrarLugares(recortar, lugar)
		 }

		 $departemento.addEventListener('change', function() {
		     let valor = $departemento.value

		     switch(valor) {
		         case 'Argentina':
		             recortar(provincias, 0, 2, $provincia)
		         break
		         case 'Brasil':
		             recortar(provincias, 2, 3, $provincia)
		         break
		     }

		     $distrito.innerHTML = ''
		 })

		 $provincia.addEventListener('change', function() {
		     let valor = $provincia.value

		     if(valor == 'Bs As') {
		         recortar(distritos, 0, 4, $distrito)
		     } else if(valor == 'Cordoba') {
		         recortar(distritos, 4, 7, $distrito)
		     } else {
		         recortar(distritos, 7, 9, $distrito)
		     }
		 })
		 
		 </script>
		 
		 <script>
		
	
		
		
		//PARA NO ACEPTAR LETRAS Y SIMBOLOS
		function check(e) {
		    tecla = (document.all) ? e.keyCode : e.which;

		    //Tecla de retroceso para borrar, siempre la permite
		    if (tecla == 8) {
		        return true;
		    }

		    // Patr�n de entrada, en este caso solo acepta numeros 
		    patron = /[0-9]/;
		    tecla_final = String.fromCharCode(tecla);
		    return patron.test(tecla_final);
		}
		
		//PARA NO ACEPTAR SIMBOLOS Y NUMEROS
		function checkLetras(e) {
			 tecla = (document.all) ? e.keyCode : e.which;

			 //Tecla de retroceso para borrar, siempre la permite
			 if (tecla == 8) {
   		 return true;
			}

			  // Patr�n de entrada, en este caso solo acepta numeros y letras
			  patron = /[A-Za-z]/;
			tecla_final = String.fromCharCode(tecla);
			  return patron.test(tecla_final);
			}

	</script>
	

</body>
</html>
