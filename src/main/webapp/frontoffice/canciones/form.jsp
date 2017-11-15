<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<%@include file="../../includes/alerts.jsp"%>
<style>
img {
	width: 500px;
	float: left;
}

form {
	float: right;
}

i {
	color: red;
}
</style>


<c:choose>
	<c:when test="${ cancion.id == -1 }">
		<h1>Crear Nueva Canción</h1>
	</c:when>
	<c:otherwise>
		<h1>${cancion.nombre}</h1>
		<h2>
			<i class="fa fa-heart-o" aria-hidden="true"></i>${cancion.likes}
			<!-- SELECT count( cancion_id) as likes FROM likes WHERE cancion_id = 3  -->
		</h2>
	</c:otherwise>
</c:choose>
<img class="cover" src="${cancion.cover}" alt="Imagen del álbum"
	class="cover" />
<form action="${pageContext.request.contextPath}/backoffice/canciones"
	method="post">

	<!-- campos ocultos -->
	<input type="hidden" name="id" value="${cancion.id}"> <input
		type="hidden" name="accion" value="<%=Acciones.CREAR_MODIFICAR%>">

	<!-- campos editables por el usuario -->



	<label for="nombre">Nombre:</label> <input type="text" name="nombre"
		value="${cancion.nombre}" required> <br> <label
		for="genero_id">Genero: ${cancion.genero.id}</label> <select
		name="genero_id">
		<c:forEach items="${generos}" var="genero">
			<option ${ ( cancion.genero.id == genero.id ) ? "selected" : "" }
				value="${genero.id}">${genero.nombre}</option>
		</c:forEach>
	</select> <br> <label for="duracion">Duración:</label> <input type="text"
		name="duracion" value="${cancion.duracion}" required> <br>

	<label for="usuario">Usuario:</label> <input disabled type="text"
		name="usuario" value="${cancion.usuario.nombre}"> <br> <label
		for="alta">alta:</label> <input type="text" name="alta"
		value="${cancion.alta}" disabled> <br> <label
		for="modificacion">modificacion:</label> <input type="text"
		name="modificiacion" value="${cancion.modificacion}" disabled>
	<br>
	
	<label for="cover">Cover:</label>
	<input type="text" name="cover" value="${cancion.cover}" class="selectAll">
	<br>


<h3>Artistas</h3>
	<ol>
		<c:forEach items="${cancion.artistas}" var="artista">
			<li>(${artista.id}) ${artista.nombre}</li>
		</c:forEach>
	</ol>




	<!-- boton submit -->
	<input type="submit" value="MODIFICAR">
</form>


<%@include file="../../includes/footer.jsp"%>