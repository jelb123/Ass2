<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Details - ${user.username}</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
<link rel="stylesheet" href="custom.css" />
</head>
<body>
	<jsp:include page="includes/header.jsp" />

	<div class="container">
		<h2>
			<center> Update ${user.username}'s Details</center>
		</h2>
		
		
		
		<form method="post" action="dispatcher" class="form-add">
		    <input type="hidden" name="operation" value="updateuserdetails">
			
			<div class="row">
				<div class="col-md-4">
					<label> First Name: </label> <br>
					<input placeholder="First Name" maxlength=20 class="form-add-control" name="firstname" type="text" >			
				</div>
				<div class="col-md-1"> </div>
				<div class="col-md-4">
					<label> Last Name: </label> <br>
					<input placeholder="Last Name" maxlength=20 class="form-add-control" name="lastname" type="text" >
				</div>
			</div>
			
			<label> Nickname: </label> <br>
			<input placeholder="Nickname" maxlength=20 class="form-add-control" name="nickname" type="text" > 
			
			<div class="row">
				<div class="col-md-5">
					<label> Password: </label> <br>
					<input placeholder="Password" maxlength=20 class="form-add-control" name="password" type="password" >
				</div>
			</div>
								
			<label> Email Address: </label> <br>
			<input placeholder="Email Address" maxlength=100 class="form-add-control" name="email" type="email" >
			
			<label> Date Of Birth: </label> <br>
			<input class="form-add-control" type="date" name="dateofbirth" style="float:left;">
			<br><br>
			<label>Address:</label> <br>
			<div class="row">
				<input placeholder="Street Address" maxlength=100 type="text" class="col-md-4 form-add-control" style="margin-left:15px;" name="streetaddress" >
				<input placeholder="City" maxlength=50 type="text" class="col-md-2 form-add-control" name="city" >
			</div>
			<div class="row">
				<input placeholder="State" maxlength=50 type="text" class="col-md-2 form-add-control" style="margin-left:15px;" name="state" >
				<input placeholder="Country" maxlength=50 type="text" class="form-add-control" style="width:20%;" name="country" >
				<input placeholder="Postcode" type="number" min=0 class="form-add-control" style="width:17%; margin-left:15px;" name="postcode" >
			</div>
			
			<label> Credit Card Number: </label> <br>
			<input placeholder="Credit Card Number" type="number" min=0 class="form-add-control" name="creditcardnumber" >
			<br>
			<div class="row">
		    	<div class="col-sm-8">
		       		<label></label>
		       	</div>
		       	<div class="col-sm-4">
		       			<c:choose>
		       				<c:when test="${user != null}">
		       					<input type="submit" name="addItem" value="Add Item" class="btn btn-primary"> 
		       				</c:when>
		       				<c:otherwise>
		       					<input type="submit" name="addItem" value="Add Item" class="btn btn-primary" disabled> 
		       					<p style="color:RED"> Please Log to update your details</p> 
		       				</c:otherwise>
		       			</c:choose>
		       	</div>
		   </div>
		</form>
	</div>
	<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
	<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
</body>
</html>