<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Your Wishlist!</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
<link rel="stylesheet" href="custom.css" />
</head>
<body>
	<jsp:include page="includes/header.jsp" />
	<center><h2 style="color:red"> ${msg}</h2></center>
	<center>
	<h3><b> Wish List </b></h3>
	</center>
	<div class="row">
	<c:forEach var="item" items="${items}" varStatus="i">
		<c:if test="${i.index % 5 == 0}">
			</div>
			<div class="row">
		</c:if>
   			<center>
   				<div class="col-md-2-5" style="float:left; padding-bottom: 8px;">
   					<a href="dispatcher?operation=browseitem&item=${item.itemID}">
   						<img class="img-rounded" src="${item.picture}">
   						<h5>${item.title}</h5>
   					</a>
   					<div>
		                <form method="post" action="dispatcher">
		                	<input type="hidden" name="operation" value="removefromwishlist">
		                	<input type="hidden" name="item" value="${item.itemID}">
		                	<input type="submit" value="Remove from Wishlist" class="btn btn-primary">
		                </form>
			                
			        </div>
   				</div>
   			</center>
   	</c:forEach>
	</div>
	<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
	<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
</body>