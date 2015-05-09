<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Advanced Search</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
<link rel="stylesheet" href="custom.css" />
</head>
<body>
	<jsp:include page="includes/header.jsp" />
	
	<div class="container">
		<center>
			<h3><b> Advanced Search </b></h3>
		</center>
		<form method="get" action="./Search" class="form-add">
			<label> Name: </label>
			<input placeholder="Name" class="form-add-control" name="name" type="text">
			<label> Category: </label>
			<input placeholder="Category" class="form-add-control" name="category" type="text">
			<label> Description: </label>
			<input placeholder="Description" class="form-add-control" name="description" type="text">
			<div class="row">
		    	<div class="col-sm-8">
		       		<label></label>
		       	</div>
		       	<div class="col-sm-4">
		        	<input type="submit" value="Search" class="btn btn-primary">
		       	</div>
		   </div>
		</form>
	</div>
	<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
	<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
</body>
</html>