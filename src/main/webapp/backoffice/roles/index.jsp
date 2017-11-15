<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>

<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/alerts.jsp" %>
		
	<h1>Listado Roles</h1>
	
	<a class="btn btn-primary" href="${pageContext.request.contextPath}/backoffice/roles?accion=<%=Acciones.MOSTRAR_FORMULARIO%>">
		Nueva Registro							
	</a>	
	
	
	<c:if test="${!empty listado}">
		<table class="ordenable table table-striped table-bordered">
			<caption>Listado Roles</caption>	
			<thead>
				<tr>
					<th>id</th>
					<th>Nombre</th>								
					<th>Operaciones</th>
				</tr>
			</thead>		
			<tbody>
				<c:forEach items="${listado}" var="rol">		
					<tr>
						<td>${rol.id}</td>				
						<td>${rol.nombre}</td>								
						<td>				
							<a href="${pageContext.request.contextPath}/backoffice/roles?id=${rol.id}&accion=<%=Acciones.MOSTRAR_FORMULARIO%>">  Modificar  </a>
							
							<a id="btn-eliminar" href="${pageContext.request.contextPath}/backoffice/roles?id=${rol.id}&accion=<%=Acciones.ELIMINAR%>"  class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" onclick="eliminar(${rol.id})">borrar</a>
						<!--  	<a href="${pageContext.request.contextPath}/backoffice/roles?id=${rol.id}&accion=<%=Acciones.ELIMINAR%>"><i class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" >borrar</i></a>-->
							
						</td>				
					</tr>
				</c:forEach>		
			</tbody>
			<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        desea borrar el rol????
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">cancelar</button>
        <button type="button" class="btn btn-primary">borrar</button>
      </div>
    </div>
  </div>
</div>

		</table>
		<div class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
        <p>One fine body&hellip;</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	</c:if>
	
	
	<c:if test="${empty listado}">
		<h3>No hay Registros</h3>
		<p><a href="${pageContext.request.contextPath}/backoffice/roles?accion=<%=Acciones.MOSTRAR_FORMULARIO%>">¿ Quieres crear uno nuevo ?</a></p>
	</c:if>	
	
	

	

<script>
	function eliminar(id){
		console.debug();
		var botonEliminar = document.getElementById("btn_eliminar");
		botonEliminar.href += id;
	}
</script>
<%@include file="../../includes/footer.jsp" %>