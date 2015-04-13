<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Item Added!</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
<link rel="stylesheet" href="custom.css" />
</head>
<body>
	<jsp:include page="includes/header.jsp" />
	<div class="container">
		<center>
			<h3> Your Item has <br> been added</h3>
			<p></p>
			<a href="addItem.jsp" class="btn btn-primary"> Add Another Item </a>
			<a href="." class="btn btn-success"> Back to Homepage </a>
		</center>
	</div>
</body>
</html>