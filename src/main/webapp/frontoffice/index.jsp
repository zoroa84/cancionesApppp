<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="com.ipartek.formacion.canciones.modelo.pojo.Cancion"%>

<%@include file="../includes/header.jsp" %>
<%@include file="../includes/navbar.jsp" %>

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
            
        <ol data-list="user">    
        <c:forEach items="${pageScope.top5}" var="cancion" varStatus="iteration">
        	
        </c:forEach>    
        </ol>    
            
            
        
        
    
        
        
     
      </div>

<%@include file="../includes/footer.jsp" %>    