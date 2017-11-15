/* 
 *  Gestionar el formulario de Registro de Usuarios 
 *  Haremos llamadas por AJAX para comprobar si el "nombre" o "email" del usuario existen 
 *  Para realizar las consultas a la BBDD vamos a realizarlo con "procedimientos almacenados"
 * 
 */

$( document ).ready(function() {
  
	console.log('DOM cargado');
		
	var checkNombre = false;
	var checkEmail = false;
	
	checkSubmit();
		
	$("#nombre").keyup(function(){
		//llamar solo si mas de tres caracteres
		if( $("#nombre").val().length > 2 ){
			console.log('llamada Ajax....');		
			var url = "checkUser";
			$.ajax( url , {
				"type": "get",
				"success": function(result) {
					console.log("Llego el contenido y no hubo error", result);
					if (result.disponible){
						$("#nombreMsg").html('<p class="bg-success">'+result.mensaje+'</p>');
						checkNombre = true;
					}else{
						$("#nombreMsg").html('<p class="bg-danger">'+result.mensaje+'</p>');
						checkNombre = false;
					}
					checkSubmit();
				},
				"error": function(result) {
					console.error("Este callback maneja los errores", result);
				},
				"data": { nombre: $("#nombre").val() }
			});// ajax		
		}//if
		
	});// focusout
	
	
	
	$("#email").keyup(function(){
		if( $("#email").val().length > 3 ){
			console.log('llamada Ajax....');			
			var url = "checkUser";
			$.ajax( url , {
				"type": "get",
				"success": function(result) {
					console.log("Llego el contenido y no hubo error", result);
					if (result.disponible){
						$("#emailMsg").html('<p class="bg-success">'+result.mensaje+'</p>');
						checkEmail = true;						
					}else{
						$("#emailMsg").html('<p class="bg-danger">'+result.mensaje+'</p>');
						checkEmail = false;
					}	
					checkSubmit();					
				},
				"error": function(result) {
					console.error("Este callback maneja los errores", result);
				},
				"data": { email: $("#email").val() }
			});// ajax	
			
		}
	});// focusout
	
	function checkSubmit() {
		
		if ( checkEmail && checkNombre ){
			$("#bt-submit").prop("disabled",false);
			$("#bt-submit").removeClass("bt-disabled");
		}else{
			$("#bt-submit").prop("disabled",true);
			$("#bt-submit").addClass("bt-disabled");
		}
		
	}
	
	function requerido(input){
		if(input != " "){
			document.getElementById("#escribir").style = ""; /******************/
		}else{
			document.getElementById("#escribir").innerHTML = " *requerido";
		}
	}
	
});