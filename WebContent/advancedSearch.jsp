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
		<form method="post" action="dispatcher" class="form-add">
			<label> Name: </label>
			<input placeholder="Name" class="form-add-control" name="name" type="text" required="">
			<label> Category: </label>
			<input placeholder="Category" class="form-add-control" name="category" type="text" required="">
			<label> Description: </label>
			<input placeholder="Description" class="form-add-control" name="description" type="text" required="">
			<label>Postal area</label>
			<input placeholder="Address" class="form-add-control" name="address" type="text" required="">
			<label> Current Bid ( +/- 10 ): </label>
			<input placeholder="Current Bid ( +/- 10 )" class="form-add-control" name="startPrice" type="number">
			<div class="row">
		    	<div class="col-sm-8">
		       		<label></label>
		       	</div>
		       	<div class="col-sm-4">
		       		<input type="hidden" name="operation" value="advancedSearch" >
		        	<input type="submit" value="Search" class="btn btn-primary">
		       	</div>
		   </div>
		</form>
	</div>
	<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
	<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
</body>
</html>