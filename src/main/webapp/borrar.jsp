<!doctype html>

<html lang="en">
<head>
  <meta charset="utf-8">

  <title>The HTML5 Herald</title>
  <meta name="description" content="The HTML5 Herald">
  <meta name="author" content="SitePoint">

  <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.js"></script>
  <![endif]-->
</head>

<body onload="init()">

  <h1>Ongi Etorri</h1>
  <p id="mensaje">Loading........</p>	


	<p>*OnSubmit si retornamos TRUE se submita, si es false NO se envia</p>
	<form action="#" method="post" onsubmit="return validar()">
		<input type="text" name="nombre" id="nombre" onkeyup="cambiaColor()">
		<div id="msg"></div>
		<button type="submit">Enviar</button>
	</form>


  <script>
  
    var nombre = document.getElementById("nombre");
    var msg = document.getElementById("msg");
   
  	function init(){
  		console.log('DOM cargado y listo');
  		var parrafo = document.getElementById("mensaje");
  		parrafo.innerHTML = "Documento <span>listo</span>"; 
  		parrafo.style.color = 'red';
  		parrafo.style.fontSize = '34px';  		
  	}
  	
  	function cambiaColor() {
		console.log('cambia color');
		if ( nombre.value.length <= 3 ){
			nombre.style.color = 'red';
		}else{
			nombre.style.color = 'green';
		}	
	}
  	
  	function validar() {
  		if ( nombre.value.length > 3 ){
  			return true;
  		}else{
  			msg.innerHTML = "*El nombre debe tener minimo 3 caracteres";
  			return false;	
  		}		
	}
  
  </script>
  
</body>
</html>



