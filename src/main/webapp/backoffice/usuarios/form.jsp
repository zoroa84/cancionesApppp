<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/alerts.jsp" %>



<c:choose>
	<c:when test="${ usuario.id == -1 }"><h1>Crear nuevo usuario</h1></c:when>
	<c:otherwise><h1>Detalle ${usuario.nombre}</h1></c:otherwise>
</c:choose>
<div style="width: 30%;display: inline-block;" id="uno" ><img style="width:100%;display: inline-block; " class="dos" src="${usuario.avatar }"> </div>
<form action="${pageContext.request.contextPath}/backoffice/usuarios" method="post" class="tres" style="width: 50%;display: inline-block;">
	
	
	<!-- campos ocultos -->
	<input type="hidden" name="id" value="${usuario.id}">
	<input type="hidden" name="accion" value="<%=Acciones.CREAR_MODIFICAR%>">
	
	
	<!-- campos editables por el usuario -->
	<label for="email">Email:</label>
	<input type="text" name="email" value="${usuario.email}" required>
	<br>
	
	<label for="pass">Password or Key:</label>
	<input type="text" name="pass" value="${usuario.pass}" required>
	<br>
	
	<label for="nombre">Nombre:</label>
	<input type="text" name="nombre" value="${usuario.nombre}" required>
	<br>
	
	
	<label for="avatar">Avatar o foto:</label>
	<input type="text" name="avatar" value="${usuario.avatar}">
	<br>
	
		<label for="rol_id">Rol (tipo de usuario): ${usuario.rol.id}</label>
		<select name="rol_id">
		<c:forEach items="${roles}" var="rol">
			<option ${ ( usuario.rol.id == rol.id ) ? "selected" : "" } value="${rol.id}">${rol.nombre}</option>
		</c:forEach>
		</select> <br>
	
	<!-- boton submit -->
	<input type="submit" value="MODIFICAR">
</form>

${canciones }

<%@include file="../../includes/footer.jsp" %>