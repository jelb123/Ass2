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
		<form method="post" action="insertItem" class="form-add">
			<label> Name of Item: </label>
			<input placeholder="Name" maxlength=100 class="form-add-control" name="name" type="text" required="">
			<label> Category:</label>
			<input placeholder="Category" class="form-add-control" name="category" type="text" required="">
			<label> Link to Picture: </label>
			<input placeholder="Link to Picture" class="form-add-control" name="picture" type="url" required="">
			<label> Description: </label>
			<textarea rows="4" cols="20" maxlength=1000 class="form-add-control" name="description" required=""></textarea>
			<label>Address:</label>
			<div class="row">
				<input placeholder="Street Address" type="text" class="col-md-4 form-add-control" style="margin-left:15px;" name="streetAddress" required="">
				<input placeholder="City" type="text" class="col-md-2 form-add-control" name="city" required="">
			</div>
			<div class="row">
				<input placeholder="State" type="text" class="col-md-2 form-add-control" style="margin-left:15px;" name="state" required="">
				<input placeholder="Country" type="text" class="form-add-control" style="width:20%;" name="country" required="">
				<input placeholder="Postcode" type="number" min=0 class="form-add-control" style="width:17%; margin-left:15px;" name="postCode" required="">
			</div>
			<div class="row">
		        <div class="col-md-2">
		            <label>
		                Reserve Price:
		            </label>
		        </div>
		        <input type="number" class="form-add-control" min=0 style="width:17%; float:left" name="reservePrice" required="">
		        <div class="col-md-2">
		            <label>
		                 <input type="radio" name="resCurrency" value="GBP">GBP
		            </label>
		            <br> 
		            <label>
		                 <input type="radio" name="resCurrency" value="EUR">EUR
		            </label>
		            <br>
		        </div>
		        <div class="col-md-2">
		            <label>
		                 <input type="radio" name="resCurrency" value="USD">USD
		            </label>
		            <br> 
		            <label>
		                 <input type="radio" name="resCurrency" value="AUD" checked>AUD
		            </label>
		            <br>
		        </div>
		    </div>
		    <div class="row">
		        <div class="col-md-2">
		            <label>
		                Start Price:
		            </label>
		        </div>
		        <input type="number" class="form-add-control" min=0 style="width:17%; float:left" name="startPrice" required="">
		        <div class="col-md-2">
		            <label>
		                 <input type="radio" name="startCurrency" value="GBP">GBP
		            </label>
		            <br> 
		            <label>
		                 <input type="radio" name="startCurrency" value="EUR">EUR
		            </label>
		            <br>
		        </div>
		        <div class="col-md-2">
		            <label>
		                 <input type="radio" name="startCurrency" value="USD">USD
		            </label>
		            <br> 
		            <label>
		                 <input type="radio" name="startCurrency" value="AUD" checked>AUD
		            </label>
		            <br>
		        </div>
		    </div>
		    <div class="row">
		    	<label class="col-md-3"> Bid Increment:</label>
		    	<input type="number" class="form-add-control" min=1 style="width:17%; float:left" name="bidIncrement" required="">
		    </div>
		    <div class="row">
		    	<label class="col-md-3">End Time:</label>
		    	<input class="form-add-control" type="date" name="endDate" style="float:left;">
		    	<input class="form-add-control" type="time" name="endTime" style="float:left;">
		    </div>
		    <div class="row">
		    	<label class="col-md-3">ID:</label>
		    	<input class="form-add-control" style="float:left" name="id" type="text" required="">
		    </div>
		    <p></p>
		    <div class="row">
		    	<div class="col-sm-8">
		       		<label></label>
		       	</div>
		       	<div class="col-sm-4">
		        	<input type="submit" name="addItem" value="Add Item" class="btn btn-primary">
		       	</div>
		   </div>
		</form>
	</div>
	
</body>
</html>