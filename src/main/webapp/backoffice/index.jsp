<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="com.ipartek.formacion.canciones.modelo.pojo.Cancion"%>

<%@include file="../includes/header.jsp"%>
<%@include file="../includes/navbar.jsp"%>


	<h2>Reporte Likes</h2>
	<ul>
		<li>Total ${report.total} </li>
		<li>Año ${report.anyo}</li>
     	<li>Mes ${report.mes}</li>
     	<li>Semana ${report.semana}</li>
     	<li>Hoy ${report.dia}</li>
	</ul>
	
	 <h1>Top 10</h1>
	     <ol>
	     <c:forEach items="${top10}" var="cancion">
	     	<li>${cancion.titulo} - ${cancion.likes} <i class="fa fa-heart" aria-hidden="true"></i></li>
	     </c:forEach>        
	     </ol>     
<%@include file="../includes/footer.jsp"%>
