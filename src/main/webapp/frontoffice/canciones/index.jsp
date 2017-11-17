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
					
					<th>Duración</th>
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
								<img class="cover" src="${c.cover}" alt="Imagen del álbum" class="cover" />
							</a>	
						</td>				
						<td>${c.nombre}</td>
						<td>${c.genero.nombre}</td>					
						
						<td>${c.duracion}</td>	
						<td>${c.usuario.nombre}</td>				
						<td>				
							<a href="${pageContext.request.contextPath}/frontoffice/canciones?id=${c.id}&accion=<%=Acciones.MOSTRAR_FORMULARIO%>">  Modificar  </a>
				
							<!-- guk   --> <a class="confirmDelete" href="${pageContext.request.contextPath}/frontoffice/canciones?id=${c.id}&accion=<%=Acciones.ELIMINAR%>" data-toggle="modal" ><i class="fa fa-trash" aria-hidden="true"></i></a>						
						</td>				
					</tr>
				</c:forEach>		
			</tbody>
		</table>
	</c:if>
	
	
	<c:if test="${empty listado}">
		<h3>No hay canciones</h3>
	</c:if>	
	
	
	<!-- Modal -->
	<div class="modal fade" id="modalEliminar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">Eliminar Registro</h4>
	      </div>
	      <div class="modal-body">
	        ¿ Estas seguro que deseas eliminar ?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	        
	        <a id="btn_eliminar" class="btn btn-danger" href="">ACEPTAR</a>
	        
	      </div>
	    </div>
	  </div>
	</div>
	
	


<%@include file="../../includes/footer.jsp" %>