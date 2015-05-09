<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
<link rel="stylesheet" href="custom.css" />
</head>
<body>
	<jsp:include page="includes/header.jsp" />
	
	<div class="container">
	    <form method="post" action="dispatcher"  class="form-signin">
		    <input type="hidden" name="operation" value="login">
		    <h2 class="form-signin-heading" align="CENTER">
		        Please Log In
		    </h2>
		    <c:if test="${msg != null}">
		    	<p style="color:RED"> ${msg}</p>
		    </c:if>
		    <input placeholder="Username" class="form-control" name="username" type="text" required="" />
		    <input placeholder="Password" class="form-control" name="password" type="password" required="" />
		    <%--
			    <label class="checkbox" align="RIGHT">
			        <label>
			             <input type="checkbox" name="Remember me" value="on" />Remember me
			        </label>
			    </label>
		    --%>
		    <div class="controls" align="CENTER">
		        <input type="submit" name="Login" value="Login" class="btn btn-primary" />
		        <small>
		            or <a href="registerUser.jsp"> create a new account </a>
		        </small>
		    </div>
	    </form>
	    
	</div>
	<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
	<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
</body>
</html>