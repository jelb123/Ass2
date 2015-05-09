<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>You Are Registered!</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
<link rel="stylesheet" href="custom.css" />
</head>
<body>
	<jsp:include page="includes/header.jsp" />
	
	<div class="container">
		<form method="post" action="dispatcher"  class="form-signin">
			<input type="hidden" name="operation" value="browseitems">
			<c:if test="${msg != null}">
		    	<p class="bg-success"> ${msg}</p>
		    </c:if>
		    <div class="controls" align="CENTER">
		        <input type="submit" name="Home" value="Home" class="btn btn-primary" /> 
			</div>
		</form>
	</div>
</body>
</html>