<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin: Show all users</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
<link rel="stylesheet" href="custom.css" />
</head>
<body>
	<jsp:include page="includes/header.jsp" />
	
	<div class="container" align="CENTER">
			<br><br>
			<c:if test="${msg != null}">
		    	<p class="bg-danger" style="width:50%" align="CENTER"> ${msg}</p>
	    	</c:if>
			<table class="table table-striped" style="width:50%" align="CENTER">
			<tr>
				<th> Username </th>
				<th> Id </th>
				<th> Account Status </th>
				<th> 	</th>
			</tr>
			<c:forEach var="user" items="${userslist}" varStatus="i">
			<tr>
				<form method="post" action="dispatcher">
					<input type="hidden" name="operation" value="banuser">
					<input type="hidden" name="id" value="${user.id}">
					<td> ${user.username} </td>
					<td> ${user.id} </td>
					<td> <c:if test="${user.accountState == 1}"> Active </c:if> 
					<c:if test="${user.accountState == 2}"> Pending </c:if>
					<c:if test="${user.accountState == 3}"> Banned </c:if>
					<td> <c:if test="${user.accountState != 3 && user.isAdmin == false}"> <input type="submit" name="Ban User" value="Ban User" class="btn btn-danger btn-sm" /></c:if> </td>
				</form>
			</tr>	
		
			</c:forEach>
			</table>
		
	</div>
	<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
	<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
</body>
</html>