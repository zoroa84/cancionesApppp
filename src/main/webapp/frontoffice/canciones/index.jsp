<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/alerts.jsp" %>
		
	<h1>Listado Canciones</h1>
	
	<a class="btn btn-primary" href="${pageContext.request.contextPath}/frontoffice/canciones?accion=<%=Acciones.MOSTRAR_FORMULARIO%>">
		Nueva Cancion							
	</a>	
	
	
	<c:if test="${!empty listado}">
		<table class="ordenable table table-striped table-bordered">
			<caption>Listado Canciones</caption>	
			<thead>
				<tr>
					<th>Id</th>
					<th>Cover</th>
					<th>Nombre</th>
					<th>genero</th>
					
					<th>Duraci�n</th>
					<th>usuario</th>
					<th>Operaciones</th>
									
				</tr>
			</thead>		
			<tbody>
				<c:forEach items="${listado}" var="c">		
					<tr>
						<td>${c.id}</td>
						<td>
							<a href="${pageContext.request.contextPath}/frontoffice/canciones?id=${c.id}&accion=<%=Acciones.MOSTRAR_FORMULARIO%>">
								<img class="cover" src="${c.cover}" alt="Imagen del �lbum" class="cover" />
							</a>	
						</td>				
						<td>${c.nombre}</td>
						<td>${c.genero.nombre}</td>					
						
						<td>${c.duracion}</td>	
						<td>${c.usuario.nombre}</td>				
						<td>				
							<a href="${pageContext.request.contextPath}/frontoffice/canciones?id=${c.id}&accion=<%=Acciones.MOSTRAR_FORMULARIO%>">  Modificar  </a>
							<a href="${pageContext.request.contextPath}/frontoffice/canciones?id=${c.id}&accion=<%=Acciones.ELIMINAR%>"><i class="fa fa-trash" aria-hidden="true"></i></a>
						</td>				
					</tr>
				</c:forEach>		
			</tbody>
		</table>
	</c:if>
	
	
	<c:if test="${empty listado}">
		<h3>No hay canciones</h3>
	</c:if>	
	
	

	


<%@include file="../../includes/footer.jsp" %>