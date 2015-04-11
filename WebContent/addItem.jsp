<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
<link rel="stylesheet" href="custom.css" />
</head>
<body>
	<jsp:include page="includes/header.jsp" />
	<h2>
		<center> Add an Item</center>
	</h2>
	<div class="container">
		<form method="post" action="" enctype="multipart/form-data" class="form-add">
			<label> Name of Item: </label>
			<input placeholder="Name" class="form-add-control" name="name" type="text" required="">
			<label> Category:</label>
			<input placeholder="Category" class="form-add-control" name="category" type="text" required="">
			<label> Link to Picture: </label>
			<input placeholder="Link to Picture" class="form-add-control" name="picture" type="text" required="">
			<label> Description: </label>
			<textarea rows="4" cols="100" class="form-add-control" name="description" required=""></textarea>
			<label>Address:</label>
			<div class="row">
				<div class="col-md-5">
					<input placeholder="Street Address" class="form-add-control" name="streetAddress" required="">
				</div>
				<div class="col-md-4">
					<input placeholder="City" class="form-add-control" name="city" required="">
				</div>
			</div>
		</form>
	</div>
	
</body>
</html>