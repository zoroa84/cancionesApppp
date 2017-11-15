<%@include file="../includes/header.jsp" %>
<%@include file="../includes/navbar.jsp" %>
 
<div class="row">
      <div class="col-xs-12 col-sm-6 col-md-6">
      
		<%@include file="../includes/alerts.jsp" %>      	
      
		<div class="row">
		    <div class="col-sm-6 col-md-4">
		        <img src="${sessionScope.usuario_logeado.avatar}" alt="" class="img-rounded img-responsive" />
		  </div>
		  <div class="col-sm-6 col-md-8">
		      <h3> <i class="glyphicon glyphicon-user"></i>${sessionScope.usuario_logeado.nombre}</h3>
		      
		      <form action="${pageContext.request.contextPath}/frontoffice/profile" method="post">
		      
		          <i class="glyphicon glyphicon-envelope"></i>
		          <input type="email" name="email" value="${sessionScope.usuario_logeado.email}">  
		    	              
		          <br />
		          <i class="glyphicon glyphicon-lock"></i>		          
		          <input type="password" name="pass" value="${sessionScope.usuario_logeado.pass}">		          
		          <br>		
		          <i class="glyphicon glyphicon-lock"></i>				          
		          <input type="password" name="repass" value="" placeholder="Repite Contraseña">
		          <br>
		          <label for="avatar">Avatar:</label>
		          <input type="text" class="selectAll" name="avatar" value="${sessionScope.usuario_logeado.avatar}">
		          <br>
		          <br>
		          <button type="submit" class="btn btn-primary">Modificar</button>
		          
		      </form>
		      
		  </div>
        </div>
    </div>
</div>


	    	    

<%@include file="../includes/footer.jsp" %>   