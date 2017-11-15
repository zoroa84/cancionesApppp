<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/alerts.jsp" %>



<c:choose>
	<c:when test="${ artista.id == -1 }"><h1>Crear nuevo artista</h1></c:when>
	<c:otherwise><h1>Detalle ${artista.nombre}</h1></c:otherwise>
</c:choose>

<form action="${pageContext.request.contextPath}/backoffice/artistas" method="post">

	<!-- campos ocultos -->
	<input type="hidden" name="id" value="${artista.id}">
	<input type="hidden" name="accion" value="<%=Acciones.CREAR_MODIFICAR%>">
	
	<!-- campos editables por el usuario -->
	<label for="nombre">Nombre:</label>
	<input type="text" name="nombre" value="${artista.nombre}" required>
		
	<!-- boton submit -->
	<input type="submit" value="MODIFICAR">
</form>


<%@include file="../../includes/footer.jsp" %>