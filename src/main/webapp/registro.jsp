<%@page errorPage="error.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico">

    <title>Canciones Web</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap.min.css" rel="stylesheet">  
    <link href="${pageContext.request.contextPath}/css/register.css" rel="stylesheet">  
</head>
<body>

<hgroup>
  <h1>Registro</h1>
</hgroup>



<form action="${pageContext.request.contextPath}/registro" method="post">
<p>${msg}</p>
		
  <div class="group">
    <input type="text" name="nombre" value="${usuario.nombre}" id="nombre" placeholder="Dinos tu Nombre" onblur ="requerido(this);">       
    <div id="nombreMsg"></div><div id="escribir"> *requerido </div>
  </div>
  <div class="group">
    <input type="password" name="pass" value="" placeholder="Contrseña">        
  </div>
  <div class="group">
    <input type="password" name="pass2" value="" placeholder="Repite Contrseña">   
  </div>
  <div class="group">
    <input type="email" id="email" name="email" value="${usuario.email}" placeholder="Tu Email">   
     <div id="emailMsg"></div> 
  </div>
  <div class="group">
    <input type="text" name="avatar" value="${usuario.avatar}" placeholder="URL de la imagen de Avatar">   
  </div>
  <button type="submit" class="button buttonBlue" id="bt-submit">registrarme
    <div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
  </button>
  <a href="${pageContext.request.contextPath}/login.jsp">< volver</a>
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="js/registro.js" ></script>
</body>
</html>