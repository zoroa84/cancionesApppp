<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>

<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/alerts.jsp" %>
		
	<h1>Listado genero</h1>
	
	<a class="btn btn-primary" href="${pageContext.request.contextPath}/backoffice/genero?accion=<%=Acciones.MOSTRAR_FORMULARIO%>">
		Nueva Cancion							
	</a>	
	
	
	<c:if test="${!empty listado}">
		<table class="ordenable table table-striped table-bordered">
			<caption>Listado genero</caption>	
			<thead>
				<tr>
					<th>id</th>
					<th>nombre</th>
					<th>operaciones</th>
								
				</tr>
			</thead>		
			<tbody>
				<c:forEach items="${listado}" var="genero">		
					<tr>
										
						<td>${genero.id}</td>	
						<td>${genero.nombre}</td>			
									
						<td>				
							<a href="${pageContext.request.contextPath}/backoffice/genero?id=${genero.id}&accion=<%=Acciones.MOSTRAR_FORMULARIO%>">  Modificar  </a>
							<a href="${pageContext.request.contextPath}/backoffice/genero?id=${genero.id}&accion=<%=Acciones.ELIMINAR%>"><i class="fa fa-trash" aria-hidden="true"></i></a>
						</td>				
					</tr>
				</c:forEach>		
			</tbody>
		</table>
	</c:if>
	
	
	<c:if test="${empty listado}">
		<h3>No hay genero</h3>
	</c:if>	
	
	

	


<%@include file="../../includes/footer.jsp" %>