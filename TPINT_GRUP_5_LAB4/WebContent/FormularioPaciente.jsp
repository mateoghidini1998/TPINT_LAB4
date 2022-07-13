<%@page import="excepciones.UsuarioSinPermisoException"%>
<%@page import="entidad.Usuario"%>
<%@page import="excepciones.UsuarioNoLoggeadoException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar paciente</title>
<style type="text/css">
	<jsp:include page="css\StyleSheetMain.css"></jsp:include>
</style>
	
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
	 
	 String mensaje = "";
	%>
	<jsp:include page="Menu.jsp"></jsp:include>
	<div class="container">
	  <div class="row">
	    <div class="col-12">
	     <form  method="post" action="ServletPacientes">
	      
			<h1 class="h1 mb-5" >Ingresar nuevo paciente</h1>
			<div class="form-row">
	        <div class="form-group  col-md-4">
	          <div> 
	          <label>Nombre:</label> 
	          <input type="text" onkeypress="return checkLetras(event)" class="form-control" name="txtNombre" title="Ingrese su nombre" required /> </div>
	        </div>
	        <div class="form-group col-md-4">	          
	          <div>
	          <label>Apellido:</label> 
	          <input type="text" onkeypress="return checkLetras(event)" class="form-control" name="txtApellido" required="">
	          </div>
	        </div>
	        
	        <div class="form-group col-md-4">
	          <label>DNI: </label>
	          <input type="text" onkeypress="return check(event)" maxlength="8" minlength="7" class="form-control" name="txtDNI" required>
	        </div>
	        <div class="form-group col-md-4">
	          <label>Correo electrónico: </label>
	          <input type="email" class="form-control" name="txtCorreo" required>
	        </div>
	        
	        
	        
	        <div class="form-group col-md-4">
	          <label>Teléfono: </label>
	          <input type="text" onkeypress="return check(event)" class="form-control" name="txtTelefono" required>
	        </div>
	        
	         <div class="form-group col-md-4">
	          <label>Celular: </label>
	          <input type="text" onkeypress="return check(event)" class="form-control" name="txtCelular" required>
	        </div>
	        
	        <div class="form-group col-md-4">
	          <label>Fecha de nacimiento: </label>
	          <input type="date" class="form-control " name="txtFechaNac" required>
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
	          <label>Dirección:</label>
	          <input type="text" class="form-control" name="txtDireccion" required>
	        </div>
	        
	        <div class="form-group col-md-4">
	          <label>Sexo: </label>
	          <select class="form-control " name="comboSexo" >
	          	<!-- Esto debería leerlo desde una DB -->
	          	<option value="" selected>Seleccionar...</option>
	            <option value="1">Masculino</option>
	            <option value="2">Femenino</option>
	            <option value="3">Otro</option>
	          </select>
	        </div>
	        
	        <div class="col-12">
	        	<input onclick="confirmarAgregar(event)"  type="submit" class="btn btn-success" value="Aceptar" name="btnAceptar">
	        	
	        </div>
	       </div> 
	      </form>
	      
	       <!-- PARA LIMPIAR CAMPOS  -->
	       <form  method="post" action="ServletPacientes">
	       <div class="col-12 ">
	       <input  type="submit" class="btn btn-danger" value="Limpiar Campos" name="btnLimpiarCampos">
	       </div>
	       </form>
	      
	    </div>
	  </div>
	 </div>
	 
		 <%
		if (request.getAttribute("estadoPaciente") != null) {
	%>
	<%=request.getAttribute("estadoPaciente")%>
	<%
		}
	%>
	
	<script>
		
		
			
			let btnClear = document.querySelector('button');
			
			let inputs = document.querySelector('inputs');
			
			btnClear.addEventListener('click', ()=>{
				inputs.forEach(input => input.value = ' ');
					
			});
			
	
	
		
	</script>
	
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
		
		function confirmarAgregar(event) {
			
			  var result = confirm("Esta seguro que desea agregar este Paciente?");
			  if (result === false) {
			    event.preventDefault();
			    
			  }			  
			  
			  			  
			}
		
		
		//PARA NO ACEPTAR LETRAS Y SIMBOLOS
		function check(e) {
		    tecla = (document.all) ? e.keyCode : e.which;

		    //Tecla de retroceso para borrar, siempre la permite
		    if (tecla == 8) {
		        return true;
		    }

		    // Patrón de entrada, en este caso solo acepta numeros 
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

			  // Patrón de entrada, en este caso solo acepta numeros y letras
			  patron = /[A-Za-z]/;
			tecla_final = String.fromCharCode(tecla);
			  return patron.test(tecla_final);
			}

	</script>
	
	
	

</body>
</html>